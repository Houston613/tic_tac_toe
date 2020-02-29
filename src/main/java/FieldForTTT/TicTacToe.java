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

    public void Clear(int row, int column) {
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
                while (getCell(row,column)!=Symbols.Error && getCell(row,column) == symbol) {
                    row++;
                    countOfSymbol++;
                    if (row == size) {
                        row = 0;
                        column++;
                        //дополнительная проверка для перехода на след стобик
                    }
                }
                while (getCell(row,column)!=Symbols.Error && getCell(row,column) != symbol) {
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
                    while ((getCell(row,column)!=Symbols.Error && getCell(row,column) == symbol)) {
                        column++;
                        countOfSymbol++;
                        if (column == size) {
                            row++;
                            column=0;
                            //дополнительная проверка для перехода на след стобик
                        }
                    }
                    while ((getCell(row,column)!=Symbols.Error && getCell(row,column) != symbol)) {
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
                while ((getCell(row,column)!=Symbols.Error && getCell(row,column) == symbol)) {
                    row++;
                    column++;
                    countOfSymbol++;
                }
                while ((getCell(row,column)!=Symbols.Error && getCell(row,column) != symbol)) {
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
                while ((getCell(row,column)!=Symbols.Error && getCell(row,column) == symbol)) {
                    row++;
                    column--;
                    countOfSymbol++;
                }
                while ((getCell(row,column)!=Symbols.Error && getCell(row,column) != symbol)) {
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
