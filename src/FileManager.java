// Считывает текст из файла и записывает текст в файл

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class FileManager {
    public static String loadText(String file) {
        StringBuilder text = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(file));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                text.append(line + "\n");
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

    public static boolean loadConfig (String file, int n) {
        boolean f = false;
        try {
            Scanner sc = new Scanner(new File(file));
            for (int i = 0; i <= n; i++){
                f = sc.nextBoolean();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public static void saveConfig (String file, boolean... f) {
        try {
            StringBuilder config = new StringBuilder();
            for (int i = 0; i < f.length; i++) {
                config.append(f[i] + "\n");
            }
            FileWriter fw = new FileWriter(file);
            fw.write(config.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
