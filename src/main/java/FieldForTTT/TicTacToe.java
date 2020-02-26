package FieldForTTT;

import static java.lang.Math.max;

public class TicTacToe {
    public int size;//имеется размер не меняющийся
    private Symbols[][] field;

    public TicTacToe (int size) {//конструктор игрового поля
        this.size = size;
        field = new Symbols[size][size];
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                field[row][column] = Symbols.VOID ;
    }

    public Symbols getCell(int row, int column){
        tester(row, column);//в случае ошибки тестер кинет ошибку и все "сломается как нужно"
        return field[row][column];
    }

    public boolean tester(int row, int column) {
        if ((row < size) && (column < size) && (row >= 0) && (column >= 0))
            return true;
        else throw new IllegalArgumentException("Вас поймали за пересечение границы");
    }
    public boolean liteTester(int row, int column) {
        return (row < size) && (column < size) && (row >= 0) && (column >= 0);
    }

   // не понимаю, как иначе засунуть throw в тестер, тк тогда он ломает циклы

    public void addSymbol(int row, int column, Symbols symbol) {
        tester(row, column);
            if ((symbol == Symbols.X) && (field[row][column] == Symbols.VOID))
                field[row][column] = Symbols.X;
            if ((symbol == Symbols.O) && (field[row][column] == Symbols.VOID))
                field[row][column] = Symbols.O;
            //теперь если клетка не пустая то просто ничего не произойдет. надеюсь правильно понял
    }

    public void Clear(int row, int column) {
        if (tester(row, column))
            field[row][column] = Symbols.VOID;
        }

    public int findMaxLines(Symbols symbol) {
        int maxHorizontal = 0;
        int maxVertical = 0;
        int maxDiagonalA = 0;
        int maxDiagonalB = 0;
        //выбираем символ, считаем для него максимальные линии
        if ((symbol == Symbols.X)||(symbol == Symbols.O)) {
            int countOfSymbol = 0;//комбинация символов
            int row = 0;//нужно для прохода цикла по строке
            int column = 0;//нужно для прохода цикла по столбцу
            while (liteTester(row,column)) {
                while (liteTester(row,column) && field[row][column] == symbol) {
                    row++;
                    countOfSymbol++;
                    if (row == size) {
                        row = 0;
                        column++;
                        //дополнительная проверка для перехода на след стобик
                    }
                }
                while (liteTester(row,column) && field[row][column] != symbol) {
                    row++;
                    if (row == size) {
                        row = 0;
                        column++;
                        //дополнительная проверка для перехода на некст строку
                    }
                }
                maxHorizontal = max(countOfSymbol, maxHorizontal);
                countOfSymbol = 0;
            }

            row = 0;
            column = 0;

                while (liteTester(row,column)) {
                    while (liteTester(row,column) && field[row][column] == symbol) {
                        column++;
                        countOfSymbol++;
                        if (column == size) {
                            row++;
                            column=0;
                            //дополнительная проверка для перехода на след стобик
                        }
                    }
                    while (liteTester(row,column) && field[row][column] != symbol) {
                        column++;
                        if (column == size) {
                            row++;
                            column = 0;
                            //дополнительная проверка для перехода на след стобик
                        }
                    }
                    maxVertical = max(countOfSymbol, maxVertical);
                    countOfSymbol = 0;
                }

            row = 0;
            column = 0;

            while (liteTester(row,column)) {
                while (liteTester(row,column) && field[row][column] == symbol) {
                    row++;
                    column++;
                    countOfSymbol++;
                }
                while (liteTester(row,column) && field[row][column] != symbol) {
                    row++;
                    column++;
                }
                 maxDiagonalA = max(countOfSymbol, maxDiagonalA);
                countOfSymbol = 0;
            }

            row = 0;
            column = size - 1;
            //для подсчета диагонали с нижнего левого угла

            while (liteTester(row,column)) {
                while (liteTester(row,column) && field[row][column] == symbol) {
                    row++;
                    column--;
                    countOfSymbol++;
                }
                while (liteTester(row,column) && field[row][column] != symbol) {
                    row++;
                    column--;
                }
                maxDiagonalB = max(countOfSymbol, maxDiagonalB);
                countOfSymbol = 0;
            }
            return max(max(maxHorizontal,maxVertical), max(maxDiagonalA,maxDiagonalB));
        }
        else throw new IllegalArgumentException("Недопустимый символ");
    }
}
