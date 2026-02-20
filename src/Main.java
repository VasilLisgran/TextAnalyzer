import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 1. Сначала работа со стоп-словами
        StopWordsManager manager = new StopWordsManager();
        Set<String> stopWords = manager.loadStopWords("stop-words.txt");  // заполняем и получаем сразу

        // 2. Анализ файла
        FileAnalyzer FA = new FileAnalyzer(stopWords);
        FA.readFile("file.txt");
        FileInfo FI = FA.getInfo();

        int n = 2;

        ReportGenerator RG = new ReportGenerator();
        RG.saveReport(FI, FA, stopWords, "done.txt", n);

    }
}