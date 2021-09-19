package curves.windows;

import java.awt.*;

import javax.swing.*;

class JOptionPaneTest {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(300, 200);
        String[] items = {"Parabola", "Hyperbola"};
        JComboBox<String> combo = new JComboBox<>(items);
        JTextField numberOfDots = new JTextField("1234.56");
        frame.add(combo);
        combo.setVisible(true);
        JTextField minX = new JTextField("9876.54");
        JTextField maxX = new JTextField("9876.54");
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setSize(300, 200);
//        panel.add(combo);
        panel.add(new JLabel("Field 1:"));
        panel.add(numberOfDots);
        panel.add(new JLabel("Field 2:"));
        panel.add(minX);
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
//        int result = JOptionPane.showConfirmDialog(null, panel, "Measure Window",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//        if (result == JOptionPane.OK_OPTION) {
//            System.out.println(combo.getSelectedItem()
//                    + " " + numberOfDots.getText()
//                    + " " + minX.getText());
//        } else {
//            System.out.println("Cancelled");
//        }
    }
}
