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
        return true;
    }
}
