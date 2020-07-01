package assignment27;

public class Player {

    private String name;
    private int score;

    Player(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
