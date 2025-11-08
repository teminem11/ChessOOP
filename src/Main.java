public class Main {
    public static void main(String[] args) {

        //board.printBoard();

        System.out.println("\nTesting CHECK detection:");
        Board board = new Board();

        // Simple scenario to test
        board.movePiece(4, 1, 4, 3); // White pawn e2 → e4
        board.movePiece(5, 6, 5, 4); // Black pawn f7 → f5
        board.movePiece(3, 0, 7, 4); // White queen d1 → h5 (Check!)
        board.printBoard();






    }
}
