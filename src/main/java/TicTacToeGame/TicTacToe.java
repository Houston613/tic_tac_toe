package TicTacToeGame;

import static java.lang.Math.max;

public class TicTacToe {
    private static int size;
    private Symbols[][] field;
    private int maxTic = 0;
    private int maxTac = 0;
    private int x;
    private int y;
    private int height;
    private int width;

    public enum Symbols {
        X, O, VOID, Error
    }

    public TicTacToe (int size) {
        if (size<=0)
            throw new IllegalArgumentException("Размер поля не может быть отрицательным");
        TicTacToe.size = size;
        field = new Symbols[size][size];
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                field[row][column] = Symbols.VOID ;
    }

    public Symbols getCell(int row, int column){
        if (liteTester(row, column))
            return field[row][column];
        else return Symbols.Error;
    }

    private boolean liteTester(int row, int column) {
        return (row < size) && (column < size) && (row >= 0) && (column >= 0);
    }

    private void findForAdd(Symbols symbol, int row, int column ){
        x = column;
        y = row;
        findMaxLine(symbol,Line.horizontal, true);
        findMaxLine(symbol,Line.vertical,true);
        findMaxDiagonal(symbol,Line.leftLow,true);
        findMaxDiagonal(symbol,Line.leftUp,true);
        findMaxDiagonal(symbol,Line.rightUp,true);
        findMaxDiagonal(symbol,Line.rightLow,true);
    }

    public boolean addSymbol(int row, int column, Symbols symbol) {
        boolean add = false;
        if (getCell(row,column) == Symbols.VOID){
            add = true;
            if (symbol == Symbols.X){
                field[row][column] = Symbols.X;
                findForAdd(symbol,row,column);
            }
            else if (symbol == Symbols.O) {
                field[row][column] = Symbols.O;
                findForAdd(symbol,row,column);
            }
        }
        return add;
    }

    public boolean clear(int row, int column) {
        if (getCell(row, column)!= Symbols.Error) {
            field[row][column] = Symbols.VOID;
            maxTic = 0;
            maxTac = 0;
            findMaxLinesAll(Symbols.X);
            findMaxLinesAll(Symbols.O);
            return true;
        }
        return false;
    }

    public void clearAll(){
        maxTac = 0;
        maxTic = 0;
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                field[row][column] = Symbols.VOID;
        findMaxLinesAll(Symbols.X);
        findMaxLinesAll(Symbols.O);
    }

    private void maximum(Symbols symbol, int countOfSymbol){
        if (symbol == Symbols.X)
            maxTic = max(maxTic, countOfSymbol);
        if (symbol == Symbols.O)
            maxTac = max(maxTac, countOfSymbol);
    }

    private void changer(Line line) {
        height += line.h;
        width += line.w;
    }

    private void findMaxLine(Symbols symbol, Line line, boolean forAdd) {
        height= 0;
        width = 0;
        int countOfSymbol = 0;
        if ((forAdd) && (line == Line.vertical))
            width = x;
        else if ((line == Line.horizontal) && (forAdd))
            height = y;

        while (liteTester(height, width)) {
                if (getCell(height, width) == symbol)
                    countOfSymbol++;
                else if ((getCell(height, width) != symbol)&&(getCell(height, width) != Symbols.Error)) {
                    maximum(symbol, countOfSymbol);
                    countOfSymbol = 0;
                }
                changer(line);
                if ((width == size) && (!forAdd)) {//условие перехода на новую строку
                    width = 0;
                    height++;
                } else if ((height == size) && (!forAdd)) {//условие перехода на новую строку
                    width++;
                    height = 0;
                }
            maximum(symbol, countOfSymbol);
        }
    }

    public enum Line{
        horizontal(0,1),vertical(1,0),
        leftUp(-1,1),leftLow(1,-1),rightUp(-1,-1),rightLow(1,1);
        private int h;//аналог row
        private int w;//аналог column
        Line(int h, int w){
            this.h = h;
            this.w = w;
        }
    }
    /** enum и check позволяют описать 4 возможных состояния в одном методе
     * right check true - направление вправо вниз, считается нижняя часть
     * left check false - направление влево вниз
     * right check false - направление влево вверх - верхняя
     * left check true - направление вправо вверх
     **/
    private void findMaxDiagonal(Symbols symbol, Line diagonal,boolean forAdd) {
        int countOfSymbol = 0;
        int i = 0;
        //если check true, считаем диагонали нижнего куска
        //иначе счиатем диагонали верхнего
        while (i <= size) {
            width = 0;
            height  = i;
            if ((diagonal == Line.leftLow)||(diagonal == Line.rightUp))
                width = size - 1;//верхний кусок считаеем с правого верхнего угла
            if (forAdd) {
                width = x;
                height = y;
                if (diagonal == Line.leftUp)
                    while ((height < size - 1) && (width > 0)) {
                        changer(Line.leftLow);
                    }
                else if (diagonal == Line.leftLow)
                    while ((width < size - 1) && (height > 0)) {
                        changer(Line.leftUp);
                    }
                else if (diagonal == Line.rightUp)
                    while ((width < size - 1) && (width < size - 1)) {
                        changer(Line.rightLow);
                    }
                else if (diagonal == Line.rightLow)
                    while ((width > 0) && (height > 0)) {
                        changer(Line.rightUp);
                    }
            }
                while (liteTester(height, width)) {
                        if (getCell(height, width) == symbol)
                            countOfSymbol++;
                        else if ((getCell(height, width) != symbol) && (getCell(height, width) != Symbols.Error)) {
                            maximum(symbol, countOfSymbol);
                            countOfSymbol = 0;
                        }
                        changer(diagonal);
                    maximum(symbol, countOfSymbol);
                }
                if (forAdd)
                    break;
            i++;
        }
    }

    public int findMaxLines(Symbols symbol) {
        if ((symbol == Symbols.X)||(symbol == Symbols.O)) {
            if (symbol == Symbols.X)
                return maxTic;
            else return maxTac;
        }
        else throw new IllegalArgumentException("Недопустимый символ");
    }

    private void findMaxLinesAll(Symbols symbol) {
        if ((symbol == Symbols.X)||(symbol == Symbols.O)) {
            findMaxLine(symbol,Line.vertical, false);
            findMaxLine(symbol,Line.horizontal, false);
            findMaxDiagonal(symbol,Line.leftLow,false);
            findMaxDiagonal(symbol,Line.leftUp,false);
            findMaxDiagonal(symbol,Line.rightUp,false);
            findMaxDiagonal(symbol,Line.rightLow,false);
        } else throw new IllegalArgumentException("Недопустимый символ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicTacToe ttt = (TicTacToe) o;
        //создала идея
        int check = 0;
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                if (ttt.getCell(row, column).equals(this.getCell(row, column)))
                    check++;
        return check == (size * size);
    }

    @Override
    public int hashCode() {
        int result = 0;
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                switch (getCell(row, column)) {
                    case X:
                        result+= row*column + 88;
                        break;
                    case O:
                        result+= row*column +48;
                        break;
                    case VOID:
                        result+= row*column +32;
                        break;
                }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++)
                switch (getCell(row, column)) {
                    case X:
                        result.append("X");
                        break;
                    case O:
                        result.append("O");
                        break;
                    case VOID:
                        result.append(" ");
                        break;
                }
            if (row!=size-1)
                result.append(System.lineSeparator());
        }
        return result.toString();
    }
}