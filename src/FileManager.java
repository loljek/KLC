// Работает с файлами

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {
    public static final int configLines = 4;

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

    public static void saveText(String file, String text) {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(text);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
