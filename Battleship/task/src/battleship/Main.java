package battleship;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        Game.setUp(Game.Player.PLAYER_1);
        Game.setUp(Game.Player.PLAYER_2);
        System.out.println();
        while (true) {
            Game.turn(Game.Player.PLAYER_1);
            Game.turn(Game.Player.PLAYER_2);
        }
    }
}
