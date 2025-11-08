public class Main {
    public static void main(String[] args) {
        System.out.println("\nTesting self-check prevention:");

        Board board = new Board();

        // e2→e4
        board.movePiece(4, 1, 4, 3);
        // e7→e5
        board.movePiece(4, 6, 4, 4);
        // f2→f3
        board.movePiece(5, 1, 5, 2);
        // Qd8→h4 (шах)
        board.movePiece(3, 7, 7, 3);

        // Bad move: a2→a3 (does not remove the check) — must be rejected
        board.movePiece(0, 1, 0, 2);

        // Good move: g2→g3 (blocks the diagonal) — must pass
        board.movePiece(6, 1, 6, 2);

        // Alternative: g1→f3 (also removes the check) — must pass
        board.movePiece(6, 0, 5, 2);


    }
}
