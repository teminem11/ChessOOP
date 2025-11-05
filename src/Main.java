public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        System.out.println("\nWhite pawn go forward:");
        board.movePiece(0, 1, 0, 3); // okay
        board.printBoard();

        System.out.println("\nLet's try moving the pawn two more squares:");
        board.movePiece(0, 3, 0, 5); // error
        board.printBoard();
    }
}
