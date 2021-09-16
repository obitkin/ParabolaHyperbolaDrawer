package graphics.windows;

import graphics.canvas.ParabolaCanvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Dictionary;
import java.util.Hashtable;

import static java.lang.System.exit;

public class Drawer {

    private Frame mainFrame;
    private Panel controlPanel;
    private ParabolaCanvas parabolaCanvas;

    JSlider angleOfRotationSlider = new JSlider(JSlider.HORIZONTAL, 0, 360, 0);
    JSlider numberOfDotsSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
    JSlider parameterSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);

    int WINDOW_HEIGHT = 780;
    int WINDOW_WIDTH = 1374;

    int CANVAS_HEIGHT = WINDOW_HEIGHT - 100;
    int CANVAS_WIDTH = WINDOW_WIDTH - 2;

    public Drawer(){
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new Frame("ParabolaCanvas Drawer");
        mainFrame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // По умолчанию натуральная высота, максимальная ширина
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 0.5;
        constraints.gridy   = 0  ;  // нулевая ячейка таблицы по вертикали
        constraints.gridx = 0;

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                exit(0);
            }
        });

        Panel rowBot = new Panel(new GridLayout(2, 3));
        rowBot.setSize(WINDOW_WIDTH, 60);
        rowBot.add(new Label("angle of rotation"));
        rowBot.add(new Label("number of dots"));
        rowBot.add(new Label("parameter A"));

        Dictionary<Integer, JLabel> labels = new Hashtable<Integer, JLabel>();
        labels.put(0, new JLabel("<html><font color=gray size=3>0"));
        labels.put(360, new JLabel("<html><font color=gray size=3>360"));
        angleOfRotationSlider.setLabelTable(labels);
        angleOfRotationSlider.setPaintLabels(true);
        angleOfRotationSlider.addChangeListener(listener -> startDraw());
        rowBot.add(angleOfRotationSlider);


        Dictionary<Integer, JLabel> labels2 = new Hashtable<Integer, JLabel>();
        labels2.put(1, new JLabel("<html><font color=gray size=3>1"));
        labels2.put(100, new JLabel("<html><font color=gray size=3>100"));
        numberOfDotsSlider.setLabelTable(labels2);
        numberOfDotsSlider.setPaintLabels(true);
        numberOfDotsSlider.addChangeListener(listener -> startDraw());
        rowBot.add(numberOfDotsSlider);

        Dictionary<Integer, JLabel> labels3 = new Hashtable<Integer, JLabel>();
        labels3.put(1, new JLabel("<html><font color=gray size=3>1"));
        labels3.put(100, new JLabel("<html><font color=gray size=3>100"));
        parameterSlider.setLabelTable(labels3);
        parameterSlider.setPaintLabels(true);
        parameterSlider.addChangeListener(listener -> startDraw());
        rowBot.add(parameterSlider);

        constraints.gridy = 1;
        mainFrame.add(rowBot, constraints);

        controlPanel = new Panel();
        controlPanel.setLayout(new FlowLayout());
        controlPanel.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        parabolaCanvas = new ParabolaCanvas(CANVAS_WIDTH / 2, CANVAS_HEIGHT);

        constraints.gridy = 2;
        constraints.ipady = WINDOW_HEIGHT - 150;
        mainFrame.add(controlPanel, constraints);
        mainFrame.setVisible(true);
    }

    private void startDraw() {
        System.out.println("Draw");
        controlPanel.removeAll();
        parabolaCanvas.parabolaDrawer.setParabola(
                angleOfRotationSlider.getValue(),
                numberOfDotsSlider.getValue(),
                parameterSlider.getValue()
        );
        controlPanel.add(parabolaCanvas);
    }

}
