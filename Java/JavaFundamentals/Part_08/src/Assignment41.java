import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment41 {

    public static void main(String[] args) {
        doTheThing();
    }

    private static void doTheThing() {
        String fileName = "Course_Part_08/dummy.txt";
        try(FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String[] words;         //empty array to store words in while loop
            int wordCount = 0;
            int uppercase = 0;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                words = line.split(" ");          //splits String line into array based on space
                wordCount = wordCount + words.length;   //sets count of words in array
                for (String word : words) {
                    if (Character.isUpperCase(word.charAt(0))) {    //checks if the first letter of every element in array is uppercase
                        uppercase++;                                //if yes, increment int uppercase
                    }
                }
            }
            System.out.println(String.format(
                    "The file %s contains %d words, of which %d words start with an uppercase character.",
                    fileName,
                    wordCount,
                    uppercase
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
