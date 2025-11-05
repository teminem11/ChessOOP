public class Board {
    private final Square[][] squares = new Square[8][8];

    public Board() {
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                squares[y][x] = new Square(x, y);
            }
        }
    }

    public Square getSquare(int x, int y) {
        return squares[y][x];
    }

    public void printBoard() {
        for (int y = 7; y >= 0; y--) {
            for (int x = 0; x < 8; x++) {
                System.out.print(squares[y][x] + " ");
            }
            System.out.println();
        }
    }
}
