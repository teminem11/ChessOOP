public class Board {
    private final Square[][] squares = new Square[8][8];
    private String currentTurn = "white"; // White starts first


    public Board() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                squares[y][x] = new Square(x, y);
            }
        }

        setupPieces();
    }

    public Square getSquare(int x, int y) {
        if (!isInside(x, y)) throw new IllegalArgumentException("Coordinates must be between 0 and 7");
        return squares[y][x];
    }

    public String getCurrentTurn() { return currentTurn; }
    public static boolean isInside(int x, int y) { return x >= 0 && x < 8 && y >= 0 && y < 8; }


    public void printBoard() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                System.out.print(squares[y][x] + " ");
            }
            System.out.println();
        }
    }

    // Movement
    public boolean movePiece(int fromX, int fromY, int toX, int toY) {
        if (!isInside(fromX, fromY) || !isInside(toX, toY) || (fromX == toX && fromY == toY)) {
            System.out.println("Invalid coordinates or destination!");
            return false;
        }
        Square fromSquare = getSquare(fromX, fromY);
        Square toSquare = getSquare(toX, toY);
        Piece movingPiece = fromSquare.getPiece();

        if (movingPiece == null) {
            System.out.println("No piece on the selected square!");
            return false;
        }

        if (!movingPiece.getColor().equals(currentTurn)) {
            System.out.println("It's not " + movingPiece.getColor() + "'s turn!");
            return false;
        }

        Piece targetPiece = toSquare.getPiece();

        if (targetPiece != null && targetPiece.getColor().equals(movingPiece.getColor())) {
            System.out.println("Invalid move: cannot capture your own piece!");
            return false;
        }

        if (!movingPiece.isValidMove(this, fromX, fromY, toX, toY)) {
            System.out.println("Invalid move for " + movingPiece.getSymbol());
            return false;
        }

        // Check if the player is currently in check
        boolean playerIsInCheck = isKingInCheck(movingPiece.getColor());

        // Temporarily make the move
        toSquare.setPiece(movingPiece);
        fromSquare.setPiece(null);

        // Check if this move leaves the player's king in check
        if (isKingInCheck(movingPiece.getColor())) {
            // Undo the move
            fromSquare.setPiece(movingPiece);
            toSquare.setPiece(targetPiece);

            if (playerIsInCheck) {
                System.out.println("Invalid move: your king would still be in check!");
            } else {
                System.out.println("Invalid move: this would leave your king in check!");
            }
            return false;
        }

        if (movingPiece instanceof Pawn && (toY == 0 || toY == 7)) {
            movingPiece = new Queen(movingPiece.getColor());
            toSquare.setPiece(movingPiece);
            System.out.println("Pawn promoted to queen!");
        }

        // Move is valid
        if (targetPiece != null) {
            System.out.println(movingPiece.getSymbol() + " captured " + targetPiece.getSymbol() +
                    " at (" + toX + "," + toY + ")");
        } else {
            System.out.println("Move completed: " + movingPiece.getSymbol() +
                    " (" + fromX + "," + fromY + ") → (" + toX + "," + toY + ")");
        }

        // Check if opponent's king is now in check
        String opponentColor = movingPiece.getColor().equals("white") ? "black" : "white";
        if (isKingInCheck(opponentColor)) {
            System.out.println("CHECK on " + opponentColor + " king!");
        }

        // Switch turn
        currentTurn = currentTurn.equals("white") ? "black" : "white";
        System.out.println("Next turn: " + currentTurn);
        return true;
    }

    public boolean isCheckmate(String color) { return isKingInCheck(color) && !hasLegalMove(color); }
    public boolean isStalemate(String color) { return !isKingInCheck(color) && !hasLegalMove(color); }

    public boolean hasLegalMove(String color) {
        for (int fy = 0; fy < 8; fy++) for (int fx = 0; fx < 8; fx++) {
            Piece moving = squares[fy][fx].getPiece();
            if (moving == null || !moving.getColor().equals(color)) continue;
            for (int ty = 0; ty < 8; ty++) for (int tx = 0; tx < 8; tx++) {
                Piece target = squares[ty][tx].getPiece();
                if ((fx == tx && fy == ty) || (target != null && target.getColor().equals(color))) continue;
                if (!moving.isValidMove(this, fx, fy, tx, ty)) continue;
                squares[ty][tx].setPiece(moving); squares[fy][fx].setPiece(null);
                boolean legal = !isKingInCheck(color);
                squares[fy][fx].setPiece(moving); squares[ty][tx].setPiece(target);
                if (legal) return true;
            }
        }
        return false;
    }

    private void setupPieces() {

        for (int x = 0; x < 8; x++) {
            squares[1][x].setPiece(new Pawn("white"));
        }

        squares[0][0].setPiece(new Rook("white"));
        squares[0][7].setPiece(new Rook("white"));
        squares[0][1].setPiece(new Knight("white"));
        squares[0][6].setPiece(new Knight("white"));
        squares[0][2].setPiece(new Bishop("white"));
        squares[0][5].setPiece(new Bishop("white"));
        squares[0][3].setPiece(new Queen("white"));
        squares[0][4].setPiece(new King("white"));


        for (int x = 0; x < 8; x++) {
            squares[6][x].setPiece(new Pawn("black"));
        }

        squares[7][0].setPiece(new Rook("black"));
        squares[7][7].setPiece(new Rook("black"));
        squares[7][1].setPiece(new Knight("black"));
        squares[7][6].setPiece(new Knight("black"));
        squares[7][2].setPiece(new Bishop("black"));
        squares[7][5].setPiece(new Bishop("black"));
        squares[7][3].setPiece(new Queen("black"));
        squares[7][4].setPiece(new King("black"));
    }

    // Check if the given color's king is under attack
    public boolean isKingInCheck(String color) {
        // 1️⃣ Find the king of the given color
        int kingX = -1, kingY = -1;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Piece piece = squares[y][x].getPiece();
                if (piece instanceof King && piece.getColor().equals(color)) {
                    kingX = x;
                    kingY = y;
                    break;
                }
            }
        }

        if (kingX == -1) {
            System.out.println("Error: King of color " + color + " not found!");
            return false;
        }

        //  Check if any enemy piece can attack the king
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Piece piece = squares[y][x].getPiece();
                if (piece != null && !piece.getColor().equals(color)) {
                    if (piece.isValidMove(this, x, y, kingX, kingY)) {
                        return true; // King is under attack
                    }
                }
            }
        }

        return false; // No threats found
    }

}
