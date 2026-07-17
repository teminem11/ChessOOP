import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class BoardTest {
 @Test void alternatesTurnsAndRejectsIllegalMoves(){Board b=new Board();assertFalse(b.movePiece(4,1,4,4));assertEquals("white",b.getCurrentTurn());assertTrue(b.movePiece(4,1,4,3));assertEquals("black",b.getCurrentTurn());}
 @Test void detectsFoolsMate(){Board b=new Board();b.movePiece(5,1,5,2);b.movePiece(4,6,4,4);b.movePiece(6,1,6,3);b.movePiece(3,7,7,3);assertTrue(b.isCheckmate("white"));}
 @Test void validatesCoordinates(){Board b=new Board();assertThrows(IllegalArgumentException.class,()->b.getSquare(8,0));}
}
