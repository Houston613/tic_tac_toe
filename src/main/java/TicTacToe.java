public class TicTacToe {
    private static int size;//имеется размер не меняющийся
    static public char[][] field;
    final static char tic = 'x';
    final static char tac = 'o';
    final static char toe = ' ';
    /*static int  left;//оставшиеся клетки
    boolean check = true;//проверка очередности хода, начинают крестики*/

    private TicTacToe(int size) {//конструктор игрового поля
        TicTacToe.size = size;
        field = new char[size][size];
        //left = size*size;
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                field[row][column] = toe;
    }
    /* реализовал методы добавления крестика/нолика
     * в методе присутствует проверка на:
     * соответствие размерам
     * можно ли поставить клетку
     * очерередность(пока убрал за ненадобностью)*/
    private static void addTic(int row, int column) {//метод добавления крестика
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

    private static void addTac(int row, int column) {//метод добавления крестика
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

    private static void addToe(int row, int column) {//метод удаления клектки
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

    private static void/*int*/ findMaxLine(int size) {
        boolean maxTic = false;
        boolean maxTac = false;
        //boolean parity = false;случай когда кол - во крестиков и ноликов совпало. не реализовано
        int maxVertical = 0;
        int maxHorizontal = 0;
        int maxDiagonalA = 0;
        int maxDiagonalB = 0;
        int countOfTic = 0;//кол-во крестиков за один проход цикла
        int countOfTac = 0;//кол-во нулей за один проход цикла
        int row = 0;//нужно для прохода цикла по строке
        int column = 0;//нужно для прохода цикла по столбцу


        /*не придумал, как сделать адекватно, а не через 3 цикла
        * сделано для того, чтобы в случае "x o xxx вторая последовательность крестиков не терялась*/

        for (int i=0; i<size; i++) {//обеспечивает движение по столбцам
            while (row < size) {
                while ((row < size) && (field[row][i] == tic)) {
                    //доп условие чтобы исключить выход на границу массива
                    //должно быть лучше чем break.
                    row++;
                    countOfTic++;
                }
                //подсчет ноликов
                while (((row < size)) && (field[row][i] == tac)) {
                    row++;
                    countOfTac++;
                }
                //если клетка пустая
                while (((row < size)) && (field[row][i] == toe)) {
                    row++;
                }

                if (maxVertical < countOfTic) {
                    maxVertical = countOfTic;
                    maxTic = true;
                    maxTac = false;
                } else if (maxVertical < countOfTac) {
                    maxVertical = countOfTac;
                    maxTac = true;
                    maxTic = false;
                }
                /*перед окончанием цикла мы получим максимальную горизонтальную для случая "крестик - нолик".
                 *при окончании линии ноликов внутренние циклы завершатся. начнется верхний цикл while, поэтому
                 * значения countOfTic countOfTac нужно обнулить */
                countOfTac = 0;
                countOfTic = 0;
            }
        }

        for (int i=0; i<size; i++) {//обеспечивает движение по строкам
            while (column < size) {
                while ((column < size) && (field[i][column] == tic)) {
                    //доп условие чтобы исключить выход на границу массива
                    //должно быть лучше чем break.
                    column++;
                    countOfTic++;
                }
                //подсчет ноликов
                while (((column < size)) && (field[i][column] == tac)) {
                    column++;
                    countOfTac++;
                }
                //если клетка пустая
                while (((column < size)) && (field[i][column] == toe)) {
                    column++;
                }

                if (maxHorizontal < countOfTic) {
                    maxHorizontal = countOfTic;
                    maxTic = true;
                    maxTac = false;
                } else if (maxHorizontal < countOfTac) {
                    maxHorizontal = countOfTac;
                    maxTac = true;
                    maxTic = false;
                }
                /*перед окончанием цикла мы получим максимальную вертикальную для случая "крестик - нолик".
                 *при окончании линии ноликов внутренние циклы завершатся. начнется верхний цикл while, поэтому
                 * значения countOfTic countOfTac нужно обнулить */
                countOfTac = 0;
                countOfTic = 0;
            }
        }

        row = 0;
        column = 0;
        //обнуление для диагонали начинающейся с верхнего левого угла
        while ((row < size)&&(column < size)) {
            while ((row < size)&&(column < size) && (field[row][column] == tic)) {
                //доп условие чтобы исключить выход на границу массива
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

            if (maxDiagonalA < countOfTic) {
                maxDiagonalA = countOfTic;
                maxTic = true;
                maxTac = false;
            } else if (maxDiagonalA < countOfTac){
                maxDiagonalA = countOfTac;
                maxTac = true;
                maxTic = false;
            }
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

            if (maxDiagonalB < countOfTic) {
                maxDiagonalB = countOfTic;
                maxTic = true;
                maxTac = false;
            } else if (maxDiagonalB < countOfTac){
                maxDiagonalB = countOfTac;
                maxTac = true;
                maxTic = false;
            }
            /*перед окончанием цикла мы получим максимальную диагональную для случая "крестик - нолик".
             *при окончании линии ноликов внутренние циклы завершатся. начнется верхний цикл while, поэтому
             * значения countOfTic countOfTac нужно обнулить */
            countOfTac = 0;
            countOfTic = 0;
        }
       int max = Math.max(Math.max(maxHorizontal,maxVertical),Math.max(maxDiagonalA,maxDiagonalB));

    }
}
