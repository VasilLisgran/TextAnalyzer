import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.io.FileReader;

public class FileAnalyzer {

    private String fileName;
    private int totalWords = 0;
    private int totalLines = 0;
    private Map<String, Integer> wordsCount = new HashMap<>();
    private Set<String> stopWords = new HashSet<>();  // добавить поле

    public FileAnalyzer(){}
    public FileAnalyzer(Set<String> stopWords){
        this.stopWords = stopWords;
    }

    public void setStopWords(Set<String> stopWords) {
        this.stopWords = stopWords;
    }

    public void readFile(String file_name) {
        this.fileName = file_name;
        this.totalWords = 0;
        this.totalLines = 0;
        this.wordsCount.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file_name))) {
            String line;
            System.out.println("Стоп-слова в анализаторе: " + stopWords);
            while ((line = reader.readLine()) != null) {
                this.totalLines++;
                String[] words = line.split("[\\s.,!?;:()=\"-]+");
                for (String word : words) {
                    if (!word.isEmpty() && !stopWords.contains(word.toLowerCase())) {
                        this.totalWords++;
                        this.wordsCount.put(word, this.wordsCount.getOrDefault(word, 0) + 1);
                        System.out.println("Добавлено слово: " + word);
                    }
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileInfo getInfo() {
        return new FileInfo(fileName, totalWords, totalLines, wordsCount);
    }

}
