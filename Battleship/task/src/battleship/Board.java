package battleship;

public class Board {
    public char[][] board;

    Board() {

        board = new char[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = '~';
            }
        }
    }

    public char getBoardPoint(int row, int column) {
        return board[row][column];
    }

    public void setBoardPoint(int row, int column, char newPointChar) {
        this.board[row][column] = newPointChar;
    }

    public boolean isShipPlacedHorizontal(int row, int firstPointColumn, int secondPointColumn) {
        for (int i = firstPointColumn - 1; i <= secondPointColumn + 1; i++) {
            int demarcatedI = i;
            if (demarcatedI < 0) demarcatedI = 0;
            if (demarcatedI > 9) demarcatedI = 9;
            if (this.board[row][demarcatedI] != '~') {
                System.out.println("Error! Ships may not touch one another. Try again:\n");
                return false;
            }
        }
        int demarcatedUpperRow = row + 1;
        int demarcatedLowerRow = row - 1;
        if (demarcatedUpperRow < 0) demarcatedUpperRow = 0;
        if (demarcatedUpperRow > 9) demarcatedUpperRow = 9;
        if (demarcatedLowerRow < 0) demarcatedLowerRow = 0;
        if (demarcatedLowerRow > 9) demarcatedLowerRow = 9;
        for (int i = firstPointColumn; i <= secondPointColumn; i++) {
            if (this.board[demarcatedUpperRow][i] != '~') {
                System.out.println("Error! Ships may not touch one another. Try again:\n");
                return false;
            }
            if (this.board[demarcatedLowerRow][i] != '~') {
                System.out.println("Error! Ships may not touch one another. Try again:\n");
                return false;
            }
        }
        for (int i = firstPointColumn; i <= secondPointColumn; i++) {
            this.board[row][i] = 'O';
        }
        return true;
    }

    public boolean isShipPlacedVertical(int column, int firstPointRow, int secondPointRow) {
        for (int i = firstPointRow - 1; i <= secondPointRow + 1; i++) {
            int demarcatedI = i;
            if (demarcatedI < 0) demarcatedI = 0;
            if (demarcatedI > 9) demarcatedI = 9;
            if (this.board[demarcatedI][column] != '~') {
                System.out.println("Error! Ships may not touch one another. Try again:\n");
                return false;
            }
        }
        int demarcatedRightColumn = column + 1;
        int demarcatedLeftColumn = column - 1;
        if (demarcatedRightColumn < 0) demarcatedRightColumn = 0;
        if (demarcatedRightColumn > 9) demarcatedRightColumn = 9;
        if (demarcatedLeftColumn < 0) demarcatedLeftColumn = 0;
        if (demarcatedLeftColumn > 9) demarcatedLeftColumn = 9;
        for (int i = firstPointRow; i <= secondPointRow; i++) {
            if (this.board[i][demarcatedRightColumn] != '~') {
                System.out.println("Error! Ships may not touch one another. Try again:\n");
                return false;
            }
            if (this.board[i][demarcatedLeftColumn] != '~') {
                System.out.println("Error! Ships may not touch one another. Try again:\n");
                return false;
            }
        }
        for (int i = firstPointRow; i <= secondPointRow; i++) {
            this.board[i][column] = 'O';
        }
        return true;
    }

    public static boolean checkForIntactShips(Board boards) {
        for (int i = 0; i <= 9; i++) {
            for (int j = 0; j <= 9; j++) {
                if (boards.getBoardPoint(i, j) == 'O') return true;
            }
        }
        return false;
    }

    public static void placeShot(int row, int column, Board boards) {
        if (boards.getBoardPoint(row, column) == 'O') {
            boards.setBoardPoint(row, column, 'X');
            if (checkForDestroyedShip(row, column, boards)) {
                System.out.println("You sank a ship!\n");
            } else {
                System.out.println("You hit a ship!\n");
            }
        } else if (boards.getBoardPoint(row, column) == '~') {
            boards.setBoardPoint(row, column, 'M');
            System.out.println("You missed!\n");
        } else if (boards.getBoardPoint(row, column) == 'X' || boards.getBoardPoint(row, column) == 'M') {
            System.out.println("You already took a shot at that coordinate!\n");
        }
    }

    public static boolean checkForDestroyedShip(int row, int column, Board boards) {
        //check right side
        for (int i = column + 1; i <= 9; i++) {
            if (boards.board[row][i] == '~' || boards.board[row][i] == 'M') break;
            if (boards.board[row][i] == 'O') return false;
        }
        //check left side
        for (int i = column - 1; i >= 0; i--) {
            if (boards.board[row][i] == '~' || boards.board[row][i] == 'M') break;
            if (boards.board[row][i] == 'O') return false;
        }
        //check up
        for (int i = row + 1; i <= 9; i++) {
            if (boards.board[i][column] == '~' || boards.board[i][column] == 'M') break;
            if (boards.board[i][column] == 'O') return false;
        }
        //check down
        for (int i = row - 1; i >= 0; i--) {
            if (boards.board[i][column] == '~' || boards.board[i][column] == 'M') break;
            if (boards.board[i][column] == 'O') return false;
        }
        return true;
    }

}
