public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        System.out.println("\nTesting turn order:");

        board.movePiece(4, 1, 4, 3); // White pawn e2 → e4 ✅
        board.movePiece(3, 6, 3, 4); // Black pawn d7 → d5 ✅
        board.movePiece(4, 3, 3, 4); // White pawn captures d5 ✅
        board.movePiece(2, 6, 2, 5); // Black pawn c7 → c6 ✅





    }
}
