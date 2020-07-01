package assignment26;

public class Game {

    private String title;
    private Player player1;
    private Player player2;

    void printScoreBoard() {
        System.out.println("----- " + title + " -----");
        System.out.println("Player one " + player1.getName() + " has a score of " + player1.getScore());
        System.out.println("Player two " + player2.getName() + " has a score of " + player2.getScore());
    }

    //Getters and setters
    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getTitle() {
        return title;
    }

    void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    void setTitle(String title) {
        this.title = title;
    }


}
