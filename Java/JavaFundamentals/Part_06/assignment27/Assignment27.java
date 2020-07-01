package assignment27;

public class Assignment27 {

    public static void main(String[] args) {
        Player thomas = new Player("Thomas");
        thomas.setScore(33);
        Player yvette = new Player("Yvette");
        yvette.setScore(50);
        Game game = new Game("Schaken", thomas, yvette);
        game.printScoreBoard();
    }
}
