public abstract class Piece {
        private final String color; // "white" or "black"

        public Piece(String color) {
            this.color = color;
        }

        public String getColor() {
            return color;
        }

        public abstract String getSymbol();

    public abstract boolean isValidMove(Board board, int fromX, int fromY, int toX, int toY);
}

