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

        return true;
    }
}
