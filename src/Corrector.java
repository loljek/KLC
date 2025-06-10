// Меняет раскладку

import java.lang.invoke.ConstantBootstraps;
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

        if (corrected.get(ch) != null) {
            return corrected.get(ch);
        } else if (Character.isUpperCase(ch) & (corrected.get(Character.toLowerCase(ch)) != null)) {
            return Character.toUpperCase(corrected.get(Character.toLowerCase(ch)));
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

    public static char getInvertChar(char ch) {
        Map<Character, Character> inverted = new HashMap<>();
        inverted.put('ф', 'a');
        inverted.put('и', 'b');
        inverted.put('с', 'c');
        inverted.put('в', 'd');
        inverted.put('у', 'e');
        inverted.put('а', 'f');
        inverted.put('п', 'g');
        inverted.put('р', 'h');
        inverted.put('ш', 'i');
        inverted.put('о', 'j');
        inverted.put('л', 'k');
        inverted.put('д', 'l');
        inverted.put('ь', 'm');
        inverted.put('т', 'n');
        inverted.put('щ', 'o');
        inverted.put('з', 'p');
        inverted.put('й', 'q');
        inverted.put('к', 'r');
        inverted.put('ы', 's');
        inverted.put('е', 't');
        inverted.put('г', 'u');
        inverted.put('м', 'v');
        inverted.put('ц', 'w');
        inverted.put('ч', 'x');
        inverted.put('н', 'y');
        inverted.put('я', 'z');
        inverted.put('б', ',');
        inverted.put('Б', '<');
        inverted.put('ю', '.');
        inverted.put('Ю', '>');
        inverted.put('ж', ';');
        inverted.put('Ж', ':');
        inverted.put('э', '\'');
        inverted.put('Э', '\"');
        inverted.put('х', '[');
        inverted.put('Х', '{');
        inverted.put('ъ', ']');
        inverted.put('Ъ', '}');
        inverted.put('ё', '`');
        inverted.put('Ё', '~');

        if (inverted.get(ch) != null) {
            return inverted.get(ch);
        } else if (Character.isUpperCase(ch) & (inverted.get(Character.toLowerCase(ch)) != null)) {
            return Character.toUpperCase(inverted.get(Character.toLowerCase(ch)));
        } else {
            return ch;
        }
    }

    public static String getInvertString (String text) {
        char[] it = text.toCharArray();
        for (int i = 0; i < it.length; i++) {
            it[i] = Corrector.getInvertChar(it[i]);
        }
        return new String(it);
    }
}
