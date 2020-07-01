package assignment26;

public class Assignment26 {

    public static void main(String[] args) {
        runGame("Dammen");
        runGame2("IT'S A-ME");
    }

    private static void runGame(String nameOfGame) {
        Game game = new Game();
        game.setTitle(nameOfGame);

        Player player1 = new Player();
        player1.setName("Mark");
        player1.setScore(21);

        Player player2 = new Player();
        player2.setName("Chantal");
        player2.setScore(29);

        game.setPlayer1(player1);
        game.setPlayer2(player2);

        game.printScoreBoard();
    }

    private static void runGame2(String nameOfGame) {
        Game game = new Game();
        game.setTitle(nameOfGame);

        Player player1 = new Player();
        player1.setName("Mario");
        player1.setScore(9999);

        Player player2 = new Player();
        player2.setName("Luigi");
        player2.setScore(3);

        game.setPlayer1(player1);
        game.setPlayer2(player2);

        game.printScoreBoard();

    }
}
