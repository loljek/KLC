import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Работает с файлами.
 * @see FileManager#loadText(String)  
 * @see FileManager#saveText(String, String)
 * @see FileManager#loadConfig(String, int) 
 * @see FileManager#saveConfig(String, boolean, int)
 */

public class FileManager {
    /**Количество настроек.*/
    public static final int configLines = 4;

    /**
     * Загрузка текста из файла.
     * @param file название файла
     * @return возвращает текст файла
     * @see FileManager#saveText(String, String)
     */

    public static String loadText(String file) {
        StringBuilder text = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(file));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                text.append(line).append("\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return text.toString();
    }

    /**
     * Сохранение текста в файл.
     * @param file название файла
     * @param text текст
     * @see FileManager#loadText(String)
     */

    public static void saveText(String file, String text) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка конкретной настройки из файла.
     * @param file название файла
     * @param line строка с настройкой
     * @return возвращает параметр настройки
     * @see FileManager#saveConfig(String, boolean, int)
     */

    public static boolean loadConfig (String file, int line) {
        boolean f = false;
        try {
            Scanner sc = new Scanner(new File(file));
            for (int i = 0; i <= line; i++){
                f = sc.nextBoolean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    /**
     * Сохранение настройки в файл.
     * @param file название файла
     * @param f параметр
     * @see FileManager#loadConfig(String, int)
     */

    public static void saveConfig (String file, boolean f, int line) {
        try {
            StringBuilder config = new StringBuilder();
            for (int i = 0; i < configLines; i++) {
                if (i == line) {
                    config.append(f).append("\n");
                } else {
                    config.append(FileManager.loadConfig(file, i)).append("\n");
                }
            }
            FileWriter fw = new FileWriter(file);
            fw.write(config.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
