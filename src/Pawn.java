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
        int direction = getColor().equals("white") ? 1 : -1; // White moves up, Black moves down
        int dx = Math.abs(toX - fromX);
        int dy = toY - fromY;

        Square destination = board.getSquare(toX, toY);
        Piece target = destination.getPiece();

        // Normal forward move (1 square)
        if (dx == 0 && dy == direction && target == null) {
            return true;
        }

        // Initial double move (2 squares)
        if (dx == 0 && dy == 2 * direction && target == null) {
            boolean isAtStart = (getColor().equals("white") && fromY == 1)
                    || (getColor().equals("black") && fromY == 6);
            // Check both squares are empty
            if (isAtStart && board.getSquare(toX, fromY + direction).getPiece() == null) {
                return true;
            }
        }

        // Capture move (diagonal)
        if (dx == 1 && dy == direction && target != null && !target.getColor().equals(getColor())) {
            return true;
        }

        return false;
    }
}
