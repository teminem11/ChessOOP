public class Pawn extends Piece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return getColor().equals("white") ? "P" : "p";
    }
}
