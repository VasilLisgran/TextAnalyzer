import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class ReportGenerator {


    public void saveReport(FileInfo fi, Set<String> stopWords, String outputFile, int n, String loadWaterFileName){
        try(PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            printTitle(writer, fi);
            printBasicStats(writer, fi, n, stopWords);
            printWater(writer, fi,loadWaterFileName);
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

    public void printBasicStats(PrintWriter writer, FileInfo fi, int n, Set<String> stopWords){
        writer.println("Самое длинное слово: " + fi.longestWord());
        writer.println("Самое короткое слово: " + fi.shortestWord());
        writer.println("Самое популярное слово: " + fi.getTopWords(n, stopWords));
        writer.println("Самое редкое слово: " + fi.getRareWords(n, stopWords));
    }

    public void printWater(PrintWriter writer, FileInfo fi, String fileName){
        double waterP = fi.water(fileName);
        writer.printf("Общий уровень шума: %.3f%%%n", waterP);
    }

}
