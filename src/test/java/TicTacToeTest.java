import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TicTacToeTest {

    @Test
    void add() {
        TicTacToe fieldForTest = new TicTacToe(3);
        fieldForTest.addTic(1,1);
        assertEquals(TicTacToe.tic, fieldForTest.getCell(1,1));
        assertThrows(IllegalArgumentException.class,()-> fieldForTest.addTic(4,0));
        assertThrows(IllegalArgumentException.class,()-> fieldForTest.addTic(1,1));
        assertThrows(IllegalArgumentException.class,()-> fieldForTest.addTac(1,1));
        fieldForTest.addToe(1,1);
        fieldForTest.addTac(1,1);
        assertEquals(TicTacToe.tac, fieldForTest.getCell(1,1));
    }

    @Test
    void findMaxLines() {
        TicTacToe fieldForTest = new TicTacToe( 4);
        fieldForTest.addTic(0,0);
        fieldForTest.addTic(0,1);
        fieldForTest.addTic(0,2);
        fieldForTest.addTic(0,3);
        assertEquals(4,fieldForTest.findMaxLines(TicTacToe.tic));
        assertEquals(0,fieldForTest.findMaxLines(TicTacToe.tac));
        fieldForTest.addTac(1,1);
        assertEquals(1,fieldForTest.findMaxLines(TicTacToe.tac));
        assertThrows(IllegalArgumentException.class,()->fieldForTest.findMaxLines(TicTacToe.toe));
    }
}