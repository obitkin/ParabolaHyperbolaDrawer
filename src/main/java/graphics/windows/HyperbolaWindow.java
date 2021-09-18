package graphics.windows;

import graphics.canvas.HyperbolaCanvas;
import graphics.canvas.ParabolaCanvas;
import graphics.graph.hyperbola.BestHyperbolaDrawer;
import graphics.graph.hyperbola.HyperbolaDrawer;
import graphics.graph.hyperbola.NaIveHyperbolaDrawer;
import graphics.graph.parabola.BestParabolaDrawer;
import graphics.graph.parabola.NaiveParabolaDrawer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Dictionary;
import java.util.Hashtable;

import static java.lang.System.exit;

public class HyperbolaWindow {

    int WINDOW_HEIGHT = 680;
    int WINDOW_WIDTH = 1300;

    int CANVAS_WIDTH = WINDOW_WIDTH / 2 - 2;
    int CANVAS_HEIGHT = WINDOW_HEIGHT;

    String viewTypeString = "Включить отображение точек графиков: ";
    String numberOfDotsString = "Выберите количество точек: ";
    String parameterAString = "Выберите параметер 'a';  a = ";
    String parameterBString = "Выберите параметер 'b';  b = ";

    Frame mainFrame;

    TextField viewTypeText = new TextField(viewTypeString);
    TextField numberOfDotsText = new TextField(numberOfDotsString);
    TextField parameterText = new TextField(parameterAString);
    TextField parameterBText = new TextField(parameterBString);

    Checkbox viewTypeCheckbox = new Checkbox();
    JSlider numberOfDotsSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 1);
    JSlider parameterSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);
    JSlider parameterBSlider = new JSlider(JSlider.HORIZONTAL, 1, 1000, 100);

    Panel controlPanel1;
    Panel controlPanel2;

    HyperbolaCanvas bestCanvas;
    HyperbolaCanvas naiveCanvas;


    public HyperbolaWindow(){
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new Frame("Hyperbola drawers");
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

        Panel rowBot = new Panel(new GridLayout(2, 4));
        rowBot.setSize(WINDOW_WIDTH, 60);
        viewTypeText.setEditable(false);
        numberOfDotsText.setEditable(false);
        parameterText.setEditable(false);
        parameterBText.setEditable(false);
        rowBot.add(viewTypeText);
        rowBot.add(numberOfDotsText);
        rowBot.add(parameterText);
        rowBot.add(parameterBText);

        viewTypeCheckbox.addItemListener(listener -> startDraw());
        rowBot.add(viewTypeCheckbox);

        Dictionary<Integer, JLabel> labels2 = new Hashtable<>();
        labels2.put(1, new JLabel("<html><font color=gray size=3>1"));
        labels2.put(100, new JLabel("<html><font color=gray size=3>100"));
        numberOfDotsSlider.setLabelTable(labels2);
        numberOfDotsSlider.setPaintLabels(true);
        numberOfDotsSlider.addChangeListener(listener -> startDraw());
        rowBot.add(numberOfDotsSlider);

        Dictionary<Integer, JLabel> labels3 = new Hashtable<>();
        labels3.put(1, new JLabel("<html><font color=gray size=3>0.1"));
        labels3.put(1000, new JLabel("<html><font color=gray size=3>100"));
        parameterSlider.setLabelTable(labels3);
        parameterSlider.setPaintLabels(true);
        parameterSlider.addChangeListener(listener -> startDraw());
        rowBot.add(parameterSlider);

        Dictionary<Integer, JLabel> labels4 = new Hashtable<>();
        labels4.put(1, new JLabel("<html><font color=gray size=3>0.1"));
        labels4.put(1000, new JLabel("<html><font color=gray size=3>100"));
        parameterBSlider.setLabelTable(labels4);
        parameterBSlider.setPaintLabels(true);
        parameterBSlider.addChangeListener(listener -> startDraw());
        rowBot.add(parameterBSlider);

        mainFrame.add(rowBot, constraints);

        rowBot = new Panel(new GridLayout(1, 2));
        Label my = new Label("Результат работы реализованного алгоритма", Label.CENTER);
        my.setBackground(Color.GRAY);
        rowBot.add(my);
        Label notMy = new Label("Результат работы наивного алгоритма", Label.CENTER);
        notMy.setBackground(Color.GRAY);
        rowBot.add(notMy);
        rowBot.setSize(WINDOW_WIDTH, 60);

        constraints.gridy = 1;
        mainFrame.add(rowBot, constraints);

        Panel cvHolder = new Panel(new GridLayout(1, 2));
        controlPanel1 = new Panel();
        controlPanel1.setLayout(new FlowLayout());
        controlPanel1.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        controlPanel2 = new Panel();
        controlPanel2.setLayout(new FlowLayout());
        controlPanel2.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        cvHolder.add(controlPanel1);
        cvHolder.add(controlPanel2);
        cvHolder.setSize(WINDOW_WIDTH, 600);

        HyperbolaDrawer bestHyperbolaDrawer = new BestHyperbolaDrawer(CANVAS_WIDTH, CANVAS_HEIGHT);
        HyperbolaDrawer naiveHyperbolaDrawer = new NaIveHyperbolaDrawer(CANVAS_WIDTH, CANVAS_HEIGHT);
        bestCanvas = new HyperbolaCanvas(bestHyperbolaDrawer);
        naiveCanvas = new HyperbolaCanvas(naiveHyperbolaDrawer);

        constraints.gridy = 2;
        constraints.ipady = WINDOW_HEIGHT - 150;
        mainFrame.add(cvHolder, constraints);
        mainFrame.setVisible(true);
    }

    private void startDraw() {
        bestCanvas.setStateOfViewType(viewTypeCheckbox.getState());
        naiveCanvas.setStateOfViewType(viewTypeCheckbox.getState());
        numberOfDotsText.setText(numberOfDotsString + numberOfDotsSlider.getValue());
        parameterText.setText(parameterAString + parameterSlider.getValue() / 10.);
        parameterBText.setText(parameterBString + parameterBSlider.getValue() / 10.);

        controlPanel1.removeAll();
        controlPanel2.removeAll();
        bestCanvas.hyperbolaDrawer.setHyperbola(
                numberOfDotsSlider.getValue(),
                parameterSlider.getValue() / 10.,
                parameterBSlider.getValue() / 10.
        );
        naiveCanvas.hyperbolaDrawer.setHyperbola(
                numberOfDotsSlider.getValue(),
                parameterSlider.getValue() / 10.,
                parameterBSlider.getValue() / 10.
        );
        controlPanel1.add(bestCanvas);
        controlPanel2.add(naiveCanvas);
    }

}