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


        System.out.println("\nChecking a pawn capture:");
        board.movePiece(4, 1, 4, 3); // White pawn e2 → e4
        board.movePiece(3, 6, 3, 4); // black pawn d7 → d5
        board.printBoard();

        System.out.println("\nThe white pawn captures the black pawn diagonally:");
        board.movePiece(4, 3, 3, 4); // White pawn e4 → d5 (capture)
        board.printBoard();


    }
}
