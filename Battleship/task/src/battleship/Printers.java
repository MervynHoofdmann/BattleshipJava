package battleship;

public class Printers {
    //prints board without fog of war
    public static void printSetUpBoard(Board boards) {
        // print top line of the board
        System.out.print(" ");
        for (int i = 1; i < 11; i++) System.out.print(" " + i);
        System.out.println();
        // print rest of the board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) System.out.print((char) ('A' + i));
                else System.out.print(" " + boards.board[i][j - 1]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printEnemyBoard(Board boards) {
        // print top line of the board
        System.out.print(" ");
        for (int i = 1; i < 11; i++) System.out.print(" " + i);
        System.out.println();
        // print rest of the board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 11; j++) {
                if (j == 0) System.out.print((char) ('A' + i));
                else if (boards.board[i][j - 1] == 'O') {
                    System.out.print(" ~");
                } else {
                    System.out.print(" " + boards.board[i][j - 1]);
                }
            }
            System.out.println();
        }
    }
}
