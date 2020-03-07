package FieldForTTT;

import java.util.Arrays;

import static java.lang.Math.max;

public class TicTacToe {
    public static int size;
    private Symbols[][] field;
    int maxTic = 0;
    int maxTac = 0;
    int x;
    int y;
    //осознал что надо считать последовательности для крестиков и ноликов, а не просто самую большую

    public TicTacToe (int size) {
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

    public boolean liteTester(int row, int column) {
        return (row < size) && (column < size) && (row >= 0) && (column >= 0);
    }

    //тк вы сказали, что надо думатб что мы делаем класс для проекта, добавлю подсчет символов после добавления
    //ведь в настоящей игре делать надо именно это
    //можно не проверять все поле, а только по той вертикали/горизонтали/диагонали на которой находится клетка

    public boolean addSymbol(int row, int column, Symbols symbol) {
        boolean add = false;
        if (getCell(row,column) == Symbols.VOID){
            if (symbol == Symbols.X){
                field[row][column] = Symbols.X;
                add = true;
                x=column;
                y=row;
                findMaxLine(symbol,false, true);
                findMaxLine(symbol,true,false);
            }

            else if (symbol == Symbols.O) {
                field[row][column] = Symbols.O;
                add = true;
            }
        }
        return add;
    }

    public void clear(int row, int column) {
            field[row][column] = Symbols.VOID;
            maxTic = 0;
            maxTac = 0;
    }

    public void clearAll(int size){
        maxTac = 0;
        maxTic = 0;
        TicTacToe.size = size;
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                clear(row,column);
    }

    //разделил методы нахождения, а то так не очень удобно все в куче держать

    public void maximum(Symbols symbol, int countOfSymbol){
        if (symbol == Symbols.X)
            maxTic = max(maxTic, countOfSymbol);
        if (symbol == Symbols.O)
            maxTac = max(maxTac, countOfSymbol);
    }

    public void findMaxLine(Symbols symbol, boolean check, boolean forAdd) {
        //чек true для горизонтальных линий, иначе для вертикальных
        //условие line для того чтобы в add использовать методы нахождения макс длинн
        //если true значит идем по фулл полю, иначе по стрке/линии/диагонали
        int row = 0;
        int column = 0;
        int countOfSymbol = 0;
        if ((forAdd) && (!check))
            column = x;
        //если это условие в add и ищем
        else if ((check)&& (forAdd))
            row = y;

        while (liteTester(row, column)) {
            while (getCell(row, column) != Symbols.Error && (getCell(row, column) == symbol)) {
                if (check) {
                    column++;
                    countOfSymbol++;
                    if ((column == size) && (!forAdd)) {
                        column = 0;
                        row++;
                        break;
                    } else if (forAdd)//если для add, то просто заканчиваем
                        break;
                } else {
                    row++;
                    countOfSymbol++;
                    if ((row == size) && (!forAdd)) {
                        column++;
                        row = 0;
                        break;
                    } else if (forAdd)
                        break;
                }
            }
            while ((getCell(row, column) != Symbols.Error) && (getCell(row, column) != symbol)) {
                if (check) {
                    column++;
                    if ((column == size) && (!forAdd)) {
                        row++;
                        column = 0;
                        break;
                    }else if (forAdd)
                        break;
                } else {
                    row++;
                    if ((row == size) && (!forAdd)) {
                        row = 0;
                        column++;
                        break;
                    }else if (forAdd)
                        break;
                }
            }
            maximum(symbol, countOfSymbol);
            countOfSymbol = 0;

        }
    }

    public enum Diagonal{
        left(),right()
    }
    /** enum и check позволяют описать 4 возможных состояния в одном методе
     * right check true - направление вправо вниз, считается нижняя часть
     * left check true - направление вправо вверх
     * right check false - направление влево вверх - верхняя
     * left check false - направление влево вниз
    **/
    public void findMaxDiagonal(Symbols symbol, boolean check, Diagonal diagonal) {
        int countOfSymbol = 0;
        int i = 0;
        //если check true, считаем диагонали нижнего куска
        //иначе счиатем диагонали верхнего
        while (i <= size) {//зачем=то выдумывал счетчик какой-то, если можно просто переменную сделать
            int column = 0;
            int row = i;
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
                maximum(symbol,countOfSymbol);
                countOfSymbol = 0;
            }
            i++;
        }
    }

    public int findMaxLines(Symbols symbol) {
        if ((symbol == Symbols.X)||(symbol == Symbols.O)) {
            findMaxLine(symbol,true, false);
            findMaxLine(symbol,false, false);
            findMaxDiagonal(symbol,true, Diagonal.left);
            findMaxDiagonal(symbol,true, Diagonal.right);
            findMaxDiagonal(symbol,false, Diagonal.left);
            findMaxDiagonal(symbol,false, Diagonal.right);
        }else throw new IllegalArgumentException("Недопустимый символ");
        if (symbol == Symbols.X)
            return maxTic;
        else return maxTac;
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
                result.append("\n");
        }
        return result.toString();
    }
}
