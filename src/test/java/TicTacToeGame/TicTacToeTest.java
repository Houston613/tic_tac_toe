package TicTacToeGame;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class TicTacToeTest {

    @Test
    void add() {
        TicTacToe fieldForTest = new TicTacToe(3);
        fieldForTest.addSymbol(1,1,TicTacToe.Symbols.X);
        assertEquals(TicTacToe.Symbols.X, fieldForTest.getCell(1,1));
        assertFalse(fieldForTest.addSymbol(4, 0, TicTacToe.Symbols.X));
        fieldForTest.clear(1,1);
        fieldForTest.addSymbol(1,1,TicTacToe.Symbols.O);
        assertEquals(TicTacToe.Symbols.O, fieldForTest.getCell(1,1));
    }
    @Test
    void findMaxLine() {
        TicTacToe fieldForTest = new TicTacToe( 4);
        fieldForTest.addSymbol(0,0, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,1, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,2, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,3, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(1,0,TicTacToe.Symbols.O);
        fieldForTest.addSymbol(2,0,TicTacToe.Symbols.O);
        assertEquals(4,fieldForTest.findMaxLines(TicTacToe.Symbols.X));
        assertEquals(2,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
        fieldForTest.addSymbol(1,1, TicTacToe.Symbols.O);
        assertEquals(2,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
        assertTrue(fieldForTest.clear(0, 0));
        assertFalse(fieldForTest.clear(-1, 0));
        assertEquals(3,fieldForTest.findMaxLines(TicTacToe.Symbols.X));
        fieldForTest.clearAll();
        assertEquals(0,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
    }
    @Test
    void FindMaxDiagonal(){
        TicTacToe fieldForTest = new TicTacToe( 4);
        fieldForTest.addSymbol(0,0, TicTacToe.Symbols.O);
        fieldForTest.addSymbol(1,1, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(2,2, TicTacToe.Symbols.O);
        fieldForTest.addSymbol(3,3, TicTacToe.Symbols.O);
        assertEquals(1,fieldForTest.findMaxLines(TicTacToe.Symbols.X));
        assertEquals(2,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
        fieldForTest.clearAll();
        assertEquals(0,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
        fieldForTest.addSymbol(3,0,TicTacToe.Symbols.X);
        fieldForTest.addSymbol(2,1, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(1,2,TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,3,TicTacToe.Symbols.X);
        assertEquals(4,fieldForTest.findMaxLines(TicTacToe.Symbols.X));
        fieldForTest.clearAll();
    }
    @Test
    void anotherFindMaxDiagonal(){
            TicTacToe fieldForTest = new TicTacToe( 7);
            fieldForTest.addSymbol(0,0, TicTacToe.Symbols.X);
            fieldForTest.addSymbol(1,1, TicTacToe.Symbols.X);
            fieldForTest.addSymbol(2,2, TicTacToe.Symbols.X);
            fieldForTest.addSymbol(3,3, TicTacToe.Symbols.O);
            fieldForTest.addSymbol(4,4, TicTacToe.Symbols.O);
            fieldForTest.addSymbol(5,5, TicTacToe.Symbols.X);
            fieldForTest.addSymbol(6,6, TicTacToe.Symbols.X);
            assertEquals(3,fieldForTest.findMaxLines(TicTacToe.Symbols.X));
            assertEquals(2,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
    }
    @Test
    void symbolCheck(){
        TicTacToe fieldForTest = new TicTacToe(3);
        fieldForTest.addSymbol(1,1, TicTacToe.Symbols.X);
        assertThrows(IllegalArgumentException.class,()->fieldForTest.findMaxLines(TicTacToe.Symbols.VOID));
        assertThrows(IllegalArgumentException.class,()->fieldForTest.findMaxLines(TicTacToe.Symbols.Error));
    }
    @Test
    void anotherFindMaxLine(){
        TicTacToe fieldForTest = new TicTacToe( 7);
        fieldForTest.addSymbol(0,0, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,1, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,2, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,3, TicTacToe.Symbols.O);
        fieldForTest.addSymbol(0,4, TicTacToe.Symbols.O);
        fieldForTest.addSymbol(0,5, TicTacToe.Symbols.X);
        fieldForTest.addSymbol(0,6, TicTacToe.Symbols.X);
        assertEquals(3,fieldForTest.findMaxLines(TicTacToe.Symbols.X));
        assertEquals(2,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
        fieldForTest.addSymbol(1,4, TicTacToe.Symbols.O);
        fieldForTest.addSymbol(2,5, TicTacToe.Symbols.O);
        assertEquals(3,fieldForTest.findMaxLines(TicTacToe.Symbols.O));
    }
    @Test
    void equalsTest(){
        TicTacToe fieldForTest = new TicTacToe(3);
        TicTacToe fieldForEquals = new TicTacToe(3);
        fieldForTest.addSymbol(0,0, TicTacToe.Symbols.X);
        assertNotEquals(fieldForTest,fieldForEquals);
        fieldForEquals.addSymbol(0,0, TicTacToe.Symbols.X);
        assertEquals(fieldForTest,fieldForEquals);
    }
    @Test
    void toStringTest(){
        TicTacToe fieldForString = new TicTacToe(2);
        fieldForString.addSymbol(0,0, TicTacToe.Symbols.X);
        assertEquals("X "+System.lineSeparator()+"  ",fieldForString.toString());
    }
    @Test
    void hashCodeTest(){
        TicTacToe hashTestFirst = new TicTacToe(2);
        hashTestFirst.addSymbol(0,0,TicTacToe.Symbols.X);
        TicTacToe hashTestSecond = new TicTacToe(2);
        assertNotEquals(hashTestFirst.hashCode(),hashTestSecond.hashCode());
        hashTestSecond.addSymbol(0,0,TicTacToe.Symbols.X);
        assertEquals(hashTestFirst.hashCode(),hashTestSecond.hashCode());
    }
}