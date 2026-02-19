import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // 1. Сначала работа со стоп-словами
        StopWordsManager manager = new StopWordsManager();
        Set<String> stopWords = manager.InputStopWords();  // заполняем и получаем сразу

        // 2. Анализ файла
        FileAnalyzer FA = new FileAnalyzer(stopWords);
        FA.readFile("file.txt");
        FileInfo FI = FA.getInfo();

        // 3. Вывод статистики
        System.out.println("Название файла: " + FI.fileName());
        System.out.println("Всего слов: " + FI.totalWords());
        System.out.println("Всего строк: " + FI.totalLines());
        System.out.println("Уникальных слов: " + FI.uniqueWords().size());

        // 4. Топы с учетом стоп-слов
        System.out.println("\n=== ТОП-2 САМЫХ ЧАСТЫХ ===");
        FI.getTopWords(2, stopWords);

        System.out.println("\n=== ТОП-2 САМЫХ РЕДКИХ ===");
        FI.getRareWords(2, stopWords);

        // 5. Статистика по длине
        FI.lengthStats();
    }
}