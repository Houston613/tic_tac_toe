public class TicTacToe {
    private static int size;//имеется размер не меняющийся
    static public char[][] field;
    final static char tic = 'x';
    final static char tac = 'o';
    final static char toe = ' ';
    /*static int  left;//оставшиеся клетки
    boolean check = true;//проверка очередности хода, начинают крестики*/

    private TicTacToe(int size) {//конструктор игрового поля
        field = new char[size][size];
        //left = size*size;
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                field[row][column] = toe;
    }

    public static void main(String[] args) {
        TicTacToe round = new TicTacToe(4);
        for (int row = 0; row < size; row++)
            for (int column = 0; column < size; column++)
                addTic(row, column);

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
        if (field[row][column] != toe) {
            field[row][column] = toe;
            //check = !check;
            //left++;
        }
    }

    private static void/*int*/ findMaxLine(int size) {
        boolean maxTic = false;
        boolean maxTac = false;
        boolean parity = false;
        int maxVertical = 0;
        int maxHorizontal = 0;
        int maxDiagonal = 0;
        int countOfTic = 0;
        int countOfTac = 0;
        int countOfToe = 0;
        int row = 0;
        int column = 0;
        while (row <= size) {
            while (field[row][column] == tic) {
                row++;
                countOfTic++;
                if (row > size) {
                    break;
                }//если row больше сайза, чтобы не было выхода за границу массива
                //цикл подсчета крестиков
            }
            while ((row <= size) && (field[row][column] == tac)) {
                row++;
            countOfTac++;
                if (row > size) {
                    break;
                }
            }
            if (maxHorizontal < countOfTic) {
                maxHorizontal = countOfTic;
                maxTic = true;
                maxTic = false;
                } else if (maxHorizontal < countOfTac){
                maxHorizontal = countOfTac;
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
}
