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
}
