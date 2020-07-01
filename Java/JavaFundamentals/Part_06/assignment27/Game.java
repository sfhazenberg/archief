package assignment27;

public class Game {

    private String title;
    private Player player1;
    private Player player2;

    Game(String title, Player player1, Player player2) {
        this.title = title;
        this.player1 = player1;
        this.player2 = player2;
    }

    private String getPlayerNameHighestScore() {
        int score1 = player1.getScore();
        int score2 = player2.getScore();
        if (score1 == score2) {
            return "equal score";
        } else if (score1 > score2){
            return player1.getName();
        } else {
            return player2.getName();
        }
    }

    void printScoreBoard() {
        System.out.println("----- " + title + " -----");
        System.out.println("Player one " + player1.getName() + " has a score of " + player1.getScore());
        System.out.println("Player two " + player2.getName() + " has a score of " + player2.getScore());
        System.out.println("Player " + getPlayerNameHighestScore() + " has the highest score!");
    }

    //Getters and setters
    public String getTitle() {
        return title;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
