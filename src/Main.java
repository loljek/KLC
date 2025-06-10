// Создаёт и запускает приложение

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main (String[] args) {
        String inputTextFile = "inputText.txt";
        String outputTextFile = "outputText.txt";
        String configFile = "config.txt";

        JFrame frame = new JFrame("KLC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension frame_size = new Dimension(700, 670);
        frame.setSize(frame_size);
        frame.setLocationRelativeTo(null);

        JTextArea ta1 = new JTextArea();
        ta1.setText(FileManager.loadText(inputTextFile));
        JTextArea ta2 = new JTextArea();
        ta2.setText(FileManager.loadText(outputTextFile));
        Dimension ta_size = new Dimension(340, 500);
        ta1.setPreferredSize(ta_size);
        ta2.setPreferredSize(ta_size);

        JPanel textPanel = new JPanel();
        textPanel.add(ta1);
        textPanel.add(ta2);
        textPanel.setPreferredSize(new Dimension(700, 500));

        JButton correct = new JButton();
        correct.setText("поменять раскладку");
        Dimension correct_size = new Dimension(350, 70);
        correct.setMinimumSize(correct_size);
        correct.setPreferredSize(correct_size);
        correct.setMaximumSize(correct_size);

        JCheckBox cb1 = new JCheckBox();
        cb1.setText("умная конвертация");
        cb1.setSelected(FileManager.loadConfig(configFile, 0));
        JCheckBox cb2 = new JCheckBox();
        cb2.setText("сохранять текст");
        cb2.setSelected(FileManager.loadConfig(configFile, 1));
        Dimension cb_size = new Dimension(300, 20);
        cb1.setMinimumSize(cb_size);
        cb1.setPreferredSize(cb_size);
        cb1.setMaximumSize(cb_size);
        cb2.setMinimumSize(cb_size);
        cb2.setPreferredSize(cb_size);
        cb2.setMaximumSize(cb_size);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(correct);
        buttonPanel.add(cb1);
        buttonPanel.add(cb2);

        correct.addActionListener(e -> {
            try {
                if (cb2.isSelected()) {
                    FileManager.saveText(inputTextFile, ta1.getText());
                    ta2.setText(Corrector.getCorrectString(ta1.getText()));
                    FileManager.saveText(outputTextFile, ta2.getText());
                } else {
                    ta2.setText(Corrector.getCorrectString(ta1.getText()));
                }
                FileManager.saveConfig(configFile, cb1.isSelected(), cb2.isSelected());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        frame.setLayout(new FlowLayout());
        frame.add(textPanel);
        frame.add(buttonPanel);
        frame.setVisible(true);

//        System.out.println(WordsChecker.isWordExists("1"));
    }
}
