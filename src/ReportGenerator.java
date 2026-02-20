import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class ReportGenerator {
    StopWordsManager manager = new StopWordsManager();
    Set<String> stopWords = manager.loadStopWords("stop-words.txt");  // заполняем и получаем сразу

    // 2. Анализ файла
    FileAnalyzer FA = new FileAnalyzer(stopWords);
    FileInfo FI = FA.getInfo();

    public void saveReport(FileInfo fi, FileAnalyzer fa, Set<String> stopWords, String outputFile, int n){
        try(PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            printTitle(writer, fi);
            printBasicStats(writer, fi, n);
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void printTitle(PrintWriter writer, FileInfo fi){
        writer.println("Название: " + fi.fileName());
        writer.println("Всего слов: " + fi.totalWords());
        writer.println("Всего строк: " + fi.totalLines());
    }

    public void printBasicStats(PrintWriter writer, FileInfo fi, int n){
        writer.println("Самое длинное слово: " + fi.longestWord());
        writer.println("Самое короткое слово: " + fi.shortestWord());
        writer.println("Самое популярное слово: " + fi.getTopWords(n, stopWords));
        writer.println("Самое редкое слово: " + fi.getRareWords(n, stopWords));
    }

}
