import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Assignment36 {

    public static void main(String[] args) {
        try (FileWriter fileWriter = new FileWriter("Course_Part_08/goodMeme.txt");
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("Have you ever heard the tragedy of Darth Plagueis the Wise?\n");
            bufferedWriter.write("I thought not.\n");
            bufferedWriter.write("It's not a story the Jedi would tell you.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
