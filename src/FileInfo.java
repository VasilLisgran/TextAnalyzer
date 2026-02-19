import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public record FileInfo(
        String fileName,
        int totalWords,
        int totalLines,
        Map<String, Integer> uniqueWords
) {
    public void getTopWords(int n, Set<String> stopWords){
        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : uniqueWords.entrySet()){
            if(!stopWords.contains(entry.getKey())){
                list.add(entry);
            }
        }

        // Comparator sorting by 1) How many times word uses; 2) Letter of words if first has same values
        list.sort(Comparator.comparingInt((Map.Entry<String, Integer> e) -> e.getValue())
                .reversed()
                .thenComparing(Map.Entry::getKey));

        for (int i = 0; i < n && i < list.size(); i++) {
            // Entry for using one pair, not the whole Map
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println((i+1) +"." + entry.getKey() + " " + entry.getValue());
        }
    }

    public void getRareWords(int n, Set<String> stopWords){
        List<Map.Entry<String, Integer>> list = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : uniqueWords.entrySet()){
            if(!stopWords.contains(entry.getKey())){
                list.add(entry);
            }
        }

        list.sort(Comparator.comparingInt((Map.Entry<String, Integer> e) -> e.getValue())
                .thenComparing(Map.Entry::getKey));

        for (int i = 0; i < n && i < list.size(); i++) {
            Map.Entry<String, Integer> entry = list.get(i);

            System.out.println((i+1) +"." + entry.getKey() + " " + entry.getValue());
        }
    }

    public void lengthStats(){
        longestWord();
        shortestWord();
    }

    public void longestWord(){
        // Firstly I decided to use Comparator....
        /* List<String> wordslist = new ArrayList<>(uniqueWords.keySet());
        wordslist.sort(Comparator.comparing(String::length));
        String longest = wordslist.getLast(); */

        // But Comparator works by O(n log n) vs For by O(n)
        String longest = "";
        for (String word : uniqueWords.keySet()) {
            if (word.length() > longest.length()) {
                longest = word;
            }
        }
        System.out.println("The longest word: " + longest);
    }

    public void shortestWord() {
        String shortest = "";
        for (String word : uniqueWords.keySet()) {
            //Skip if its ""
            if (shortest.isEmpty() || word.length() < shortest.length()) {
                shortest = word;
            }
        }
        System.out.println("The shortest word: " + shortest);
    }
}

