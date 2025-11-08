public class Queen extends Piece {
    public Queen(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "Q" : "q";
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        int dx = Math.abs(toX - fromX);
        int dy = Math.abs(toY - fromY);

        // The queen can move like a rook (straight) or a bishop (diagonal)
        boolean straight = (fromX == toX || fromY == toY);
        boolean diagonal = (dx == dy);

        if (!straight && !diagonal) {
            return false; // Invalid direction for a queen
        }

        // Determine direction of movement
        int stepX = Integer.compare(toX, fromX);
        int stepY = Integer.compare(toY, fromY);

        // Check for obstacles between from and to
        int x = fromX + stepX;
        int y = fromY + stepY;
        while (x != toX || y != toY) {
            if (board.getSquare(x, y).getPiece() != null) {
                return false; // Path is blocked
            }
            x += stepX;
            y += stepY;
        }

        // The destination must be empty or occupied by an enemy piece
        Piece destination = board.getSquare(toX, toY).getPiece();
        return destination == null || !destination.getColor().equals(getColor());
    }
}
