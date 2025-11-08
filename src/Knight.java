public class Knight extends Piece {
    public Knight(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "N" : "n";
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);

        // The knight moves in an "L" shape: 2 in one direction and 1 in the other
        boolean isLShape = (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
        if (!isLShape) {
            return false; // Invalid move pattern
        }

        // The destination must be empty or contain an opponent's piece
        Piece target = board.getSquare(toX, toY).getPiece();
        return target == null || !target.getColor().equals(getColor());
    }
}
