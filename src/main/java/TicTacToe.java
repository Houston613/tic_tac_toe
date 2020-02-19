public class TicTacToe {
    public int size;//имеется размер не меняющийся
    public char[][] field;
    final char tic = 'x';
    final char tac = 'o';
    final char toe = ' ';
    private int maxVerticalTic = 0;
    private int maxVerticalTac = 0;
    private int maxHorizontalTic = 0;
    private int maxHorizontalTac = 0;
    private int maxDiagonalATic = 0;
    private int maxDiagonalATac = 0;
    private int maxDiagonalBTic = 0;
    private int maxDiagonalBTac = 0;

    public TicTacToe (int size) {//конструктор игрового поля
        this.size = size;
        field = new char[size][size];
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                field[row][column] = toe;
    }
    public char getCell(int row, int column){
        if ((row < size) && (column<size) && (row>=0) && (column>=0))
            return field[row][column];
        else throw  new IllegalArgumentException("вы вышли за границы поля");
    }
    /* реализовал методы добавления крестика/нолика
     * в методе присутствует проверка на:
     * соответствие размерам
     * можно ли поставить клетку
     */
    public void addTic(int row, int column) {//метод добавления крестика
        if (row > size) {
            System.out.println("введите a меньшее size");
        } else if (column > size) {
            System.out.println("введите b меньшее size");
        } else {
            if (field[row][column] == toe) {
                field[row][column] = tic;
            } else
                System.out.println("Поле занято");
        }
    }

    public void addTac(int row, int column) {//метод добавления крестика
        if (row > size) {
            System.out.println("введите row меньшее size");
        } else if (column > size) {
            System.out.println("введите column меньшее size");
        } else {
            if (field[row][column] == toe) {
                field[row][column] = tac;
            } else
                System.out.println("Поле занято");
        }
    }

    public void addToe(int row, int column) {//метод удаления клектки
        if (row > size) {
            System.out.println("введите row меньшее size");
        } else if (column > size) {
            System.out.println("введите column меньшее size");
        } else {
            if (field[row][column] != toe) {
                field[row][column] = toe;
            }
        }
    }
    public void maximum(){

    }

    public void findMaxLines(int size) {
        int countOfTic = 0;//кол-во крестиков за один проход цикла
        int countOfTac = 0;//кол-во нулей за один проход цикла
        int row = 0;//нужно для прохода цикла по строке
        int column = 0;//нужно для прохода цикла по столбцу

            while ((row < size) && (column < size)) {
                while ((row < size) && (field[row][column] == tic)) {
                    row++;
                    countOfTic++;
                }
                //подсчет ноликов
                while (((row < size)) && (field[row][column] == tac)) {
                    row++;
                    countOfTac++;
                }
                //если клетка пустая
                while (((row < size)) && (field[row][column] == toe)) {
                    row++;
                }
                if (countOfTic >= maxHorizontalTic)
                    maxHorizontalTic = countOfTic;
                if (countOfTic >= maxHorizontalTac)
                    maxHorizontalTac = countOfTac;
                /*перед окончанием цикла мы получим максимальную горизонтальную для случая "крестик - нолик".
                 *при окончании линии ноликов внутренние циклы завершатся. начнется верхний цикл while, поэтому
                 * значения countOfTic countOfTac нужно обнулить */
                countOfTac = 0;
                countOfTic = 0;
                if (row == size) {
                    column++;
                    row = 0;
                }
                //если мы прошлись по всем элементам в ряду, то мы переходим на след столбец
            }
            row = 0;
            column = 0;

        while ((column < size) && (row < size)) {
            while ((column < size) && (field[row][column] == tic)) {
                column++;
                countOfTic++;
            }
            //подсчет ноликов
            while (((column < size)) && (field[row][column] == tac)) {
                column++;
                countOfTac++;
            }
            //если клетка пустая
            while (((column < size)) && (field[row][column] == toe)) {
                column++;
            }
            if (countOfTic >= maxVerticalTic)
                maxVerticalTic = countOfTic;
            if (countOfTic >= maxVerticalTac)
                maxVerticalTac = countOfTac;
            /*перед окончанием цикла мы получим максимальную горизонтальную для случая "крестик - нолик".
             *при окончании линии ноликов внутренние циклы завершатся. начнется верхний цикл while, поэтому
             * значения countOfTic countOfTac нужно обнулить */
            countOfTac = 0;
            countOfTic = 0;
            if (column == size) {
                row++;
                column = 0;
            }
            //если мы прошлись по всем элементам в столбце, то мы переходим на след строку
        }
        row = 0;
        column = 0;

        while ((row < size) && (column < size)) {
            while ((row < size) && (column < size) && (field[row][column] == tic)) {
                row++;
                column++;
                countOfTic++;
            }
            while ((row < size) && (column < size) && (field[row][column] == tac)) {
                row++;
                column++;
                countOfTac++;
            }
            while ((row < size) && (column < size) && (field[row][column] == toe)) {
                row++;
                column++;
            }
            if (countOfTic >= maxDiagonalATic)
                maxDiagonalATac = countOfTic;
            if (countOfTic >= maxDiagonalATac)
                maxDiagonalATic = countOfTac;
            /*перед окончанием цикла мы получим максимальную диагональную для случая "крестик - нолик".
             *при окончании линии ноликов внутренние циклы завершатся. начнется верхний цикл while, поэтому
             * значения countOfTic countOfTac нужно обнулить */
            countOfTac = 0;
            countOfTic = 0;
        }

        row=0;
        column = size-1;
        //для подсчета диагонали с нижнего левого угла
        while ((row < size) && (column > -1)) {
            while ((row < size)&&(column > -1) && (field[row][column] == tic)) {
                //доп условие чтобы исключить выход на границу массива
                //не уверен, что лучше чем break.
                row++;
                column--;
                countOfTic++;
            }

            while ((row < size) && (column > -1) && (field[row][column] == tac)) {
                row++;
                column--;
                countOfTac++;
            }

            while ((row < size) && (column > -1) && (field[row][column] == toe)) {
                row++;
                column--;
            }

            if (maxDiagonalBTic < countOfTic) {
                maxDiagonalBTic = countOfTic;
            }
            if (maxDiagonalBTac < countOfTac){
                maxDiagonalBTac = countOfTac;
            }
            /*перед окончанием цикла мы получим максимальную диагональную для случая "крестик - нолик".
             *при окончании линии ноликов внутренние циклы завершатся. начнется верхний цикл while, поэтому
             * значения countOfTic countOfTac нужно обнулить */
            countOfTac = 0;
            countOfTic = 0;
        }
    }
}
