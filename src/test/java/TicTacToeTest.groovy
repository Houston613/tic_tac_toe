import org.junit.Test
import static org.junit.Assert.*
class TicTacToeTest extends TicTacToe {
    TicTacToeTest(int size) {
        super(size)
    }
    @Test
    void add(){
        TicTacToe fieldForTest = new TicTacToe(5);
        fieldForTest.addTic(3,3);
        fieldForTest.addTac(4,6)
        assertEquals(tic,fieldForTest.getCell(3,3)) }
}
