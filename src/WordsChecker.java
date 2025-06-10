// Пока не работает ಠ_ಠ, но потом будет сверятся с !онлайн! словарём

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WordsChecker {
    public static String isWordExists (String word) {
        try {
            URL url = new URL("https://dictionary.yandex.net/api/v1/dicservice.json/lookup?key=dict.1.1.20250606T160955Z.6534a1e0d15e867f.479fccef72e6a68e749f7286b31f3cb38e63bbc3&lang=en-ru&text=elephant");
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
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
