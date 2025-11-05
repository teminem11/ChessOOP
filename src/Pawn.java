public class Pawn extends Piece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "P" : "p";
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        int direction = getColor().equals("white") ? 1 : -1; // White goes up, black goes down

        // A pawn can only move vertically.
        if (fromX != toX) {
            return false;
        }

        // One cell forward
        if (toY == fromY + direction && board.getSquare(toX, toY).getPiece() == null) {
            return true;
        }

        // Two cells forward from the starting position
        if ((getColor().equals("white") && fromY == 1 && toY == 3) ||
                (getColor().equals("black") && fromY == 6 && toY == 4)) {

            // both squares in front of the pawn must be empty
            if (board.getSquare(toX, fromY + direction).getPiece() == null &&
                    board.getSquare(toX, toY).getPiece() == null) {
                return true;
            }
        }

        return false;
    }
}
