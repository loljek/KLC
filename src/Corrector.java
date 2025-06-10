import java.util.HashMap;
import java.util.Map;

public class Corrector {
    public static char getCorrectChar(char ch) {
        Map<Character, Character> corrected = new HashMap<>();
        corrected.put('f', 'а');
        corrected.put(',', 'б');
        corrected.put('<', 'Б');
        corrected.put('d', 'в');
        corrected.put('u', 'г');
        corrected.put('l', 'д');
        corrected.put('t', 'е');
        corrected.put('`', 'ё');
        corrected.put('~', 'Ё');
        corrected.put(';', 'ж');
        corrected.put(':', 'Ж');
        corrected.put('p', 'з');
        corrected.put('b', 'и');
        corrected.put('q', 'й');
        corrected.put('r', 'к');
        corrected.put('k', 'л');
        corrected.put('v', 'м');
        corrected.put('y', 'н');
        corrected.put('j', 'о');
        corrected.put('g', 'п');
        corrected.put('h', 'р');
        corrected.put('c', 'с');
        corrected.put('n', 'т');
        corrected.put('e', 'у');
        corrected.put('a', 'ф');
        corrected.put('[', 'х');
        corrected.put('{', 'Х');
        corrected.put('w', 'ц');
        corrected.put('x', 'ч');
        corrected.put('i', 'ш');
        corrected.put('o', 'щ');
        corrected.put(']', 'ъ');
        corrected.put('}', 'Ъ');
        corrected.put('s', 'ы');
        corrected.put('m', 'ь');
        corrected.put('\'', 'э');
        corrected.put('\"', 'Э');
        corrected.put('.', 'ю');
        corrected.put('>', 'Ю');
        corrected.put('z', 'я');
        corrected.put('б', ',');
        corrected.put('ю', '.');
        corrected.put('ж', ';');
        corrected.put('э' ,'\'');
        corrected.put('х','[');
        corrected.put('ъ', ']');
        corrected.put('ё', '`');

        if (Character.isUpperCase(ch) & (corrected.get(Character.toLowerCase(ch)) != null)) {
            return Character.toUpperCase(corrected.get(Character.toLowerCase(ch)));
        } else if (corrected.get(ch) != null) {
            return corrected.get(ch);
        }
        return ch;
    }

    public static String getCorrectString (String text) {
        char[] ct = text.toCharArray();
        for (int i = 0; i < ct.length; i++) {
            ct[i] = Corrector.getCorrectChar(ct[i]);
        }
        return new String(ct);
    }
}
