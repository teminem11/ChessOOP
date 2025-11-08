public class Bishop extends Piece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "B" : "b";
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);

        // A bishop can only move diagonally: difference in X and Y must be the same
        if (dx != dy) {
            return false;
        }

        // Determine the direction of movement (stepX, stepY)
        int stepX = Integer.compare(toX, fromX); // -1, 0, 1
        int stepY = Integer.compare(toY, fromY);

        // Check all squares between the start and end positions
        int x = fromX + stepX;
        int y = fromY + stepY;
        while (x != toX || y != toY) {
            if (board.getSquare(x, y).getPiece() != null) {
                return false; // There’s a piece blocking the path
            }
            x += stepX;
            y += stepY;
        }

        // The destination square must be empty or contain an opponent’s piece
        Piece destination = board.getSquare(toX, toY).getPiece();
        return destination == null || !destination.getColor().equals(getColor());
    }
}
