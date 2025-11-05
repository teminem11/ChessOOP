public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        System.out.println("\nMove the white pawn forward\n");
        board.movePiece(0, 1, 0, 3); // Pawn from a2 to a4

        board.printBoard();
    }
}
