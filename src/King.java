public class King extends Piece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "K" : "k";
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);

        // The king can move a maximum of 1 square.
        if (dx > 1 || dy > 1) {
            return false;
        }

        // The destination cell must be either empty or contain an enemy.
        Piece target = board.getSquare(toX, toY).getPiece();
        return target == null || !target.getColor().equals(getColor());
    }
}
