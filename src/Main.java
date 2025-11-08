public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        System.out.println("\nWhite pawn go forward:");
        board.movePiece(0, 1, 0, 3); // okay
        board.printBoard();

        System.out.println("\ntry moving the pawn two more squares:");
        board.movePiece(0, 3, 0, 5); // error
        board.printBoard();

        System.out.println("\ntry moving the white rook from a1 to a3:");
        board.movePiece(0, 0, 0, 2); // допустимо, если путь свободен
        board.printBoard();

        System.out.println("\ntry moving the king from e1 to e2:");
        board.movePiece(4, 0, 4, 1);
        board.printBoard();

        System.out.println("\ntry moving the knight from b1 to c3:\"");
        board.movePiece(1, 0, 2, 2);
        board.printBoard();

    }
}
