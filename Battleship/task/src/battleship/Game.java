package battleship;

import java.util.Scanner;

public class Game {
    public enum Player {
        PLAYER_1, PLAYER_2,
    }

    public enum Ship {
        AIRCRAFT_CARRIER(5, "Aircraft Carrier"), BATTLESHIP(4, "Battleship"), SUBMARINE(3, "Submarine"), CRUISER(3, "Cruiser"), DESTROYER(2, "Destroyer"),
        ;

        private final int length;
        private final String name;

        Ship(int length, String name) {
            this.length = length;
            this.name = name;
        }

        public int getShipLength() {
            return length;
        }

        public String getShipName() {
            return name;
        }

    }

    static Scanner scanner = new Scanner(System.in);
    public static Board[] boards = new Board[2];

    public static void setUp(Player player) {
        if (player == Player.PLAYER_1) {
            System.out.println("Player 1, place your ships on the game field\n");
        }
        if (player == Player.PLAYER_2) {
            System.out.print("Press Enter and pass the move to another player\n...");
            try {
                System.in.read();
                scanner.nextLine();
            } catch (Exception e) {
            }
            System.out.println("Player 2, place your ships on the game field\n");
        }
        boards[player.ordinal()] = new Board();
        Board playerBoard = boards[player.ordinal()];
        Printers.printSetUpBoard(playerBoard);
        for (Ship currentShip : Ship.values()) {
            String currentShipName = currentShip.getShipName();
            int currentShipLength = currentShip.getShipLength();
            boolean currentShipPlaced = false;
            System.out.printf("Enter the coordinates of the %s (%d cells):\n\n", currentShipName, currentShipLength);
            while (!currentShipPlaced) {
                String firstPointInput = scanner.next();
                String secondPointInput = scanner.next();
                int firstPointRow = firstPointInput.charAt(0) - 65;
                int secondPointRow = secondPointInput.charAt(0) - 65;
                int firstPointColumn = Integer.parseInt(firstPointInput.substring(1)) - 1;
                int secondPointColumn = Integer.parseInt(secondPointInput.substring(1)) - 1;
                if (firstPointColumn > secondPointColumn) {
                    int tempColumn = secondPointColumn;
                    secondPointColumn = firstPointColumn;
                    firstPointColumn = tempColumn;
                }
                if (firstPointRow > secondPointRow) {
                    int tempRow = secondPointRow;
                    secondPointRow = firstPointRow;
                    firstPointRow = tempRow;
                }
                // Out of bounds check
                if (firstPointRow < 0 || firstPointRow > 9 || secondPointRow > 9 || firstPointColumn < 0 || firstPointColumn > 9 || secondPointColumn > 9) {
                    System.out.println("Error! One or more coordinates out of bounds. Try Again:\n");
                    //Horizontal ship placement
                } else if (firstPointRow == secondPointRow) {
                    if ((secondPointColumn - firstPointColumn) != (currentShipLength - 1)) {
                        System.out.printf("Error! Incorrect length of the %s. Try again:\n\n", currentShipName);
                    } else if ((secondPointColumn - firstPointColumn) == (currentShipLength - 1)) {
                        if (playerBoard.isShipPlacedHorizontal(firstPointRow, firstPointColumn, secondPointColumn)) {
                            Printers.printSetUpBoard(playerBoard);
                            currentShipPlaced = true;
                        }
                    }
                    //Vertical ship placement
                } else if (firstPointColumn == secondPointColumn) {
                    if ((secondPointRow - firstPointRow) != (currentShipLength - 1)) {
                        System.out.printf("Error! Incorrect length of the %s. Try again:\n\n", currentShipName);
                    } else if ((secondPointRow - firstPointRow) == (currentShipLength - 1)) {
                        if (playerBoard.isShipPlacedVertical(firstPointColumn, firstPointRow, secondPointRow)) {
                            Printers.printSetUpBoard(playerBoard);
                            currentShipPlaced = true;
                        }
                    }
                } else {
                    System.out.println("Error! Wrong input! Try again:\n");
                }
            }
        }
    }

    public static void turn(Player player) {
        System.out.print("Press Enter and pass the move to another player\n...");
        try {
            System.in.read();
            scanner.nextLine();
        } catch (Exception e) {
        }
        Board opponentBoard = boards[0];
        Board playerBoard = boards[1];
        if (player.ordinal() == 0) {
            opponentBoard = boards[1];
            playerBoard = boards[0];
        }
        System.out.println();
        Printers.printEnemyBoard(opponentBoard);
        System.out.println("---------------------");
        Printers.printSetUpBoard(playerBoard);
        System.out.println("Take a shot!\n");
        while (true) {
            String shotInput = scanner.next();
            int shotRow = shotInput.charAt(0) - 65;
            int shotColumn = Integer.parseInt(shotInput.substring(1)) - 1;
            if (shotRow < 0 || shotRow > 9 || shotColumn < 0 || shotColumn > 9) {
                System.out.println("Error! Shot out of bounds. Try again!");
            } else {
                Board.placeShot(shotRow, shotColumn, opponentBoard);
                if (!Board.checkForIntactShips(opponentBoard)) {
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    System.exit(0);
                }
                break;
            }
        }
    }
}


