import static org.junit.jupiter.api.Assertions.*;

import FieldForTTT.Symbols;
import FieldForTTT.TicTacToe;
import org.junit.jupiter.api.Test;
class TicTacToeTest {

    @Test
    void add() {
        TicTacToe fieldForTest = new TicTacToe(3);
        fieldForTest.addSymbol(1,1, Symbols.X);
        assertEquals(Symbols.X, fieldForTest.getCell(1,1));
        assertFalse(fieldForTest.addSymbol(4, 0, Symbols.X));
        fieldForTest.clear(1,1);
        fieldForTest.addSymbol(1,1,Symbols.O);
        assertEquals(Symbols.O, fieldForTest.getCell(1,1));
    }
    @Test
    void findMaxLine() {
        TicTacToe fieldForTest = new TicTacToe( 4);
        fieldForTest.addSymbol(0,0, Symbols.X);
        fieldForTest.addSymbol(0,1, Symbols.X);
        fieldForTest.addSymbol(0,2, Symbols.X);
        fieldForTest.addSymbol(0,3, Symbols.X);
        fieldForTest.addSymbol(1,0,Symbols.O);
        fieldForTest.addSymbol(2,0,Symbols.O);
        assertEquals(4,fieldForTest.findMaxLines(Symbols.X));
        assertEquals(2,fieldForTest.findMaxLines(Symbols.O));
        fieldForTest.addSymbol(1,1, Symbols.O);
        assertEquals(2,fieldForTest.findMaxLines(Symbols.O));
        fieldForTest.clear(0,0);
        assertEquals(3,fieldForTest.findMaxLines(Symbols.X));
        fieldForTest.clearAll(TicTacToe.size);
        assertEquals(0,fieldForTest.findMaxLines(Symbols.O));
    }
    @Test
    void FindMaxDiagonal(){
        TicTacToe fieldForTest = new TicTacToe( 4);
        fieldForTest.addSymbol(0,0, Symbols.O);
        fieldForTest.addSymbol(1,1, Symbols.X);
        fieldForTest.addSymbol(2,2, Symbols.O);
        fieldForTest.addSymbol(3,3, Symbols.O);
        assertEquals(1,fieldForTest.findMaxLines(Symbols.X));
        assertEquals(2,fieldForTest.findMaxLines(Symbols.O));
        fieldForTest.clearAll(TicTacToe.size);
        assertEquals(0,fieldForTest.findMaxLines(Symbols.O));
        fieldForTest.addSymbol(3,0,Symbols.X);
        fieldForTest.addSymbol(2,1,Symbols.X);
        fieldForTest.addSymbol(1,2,Symbols.X);
        fieldForTest.addSymbol(0,3,Symbols.X);
        assertEquals(4,fieldForTest.findMaxLines(Symbols.X));
        fieldForTest.clearAll(TicTacToe.size);
    }
    @Test
    void anotherFindMaxDiagonal(){
            TicTacToe fieldForTest = new TicTacToe( 7);
            fieldForTest.addSymbol(0,0, Symbols.X);
            fieldForTest.addSymbol(1,1, Symbols.X);
            fieldForTest.addSymbol(2,2, Symbols.X);
            fieldForTest.addSymbol(3,3, Symbols.O);
            fieldForTest.addSymbol(4,4, Symbols.O);
            fieldForTest.addSymbol(5,5, Symbols.X);
            fieldForTest.addSymbol(6,6, Symbols.X);
            assertEquals(3,fieldForTest.findMaxLines(Symbols.X));
            assertEquals(2,fieldForTest.findMaxLines(Symbols.O));
    }
    @Test
    void symbolCheck(){
        TicTacToe fieldForTest = new TicTacToe(3);
        fieldForTest.addSymbol(1,1, Symbols.X);
        assertThrows(IllegalArgumentException.class,()->fieldForTest.findMaxLines(Symbols.VOID));
        assertThrows(IllegalArgumentException.class,()->fieldForTest.findMaxLines(Symbols.Error));
    }
    @Test
    void anotherFindMaxLine(){
        TicTacToe fieldForTest = new TicTacToe( 7);
        fieldForTest.addSymbol(0,0, Symbols.X);
        fieldForTest.addSymbol(0,1, Symbols.X);
        fieldForTest.addSymbol(0,2, Symbols.X);
        fieldForTest.addSymbol(0,3, Symbols.O);
        fieldForTest.addSymbol(0,4, Symbols.O);
        fieldForTest.addSymbol(0,5, Symbols.X);
        fieldForTest.addSymbol(0,6, Symbols.X);
        assertEquals(3,fieldForTest.findMaxLines(Symbols.X));
        assertEquals(2,fieldForTest.findMaxLines(Symbols.O));
        fieldForTest.addSymbol(1,4, Symbols.O);
        fieldForTest.addSymbol(2,5, Symbols.O);
        assertEquals(3,fieldForTest.findMaxLines(Symbols.O));
    }
    @Test
    void equalsTest(){
        TicTacToe fieldForTest = new TicTacToe(3);
        TicTacToe fieldForEquals = new TicTacToe(3);
        fieldForTest.addSymbol(0,0, Symbols.X);
        assertNotEquals(fieldForTest,fieldForEquals);
        fieldForEquals.addSymbol(0,0, Symbols.X);
        assertEquals(fieldForTest,fieldForEquals);
    }
    @Test
    void toStringTest(){
        TicTacToe fieldForString = new TicTacToe(2);
        fieldForString.addSymbol(0,0, Symbols.X);
        assertEquals("X "+"\n"+"  ",fieldForString.toString());
    }
    @Test
    void hashCodeTest(){
        TicTacToe hashTestFirst = new TicTacToe(2);
        hashTestFirst.addSymbol(0,0,Symbols.X);
        TicTacToe hashTestSecond = new TicTacToe(2);
        assertNotEquals(hashTestFirst.hashCode(),hashTestSecond.hashCode());
        hashTestSecond.addSymbol(0,0,Symbols.X);
        assertEquals(hashTestFirst.hashCode(),hashTestSecond.hashCode());
    }
}