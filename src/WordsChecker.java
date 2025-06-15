// Сверяется с !ОНЛАЙН! словарём

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordsChecker {
    private static JSONObject getJsonObject(String word) throws IOException {
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
        return jsonArray.getJSONObject(0);
    }

    public static String checkWord (String word) {
        try {
            char last = word.charAt(word.length() - 1);
            char first = word.charAt(0);
            boolean[] isN = new boolean[2];
            String send = word;
            if (!Character.isLetter(last)) {
                send = word.substring(0, word.length() - 1);
                isN[0] = true;
            }
            if (!Character.isLetter(first)) {
                send = word.substring(1);
                isN[1] = true;
            }
            
            JSONObject jsonObject = getJsonObject(send);
            StringBuilder r = new StringBuilder(jsonObject.get("s").toString().split("\"")[1]);
            
            if (isN[0]) {
                r.append(last);
            }
            if (isN[1]) {
                r.insert(0, first);
            }
            return r.toString();
        } catch (Exception ignored) {}
        return word;
    }

    public static String checkText (String text) {
        String[] lines = text.split("\n");
        StringBuilder r = new StringBuilder();
        for (String line : lines) {
            String[] words = line.split(" ");
            for (String word : words) {
                r.append(WordsChecker.checkWord(word)).append(" ");
            }
            r.append('\n');
        }
        return r.toString();
    }
}
