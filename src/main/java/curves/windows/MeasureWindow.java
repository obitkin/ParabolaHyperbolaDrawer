package curves.windows;

import curves.data.Parameters;
import curves.drawers.Drawer;
import curves.drawers.hyperbola.BestHyperbolaDrawer;
import curves.drawers.hyperbola.NaiveHyperbolaDrawer;
import curves.drawers.parabola.BestParabolaDrawer;
import curves.drawers.parabola.NaiveParabolaDrawer;

import java.awt.*;

import javax.swing.*;

public class MeasureWindow {

    int WINDOW_WIDTH = 500;
    int WINDOW_HEIGHT = 270;

    JComboBox<String> curveType = new JComboBox<>(new String[] {
            "Hyperbola", "Parabola", "Hyperbola Naive", "Parabola Naive"
    });
    JLabel curveTypeLabel = new JLabel("Выберите тип кривой");
    JLabel minXLabel = new JLabel("Минимальное значение X > 0");
    JTextField minXText = new JTextField();
    JLabel maxXLabel = new JLabel("Максимальное значение X ");
    JTextField maxXText = new JTextField();
    JLabel numberOfDotsLabel = new JLabel("Число точек для построения N > 0");
    JTextField numberOfDotsText = new JTextField();
    JLabel numberOfMeasureLabel = new JLabel("Число измерений M > 0");
    JTextField numberOfMeasureText = new JTextField();
    JLabel ALabel = new JLabel("Значение параметра A > 0");
    JTextField AText = new JTextField();
    JLabel BLabel = new JLabel("Значение параметра B > 0");
    JTextField BText = new JTextField();
    JButton button = new JButton("Вычислить время работы");
    JLabel result = new JLabel("Время работы: ");

    public MeasureWindow()
    {
        JFrame frame = new JFrame("Measure Window");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        JPanel panel = new JPanel();
        panel.setBounds(0,0,WINDOW_WIDTH, WINDOW_HEIGHT);
        panel.setLayout(new GridLayout(8, 2));

        panel.add(curveTypeLabel);
        panel.add(curveType);
        panel.add(minXLabel);
        panel.add(minXText);
        panel.add(maxXLabel);
        panel.add(maxXText);
        panel.add(numberOfDotsLabel);
        panel.add(numberOfDotsText);
        panel.add(numberOfMeasureLabel);
        panel.add(numberOfMeasureText);
        panel.add(ALabel);
        panel.add(AText);
        panel.add(BLabel);
        panel.add(BText);
        panel.add(button);
        panel.add(result);

        curveType.addItemListener(listener -> {
            if (listener.getItem().toString().contains("Parabola")) {
                BText.setEnabled(false);
            } else {
                BText.setEnabled(true);
            }
        });

        button.addActionListener(listener -> makeMeasure());

        frame.add(panel);
        frame.setVisible(true);
    }

    void makeMeasure() {
        Drawer drawer;
        double minX = Double.parseDouble(minXText.getText());
        double maxX = Double.parseDouble(maxXText.getText());
        int numberOfDots = Integer.parseInt(numberOfDotsText.getText());
        double A = Double.parseDouble(AText.getText());
        Parameters parameters = new Parameters(minX, maxX, null, numberOfDots);
        if (curveType.getSelectedItem().toString().equals("Parabola")) {
            drawer = new BestParabolaDrawer(parameters, A);
        } else if (curveType.getSelectedItem().toString().equals("Hyperbola")) {
            double B = Double.parseDouble(BText.getText());
            drawer = new BestHyperbolaDrawer(parameters, A, B);
        } else if (curveType.getSelectedItem().toString().equals("Hyperbola Naive")) {
            double B = Double.parseDouble(BText.getText());
            drawer = new NaiveHyperbolaDrawer(parameters, A, B);
        } else if (curveType.getSelectedItem().toString().equals("Parabola Naive")) {
            drawer = new NaiveParabolaDrawer(parameters, A);
        } else {
            throw new RuntimeException("Cannot init drawer");
        }
        long currentTime = System.currentTimeMillis();
        int numberOfMeasure = Integer.parseInt(numberOfMeasureText.getText());
        for (int i = 0; i < numberOfMeasure; i++) {
            drawer.drawCurve();
            //System.out.println(drawer.drawCurve());
        }
        long measuredTime = System.currentTimeMillis() - currentTime;
        result.setText("Общее: " + measuredTime + " мс.");
    }

}
