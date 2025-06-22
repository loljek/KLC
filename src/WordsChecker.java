import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Проверяет правильность написания при помощи API от Яндекса(speller).
 * @see WordsChecker#getJsonObject(String)
 * @see WordsChecker#checkWord(String)
 * @see WordsChecker#checkText(String)
 */

public class WordsChecker {

    /**
     * Отправляет запрос в API от Яндекса(speller).
     * @param word слово для проверки
     * @return возвращает ответ от API от Яндекса(speller).
     * @see WordsChecker#checkWord(String)
     * @see WordsChecker#checkText(String)
     */

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

    /**
     * Проверяет правильность написания слова при помощи API от Яндекса(speller).
     * @param word слово для проверки
     * @return возвращает правильное(с орфографической точки зрения) слово
     * @see WordsChecker#getJsonObject(String)
     * @see WordsChecker#checkText(String)
     */

    public static String checkWord (String word) {
        try {
            JSONObject jsonObject = getJsonObject(word);
            return jsonObject.get("s").toString().split("\"")[1];
        } catch (Exception ignored) {}
        return word;
    }

    /**
     * Проверяет правильность написания текста при помощи API от Яндекса(speller), 
     * разбивает текст на слова и проверяет их по отдельности.
     * @param text текст для проверки
     * @return возвращает правильный(с орфографической точки зрения) текст
     * @see WordsChecker#getJsonObject(String)
     * @see WordsChecker#checkWord(String) 
     */
    
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
