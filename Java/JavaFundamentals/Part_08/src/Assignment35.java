import java.io.*;

public class Assignment35 {

    public static void main(String[] args) {
        try (FileReader fileReader = new FileReader("Course_Part_08/ipsum.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
