import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.io.FileReader;

public class StopWordsManager {
    Set<String> stopWords = new HashSet<>();

    public Set<String> InputStopWords() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Write words one by one. To finish print 'stop' without ''  ");

        int totalLoaded = 0;

        while(true) {
            String word = scanner.nextLine().trim().toLowerCase();
            if (word.equals("stop")) {
                break;
            }
            stopWords.add(word);
            totalLoaded++;
        }
        saveStopWords(stopWords, "stop-words.txt");
        System.out.println("Total loaded: " + totalLoaded + " words");

        return stopWords;
    }

    public void saveStopWords(Set<String> stopWords, String fileName){
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
            for (String word : stopWords){
                writer.println(word);
            }
            System.out.println("Saved " + stopWords.size() + " words to  " + fileName);
        }
        catch (IOException e){
            System.out.println("Error while saving: " + e.getMessage());
        }
    }

    public void loadStopWords(String fileName){
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while ((line = reader.readLine()) != null){
                stopWords.add(line);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
