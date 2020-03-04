package FieldForTTT;

import static java.lang.Math.max;

public class TicTacToe {
    public int size;
    private Symbols[][] field;
    int maxTic = 0;
    int maxTac = 0;
    //осознал что надо считать последовательности для крестиков и ноликов, а не просто самую большую

    public TicTacToe (int size) {
        this.size = size;
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

    public boolean liteTester(int row, int column) {
        return (row < size) && (column < size) && (row >= 0) && (column >= 0);
    }

    public boolean addSymbol(int row, int column, Symbols symbol) {
        boolean check = false;
        if (getCell(row,column) == Symbols.VOID){
            if (symbol == Symbols.X){
                field[row][column] = Symbols.X;
                check = true;
            }
            else if (symbol == Symbols.O) {
                field[row][column] = Symbols.O;
                check = true;
            }
        }
        return check;
    }

    public void clear(int row, int column) {
            field[row][column] = Symbols.VOID;
    }
    //разделил методы нахождения, а то так не очень удобно все в куче держать
    public void maximum(Symbols symbol, int countOfSymbol){
        if (symbol == Symbols.X)
            maxTic = max(maxTic, countOfSymbol);
        if (symbol == Symbols.O)
            maxTac = max(maxTac, countOfSymbol);
        countOfSymbol = 0;
    }

    public void findMaxVertical(Symbols symbol) {
        int row = 0;
        int column = 0;
        int countOfSymbol = 0;

        while (liteTester(row, column)) {
            while ((getCell(row, column) != Symbols.Error)&&(getCell(row, column) == symbol)) {
                row++;
                countOfSymbol++;
                if (row == size) {
                    column++;
                    row = 0;
                    break;
                    //нужно закончить цикл на случай когда в след столбце такой же символ как и в конце пред. столбца.
                }
            }

            while ((getCell(row, column) != Symbols.Error)&&(getCell(row, column)!= symbol)) {
                row++;
                if (row == size) {
                    row=0;
                    column++;
                }
            }
            maximum(symbol,countOfSymbol);
        }
    }
    public void findMaxHorizontal(Symbols symbol) {
        int row = 0;
        int column = 0;
        int countOfSymbol = 0;
        while (liteTester(row, column)) {
            while ((getCell(row, column) != Symbols.Error && field[row][column] == symbol)) {
                column++;
                countOfSymbol++;
                if (column == size) {
                    column = 0;
                    row++;
                    break;
                    //нужно закончить цикл на случай когда в след столбце такой же символ как и в конце пред. столбца.}
                }
            }
                while ((getCell(row, column) != Symbols.Error)&&(getCell(row, column) != symbol)) {
                    column++;
                    if (column == size) {
                        row++;
                        column = 0;
                    }
                }
            maximum(symbol,countOfSymbol);

        }
    }
    public enum Diagonal{
        left(),right();
    }
    /** enum и check позволяют описать 4 возможных состояния в одном методе
     * right check true - направление вправо вниз, считается нижняя часть
     * left check true - направление вправо вверх
     * right check false - направление влево вверх - верхняя
     * left check false - направление влево вниз
    **/
    public void findMaxDiagonal(Symbols symbol, boolean check, Diagonal diagonal) {
        int countOfSymbol = 0;
        //если check true, считаем диагонали нижнего куска
        //иначе счиатем диагонали верхнего
        for (int y = 0; y < size; y++) {
            // с фором удобнее двигаться по диагоналям, не нужно умно перемещаться обратно в зависимости от позиции
            // (умно через счетчик внутри цикла у меня не заработало и это было некрасиво)
            int column = 0;
            int row = y;
            if (!check)
                column = size - 1;//верхний кусок считаеем с правого верхнего угла
            while (liteTester(row, column)) {
                while ((getCell(row, column) != Symbols.Error && field[row][column] == symbol)) {
                    countOfSymbol++;
                    if (check) {
                        column++; //двигаемся вправо всегда при true
                        if (diagonal == Diagonal.right)
                            row++;//вниз
                        else if (diagonal == Diagonal.left)
                            row--;//вверх
                    } else {
                        column--;//влево в любом случае
                        if (diagonal == Diagonal.right)
                            row--;//вниз
                        else if (diagonal == Diagonal.left)
                            row++;//вверх
                    }
                }
                while ((getCell(row, column) != Symbols.Error && field[row][column] != symbol)) {
                    if (check) {
                        column++; //двигаемся вправо всегда при true
                        if (diagonal == Diagonal.right)
                            row++;//вниз
                        else if (diagonal == Diagonal.left)
                            row--;//вверх
                    } else {
                        column--;//влево в любом случае
                        if (diagonal == Diagonal.right)
                            row--;//вниз
                        else if (diagonal == Diagonal.left)
                            row++;//вверх
                    }
                }
            }
            maximum(symbol,countOfSymbol);
        }
    }

    public int findMaxLines(Symbols symbol) {
        if ((symbol == Symbols.X)||(symbol == Symbols.O)) {
            findMaxVertical(symbol);
            findMaxHorizontal(symbol);
            findMaxDiagonal(symbol,true, Diagonal.left);
            findMaxDiagonal(symbol,true, Diagonal.right);
            findMaxDiagonal(symbol,false, Diagonal.left);
            findMaxDiagonal(symbol,false, Diagonal.right);
        }else throw new IllegalArgumentException("Недопустимый символ");
        if (symbol == Symbols.X)
            return maxTic;
        else return maxTac;
    }
}
