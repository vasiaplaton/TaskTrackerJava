package platon.ru.vsu.cs.adb_lib.logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    @SuppressWarnings("FieldCanBeLocal")
    private final int level = 3;
    private static final String LOG_FILE_PATH = "log.txt";
    private Log(){
        clearLogFile(); // Очистка файла при создании нового экземпляра
    }

    private void clearLogFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, false))) {
            // Пустой блок, файл будет очищен
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок при очистке файла
        }
    }

    private static Log i;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static Log getI(){
        if (i == null){
            i = new Log();
        }
        return i;
    }

    public void log(String msg, int level){
        if (level < 0) {
            throw new IllegalArgumentException();
        }

        if (level <= this.level) {
         //   print(msg, level);
            logToFile(msg, level);
        }
    }

    private void print(String msg, int level){
        String res = "";
        res += "\n";
        if (level == 0) {
            res += ANSI_RED;
        } else {
            res += ANSI_GREEN;
        }
        res += msg;
        res += ANSI_RESET;
        res += "\n";
        System.out.println(res);
    }

    private void logToFile(String msg, int level) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(LOG_FILE_PATH, true))) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            String logEntry = "[" + timestamp + "] " + msg;
            writer.println(logEntry);
        } catch (IOException e) {
            e.printStackTrace(); // Обработка ошибок записи в файл
        }
    }
}
