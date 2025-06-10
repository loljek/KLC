// Создаёт и запускает приложение

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main (String[] args) {
        String textFile = "text.txt";
        String correctTextFile = "correctText.txt";

        JFrame frame = new JFrame("KLC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension frame_size = new Dimension(700, 670);
        frame.setSize(frame_size);
        frame.setLocationRelativeTo(null);

        JTextArea ta1 = new JTextArea();
        ta1.setText(FileManager.loadText(textFile));
        JTextArea ta2 = new JTextArea();
        ta2.setText(FileManager.loadText(correctTextFile));
        Dimension ta_size = new Dimension(340, 500);
        ta1.setPreferredSize(ta_size);
        ta2.setPreferredSize(ta_size);

        JPanel textPanel = new JPanel();
        textPanel.add(ta1);
        textPanel.add(ta2);
        textPanel.setPreferredSize(new Dimension(700, 500));

        JButton correct = new JButton();
        correct.setText("поменять раскладку");
        correct.addActionListener(e -> {
            try {
                FileManager.saveText(textFile, ta1.getText());
                ta2.setText(Corrector.getCorrectString(ta1.getText()));
                FileManager.saveText(correctTextFile, ta2.getText());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Dimension correct_size = new Dimension(350, 70);
        correct.setMinimumSize(correct_size);
        correct.setPreferredSize(correct_size);
        correct.setMaximumSize(correct_size);

        JCheckBox cb1 = new JCheckBox();
        cb1.setText("умная конвертация");
        JCheckBox cb2 = new JCheckBox();
        cb2.setText("инверсия");
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

        frame.setLayout(new FlowLayout());
        frame.add(textPanel);
        frame.add(buttonPanel);
        frame.setVisible(true);

        System.out.println(WordsChecker.isWordExists("1"));
    }
}
