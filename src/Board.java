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
        return squares[y][x];
    }


    public void printBoard() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                System.out.print(squares[y][x] + " ");
            }
            System.out.println();
        }
    }

    // Movement
    public void movePiece(int fromX, int fromY, int toX, int toY) {
        Square fromSquare = getSquare(fromX, fromY);
        Square toSquare = getSquare(toX, toY);
        Piece movingPiece = fromSquare.getPiece();

        if (movingPiece == null) {
            System.out.println("No piece on the selected square!");
            return;
        }

        // Check if it's the correct player's turn
        if (!movingPiece.getColor().equals(currentTurn)) {
            System.out.println("It's not " + movingPiece.getColor() + "'s turn!");
            return;
        }

        Piece targetPiece = toSquare.getPiece();

        // Prevent capturing your own piece
        if (targetPiece != null && targetPiece.getColor().equals(movingPiece.getColor())) {
            System.out.println("Invalid move: cannot capture your own piece!");
            return;
        }

        // Validate the move according to piece rules
        if (!movingPiece.isValidMove(this, fromX, fromY, toX, toY)) {
            System.out.println("Invalid move for " + movingPiece.getSymbol());
            return;
        }

        // Execute the move
        toSquare.setPiece(movingPiece);
        fromSquare.setPiece(null);

        // Print result (capture or move)
        if (targetPiece != null) {
            System.out.println(movingPiece.getSymbol() + " captured " + targetPiece.getSymbol() +
                    " at (" + toX + "," + toY + ")");
        } else {
            System.out.println("Move completed: " + movingPiece.getSymbol() +
                    " (" + fromX + "," + fromY + ") → (" + toX + "," + toY + ")");
        }

        // Switch turn
        currentTurn = currentTurn.equals("white") ? "black" : "white";
        System.out.println("Next turn: " + currentTurn);
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
}
