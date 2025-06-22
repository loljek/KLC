import java.util.HashMap;
import java.util.Map;

/**
 * Исправляет раскладку.
 * @see Corrector#getCorrectChar(char)
 * @see Corrector#getCorrectString(String)
 */

public class Corrector {
    /**
     * Исправляет символ.
     * @param ch символ для исправления
     * @return возвращает исправленный символ
     * @see Corrector#getCorrectString(String)
     */

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

        corrected.put('ф', 'a');
        corrected.put('и', 'b');
        corrected.put('с', 'c');
        corrected.put('в', 'd');
        corrected.put('у', 'e');
        corrected.put('а', 'f');
        corrected.put('п', 'g');
        corrected.put('р', 'h');
        corrected.put('ш', 'i');
        corrected.put('о', 'j');
        corrected.put('л', 'k');
        corrected.put('д', 'l');
        corrected.put('ь', 'm');
        corrected.put('т', 'n');
        corrected.put('щ', 'o');
        corrected.put('з', 'p');
        corrected.put('й', 'q');
        corrected.put('к', 'r');
        corrected.put('ы', 's');
        corrected.put('е', 't');
        corrected.put('г', 'u');
        corrected.put('м', 'v');
        corrected.put('ц', 'w');
        corrected.put('ч', 'x');
        corrected.put('н', 'y');
        corrected.put('я', 'z');
        corrected.put('б', ',');
        corrected.put('Б', '<');
        corrected.put('ю', '.');
        corrected.put('Ю', '>');
        corrected.put('ж', ';');
        corrected.put('Ж', ':');
        corrected.put('э', '\'');
        corrected.put('Э', '\"');
        corrected.put('х', '[');
        corrected.put('Х', '{');
        corrected.put('ъ', ']');
        corrected.put('Ъ', '}');
        corrected.put('ё', '`');
        corrected.put('Ё', '~');

        if (corrected.get(ch) != null) {
            return corrected.get(ch);
        } else if (Character.isUpperCase(ch) & (corrected.get(Character.toLowerCase(ch)) != null)) {
            return Character.toUpperCase(corrected.get(Character.toLowerCase(ch)));
        }
        return ch;
    }

    /**
     * Исправляет текст, не обращает внимание на символы внутри #,
     * по отдельности исправляет каждый символ, используя {@link Corrector#getCorrectChar getCorrectChar}
     * @param text текст для исправления
     * @return возвращает исправленный текст
     */

    public static String getCorrectString (String text) {
        char[] ct = text.toCharArray();
        boolean inside = false;
        for (int i = 0; i < ct.length; i++) {
            if (ct[i] == '#') {
                inside = !inside;
                ct[i] = 0;
            } else if (!inside) {
                ct[i] = getCorrectChar(ct[i]);
            }
        }
        return new String(ct);
    }
}
