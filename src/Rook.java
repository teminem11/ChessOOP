public class Rook extends Piece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "R" : "r";
    }

    @Override
    public boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY) {
        // The move must be vertical or horizontal.
        if (fromX != toX && fromY != toY) {
            return false;
        }

        // Check that there are no shapes between from and to
        int stepX = Integer.compare(toX, fromX); // -1, 0, 1
        int stepY = Integer.compare(toY, fromY);

        int x = fromX + stepX;
        int y = fromY + stepY;

        while (x != toX || y != toY) {
            if (board.getSquare(x, y).getPiece() != null) {
                return false;
            }
            x += stepX;
            y += stepY;
        }

        // The last cell may be empty or contain an opponent's piece.
        Piece destination = board.getSquare(toX, toY).getPiece();
        return destination == null || !destination.getColor().equals(getColor());
    }
}
