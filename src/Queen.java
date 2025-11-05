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
        return true;
    }
}
