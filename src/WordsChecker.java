// Сверяется с !ОНЛАЙН! словарём

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordsChecker {
    public static String checkWord (String text) {
        try {
            char last = text.charAt(text.length() - 1);
            char first = text.charAt(0);
            boolean isLN = false;
            boolean isFN = false;
            String word = text;
            if (!Character.isLetter(last) & !Character.isLetter(first)) {
                word = text.substring(1, text.length() - 1);
                isLN = true;
                isFN = true;
            } else if (!Character.isLetter(last)) {
                word = text.substring(0, text.length() - 1);
                isLN = true;
            } else if (!Character.isLetter(first)) {
                word = text.substring(1, text.length());
                isFN = true;
            }

            URL url = new URL("https://speller.yandex.net/services/spellservice.json/checkText?text=" + word);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            String response = sb.toString();
            JSONArray jsonArray = new JSONArray(response);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            StringBuilder r = new StringBuilder(jsonObject.get("s").toString().split("\"")[1]);

            if (isLN & isFN) {
                r.insert(0, first);
                r.append(last);
            } else if (isLN) {
                r.append(last);
            } else if (isFN) {
                r.insert(0, first);
            }
            return r + " ";
        } catch (Exception e) {

        }
        return text + " ";
    }

    public static String checkText (String text) {
        String[] lines = text.split("\n");
        StringBuilder r = new StringBuilder();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                r.append(WordsChecker.checkWord(word));
            }
            r.append('\n');
        }
        return r.toString();
    }
}
