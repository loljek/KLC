import javax.swing.*;
import java.awt.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Меняет раскладку у текста на правильную.
 * @author loljek
 * @version 1.5
 */

public class Main {
    /**
     * Создаёт окно приложения
     * @param args аргументы
     */
    public static void main (String[] args) {
        String inputTextFile = "inputText.txt";
        String outputTextFile = "outputText.txt";
        String configFile = "config.txt";

        JFrame frame = new JFrame("KLC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension frame_size1 = new Dimension(700, 700);
        Dimension frame_size2 = new Dimension(1090,550);
        AtomicBoolean isHorisontal = new AtomicBoolean(FileManager.loadConfig(configFile, 3));
        if (isHorisontal.get()){
            frame.setSize(frame_size2);
        } else {
            frame.setSize(frame_size1);
        }
        frame.setLocationRelativeTo(null);

        JTextArea ta1 = new JTextArea();
        ta1.setText("");
        JTextArea ta2 = new JTextArea();
        ta1.setText("");
        Dimension ta_size = new Dimension(340, 500);
        ta1.setPreferredSize(ta_size);
        ta2.setPreferredSize(ta_size);

        JPanel textPanel = new JPanel();
        textPanel.add(ta1);
        textPanel.add(ta2);
        textPanel.setPreferredSize(new Dimension(700, 500));

        JButton correct = new JButton();
        correct.setText("загрузить текст");
        Dimension correct_size = new Dimension(350, 70);
        correct.setMinimumSize(correct_size);
        correct.setPreferredSize(correct_size);
        correct.setMaximumSize(correct_size);

        JCheckBox cb1 = new JCheckBox();
        cb1.setText("умная конвертация (работает медленнее)");
        JCheckBox cb2 = new JCheckBox();
        cb2.setText("сохранять текст");
        cb2.setSelected(FileManager.loadConfig(configFile, 1));
        JCheckBox cb3 = new JCheckBox();
        cb3.setText("тёмная тема");
        cb3.setSelected(FileManager.loadConfig(configFile, 2));
        Dimension cb_size = new Dimension(300, 20);
        cb1.setMinimumSize(cb_size);
        cb1.setPreferredSize(cb_size);
        cb1.setMaximumSize(cb_size);
        cb2.setMinimumSize(cb_size);
        cb2.setPreferredSize(cb_size);
        cb2.setMaximumSize(cb_size);
        cb3.setMinimumSize(cb_size);
        cb3.setPreferredSize(cb_size);
        cb3.setMaximumSize(cb_size);

        JButton view = new JButton();
        view.setText("вид");
        Dimension view_size = new Dimension(100, 20);
        view.setMinimumSize(view_size);
        view.setPreferredSize(view_size);
        view.setMaximumSize(view_size);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(correct);
        buttonPanel.add(cb1);
        buttonPanel.add(cb2);
        buttonPanel.add(cb3);
        buttonPanel.add(view);

        Color default_bg = new Color(238, 238,238);
        Color dark_bg = new Color(30, 30, 30);
        Color default_button_bg = new Color(220, 175, 125);
        Color dark_button_bg = new Color(70, 110, 110);
        Color default_ta_bg = new Color(255,255,255);
        Color dark_ta_bg = new Color(0, 0, 0);
        Color default_caret = new Color(10,10,10);
        Color dark_caret = new Color(220, 220, 220);
        Color default_fg = new Color(51, 51, 51);
        Color dark_fg = new Color(240, 240, 240);

        if (cb3.isSelected()) {
            frame.getContentPane().setBackground(dark_bg);
            textPanel.setBackground(dark_bg);
            buttonPanel.setBackground(dark_bg);
            correct.setBackground(dark_button_bg);
            correct.setForeground(dark_fg);
            ta1.setBackground(dark_ta_bg);
            ta1.setForeground(dark_fg);
            ta1.setCaretColor(dark_caret);
            ta2.setBackground(dark_ta_bg);
            ta2.setForeground(dark_fg);
            ta2.setCaretColor(dark_caret);
            cb1.setBackground(dark_bg);
            cb1.setForeground(dark_fg);
            cb2.setBackground(dark_bg);
            cb2.setForeground(dark_fg);
            cb3.setBackground(dark_bg);
            cb3.setForeground(dark_fg);
            view.setBackground(dark_button_bg);
            view.setForeground(dark_fg);
        } else {
            frame.getContentPane().setBackground(default_bg);
            textPanel.setBackground(default_bg);
            buttonPanel.setBackground(default_bg);
            correct.setBackground(default_button_bg);
            correct.setForeground(default_fg);
            ta1.setBackground(default_ta_bg);
            ta1.setForeground(default_fg);
            ta1.setCaretColor(default_caret);
            ta2.setBackground(default_ta_bg);
            ta2.setForeground(default_fg);
            ta2.setCaretColor(default_caret);
            cb1.setBackground(default_bg);
            cb1.setForeground(default_fg);
            cb2.setBackground(default_bg);
            cb2.setForeground(default_fg);
            cb3.setBackground(default_bg);
            cb3.setForeground(default_fg);
            view.setBackground(default_button_bg);
            view.setForeground(default_fg);
        }

        frame.setLayout(new FlowLayout());
        frame.add(textPanel);
        frame.add(buttonPanel);
        frame.setVisible(true);

        ta1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (ta1.getText().isEmpty()) {
                    correct.setText("загрузить текст");
                } else {
                    correct.setText("поменять раскладку");
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (ta1.getText().isEmpty()) {
                    correct.setText("загрузить текст");
                } else {
                    correct.setText("поменять раскладку");
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (ta1.getText().isEmpty()) {
                    correct.setText("загрузить текст");
                } else {
                    correct.setText("поменять раскладку");
                }

            }
        });

        correct.addActionListener(e -> {
            try {
                if (ta1.getText().isEmpty()) {
                    ta1.setText(FileManager.loadText(inputTextFile));
                    ta2.setText(FileManager.loadText(outputTextFile));
                    cb1.setSelected(FileManager.loadConfig(configFile, 0));
                    correct.setText("поменять раскладку");
                } else {
                    if (cb1.isSelected()) {
                        String corrected = Corrector.getCorrectString(ta1.getText());
                        ta2.setText(WordsChecker.checkText(corrected));
                    } else {
                        ta2.setText(Corrector.getCorrectString(ta1.getText()));
                    }
                    if (cb2.isSelected()) {
                        FileManager.saveText(inputTextFile, ta1.getText());
                        FileManager.saveText(outputTextFile, ta2.getText());
                        FileManager.saveConfig(configFile,  cb1.isSelected(), 0);
                    }
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
        });

        cb2.addActionListener(e -> {
            try {
                FileManager.saveConfig(configFile, cb2.isSelected(), 1);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        cb3.addActionListener(e -> {
            try {
                FileManager.saveConfig(configFile, cb3.isSelected(), 2);
                if (cb3.isSelected()) {
                    frame.getContentPane().setBackground(dark_bg);
                    textPanel.setBackground(dark_bg);
                    buttonPanel.setBackground(dark_bg);
                    correct.setBackground(dark_button_bg);
                    correct.setForeground(dark_fg);
                    ta1.setBackground(dark_ta_bg);
                    ta1.setForeground(dark_fg);
                    ta1.setCaretColor(dark_caret);
                    ta2.setBackground(dark_ta_bg);
                    ta2.setForeground(dark_fg);
                    ta2.setCaretColor(dark_caret);
                    cb1.setBackground(dark_bg);
                    cb1.setForeground(dark_fg);
                    cb2.setBackground(dark_bg);
                    cb2.setForeground(dark_fg);
                    cb3.setBackground(dark_bg);
                    cb3.setForeground(dark_fg);
                    view.setBackground(dark_button_bg);
                    view.setForeground(dark_fg);
                } else {
                    frame.getContentPane().setBackground(default_bg);
                    textPanel.setBackground(default_bg);
                    buttonPanel.setBackground(default_bg);
                    correct.setBackground(default_button_bg);
                    correct.setForeground(default_fg);
                    ta1.setBackground(default_ta_bg);
                    ta1.setForeground(default_fg);
                    ta1.setCaretColor(default_caret);
                    ta2.setBackground(default_ta_bg);
                    ta2.setForeground(default_fg);
                    ta2.setCaretColor(default_caret);
                    cb1.setBackground(default_bg);
                    cb1.setForeground(default_fg);
                    cb2.setBackground(default_bg);
                    cb2.setForeground(default_fg);
                    cb3.setBackground(default_bg);
                    cb3.setForeground(default_fg);
                    view.setBackground(default_button_bg);
                    view.setForeground(default_fg);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        view.addActionListener(e -> {
            if (frame.getWidth() <= 1000) {
                isHorisontal.set(true);
                frame.setSize(frame_size2);
            } else {
                isHorisontal.set(false);
                frame.setSize(frame_size1);
            }
            frame.setLocationRelativeTo(null);
            FileManager.saveConfig(configFile, isHorisontal.get(), 3);
        });
    }
}
