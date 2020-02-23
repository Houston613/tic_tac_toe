public class TicTacToe {
    public int size;//имеется размер не меняющийся
    public char[][] field;
    public static final char tic = 'x';
    public static final char tac = 'o';
    public static final char toe = ' ';
    public TicTacToe (int size) {//конструктор игрового поля
        this.size = size;
        field = new char[size][size];
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                field[row][column] = toe;
    }

    public char getCell(int row, int column){
        if (tester(row, column))
            return field[row][column];
        else throw  new IllegalArgumentException("вы вышли за границы поля");
    }
    public boolean tester(int row, int column) {
        return (row < size) && (column < size) && (row >= 0) && (column >= 0);
    }

    public void addTic(int row, int column) {
        if (tester(row, column)){
            if (field[row][column] == toe) {
                field[row][column] = tic;
            } else
                throw new IllegalArgumentException("поле занято");
        } else throw new IllegalArgumentException("вы вышли за границы");
    }

    public void addTac(int row, int column) {
        if (tester(row, column)){
            if (field[row][column] == toe) {
                field[row][column] = tac;
            } else
                throw new IllegalArgumentException("поле занято");
        } else throw new IllegalArgumentException("вы вышли за границы");
    }

    public void addToe(int row, int column) {
        if (tester(row, column)) {
            field[row][column] = toe;
        }else throw new IllegalArgumentException("вы вышли за границы");
    }

    public int findMaxLines(char symbol) {
        int maxHorizontal = 0;
        int maxVertical = 0;
        int maxDiagonalA = 0;
        int maxDiagonalB = 0;
        //выбираем символ, считаем для него максимальные линии
        if ((symbol== tic)||(symbol== tac)) {
            int countOfSymbol = 0;//комбинация символов
            int row = 0;//нужно для прохода цикла по строке
            int column = 0;//нужно для прохода цикла по столбцу
            while (tester(row,column)) {
                while (tester(row,column)&&(getCell(row, column) == symbol)) {
                    row++;
                    countOfSymbol++;
                    if (row == size) {
                        row = 0;
                        column++;
                        //дополнительная проверка для перехода на след стобик
                    }
                }
                while (tester(row,column)&&(getCell(row, column) != symbol)) {
                    row++;
                    if (row == size) {
                        row = 0;
                        column++;
                        //дополнительная проверка для перехода на некст строку
                    }
                }
                maxHorizontal = Math.max(countOfSymbol, maxHorizontal);
                countOfSymbol = 0;
            }
            row = 0;
            column = 0;
                while (tester(row,column)) {
                    while (tester(row,column)&&getCell(row, column) == symbol) {
                        column++;
                        countOfSymbol++;
                        if (column == size) {
                            row++;
                            column=0;
                            //дополнительная проверка для перехода на след стобик
                        }
                    }
                    while (tester(row,column)&&getCell(row, column) != symbol) {
                        column++;
                        if (column == size) {
                            row++;
                            column = 0;
                            //дополнительная проверка для перехода на след стобик
                        }
                    }
                    maxVertical = Math.max(countOfSymbol, maxVertical);
                    countOfSymbol = 0;
                }
            row = 0;
            column = 0;

            while (tester(row,column)) {
                while (tester(row,column)&&getCell(row, column) == symbol) {
                    row++;
                    column--;
                    countOfSymbol++;
                }
                while (tester(row,column)&&getCell(row, column) != symbol) {
                    row++;
                    column--;
                }
                 maxDiagonalA = Math.max(countOfSymbol, maxDiagonalA);
                countOfSymbol = 0;
            }
            row = 0;
            column = size - 1;
            //для подсчета диагонали с нижнего левого угла

            while (tester(row,column)) {
                while (tester(row,column)&&getCell(row, column) == symbol) {
                    row++;
                    column++;
                    countOfSymbol++;
                }
                while (tester(row,column)&&getCell(row, column) != symbol) {
                    row++;
                    column++;
                }
                maxDiagonalA = Math.max(countOfSymbol, maxDiagonalA);
                countOfSymbol = 0;
            }
            return Math.max(Math.max(maxHorizontal,maxVertical),Math.max(maxDiagonalA,maxDiagonalB));
        }
        else throw new IllegalArgumentException("Недопустимый символ");
    }
}
