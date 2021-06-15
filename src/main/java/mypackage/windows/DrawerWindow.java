package mypackage.windows;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DrawerWindow {

    private Frame mainFrame;
    private Label statusLabel1;
    private Label statusLabel2;
    private Panel controlPanel1;
    private Panel controlPanel2;
    TextField countInput;
    TextField timeOut1;
    TextField timeOut2;
    Button startTestButton;

    double[] data;

    int windowHeight = 720;
    int windowWidth = 1370;

    public DrawerWindow(){
        prepareGUI();
    }

    private void prepareGUI(){
        countInput = new TextField(10);
        countInput.setText("1000");
        timeOut1 = new TextField(20);
        timeOut2 = new TextField(20);
        timeOut1.setEditable(false);
        timeOut1.setText("0");
        timeOut2.setEditable(false);
        timeOut2.setText("0");
        startTestButton = new Button("Начать тестирование");
        startTestButton.addActionListener(e -> startComparison());

        mainFrame = new Frame("Bezier Drawer Test");
        mainFrame.setSize(windowWidth,windowHeight);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        Panel cvHolder = new Panel(new GridLayout(1, 2));
        statusLabel1 = new Label();
        statusLabel1.setAlignment(Label.CENTER);
        statusLabel1.setSize(350,100);
        statusLabel2 = new Label();
        statusLabel2.setAlignment(Label.CENTER);
        statusLabel2.setSize(350,100);


        Panel menuPanel = new Panel();
        menuPanel.setLayout(new BorderLayout());
        menuPanel.setSize(600, 100);

        Panel row = new Panel(new GridLayout(1, 2));
        row.add(new Label("Введите число испытаний:"));
        row.add(countInput);
        menuPanel.add(row, BorderLayout.NORTH);
        menuPanel.add(startTestButton, BorderLayout.CENTER);
        Panel rowBot = new Panel(new GridLayout(2, 2));
        rowBot.add(new Label("Затраченное время ручной реализации:"));
        rowBot.add(new Label("Затраченное время библиотеки:"));
        rowBot.add(timeOut1);
        rowBot.add(timeOut2);
        menuPanel.add(rowBot, BorderLayout.SOUTH);

        controlPanel1 = new Panel();
        controlPanel1.setLayout(new FlowLayout());
        controlPanel1.setSize(300, 300);
        controlPanel2 = new Panel();
        controlPanel2.setLayout(new FlowLayout());
        controlPanel2.setSize(300, 300);

        //cvHolder.add(statusLabel1);
        //cvHolder.add(statusLabel2);
        cvHolder.add(controlPanel1);
        cvHolder.add(controlPanel2);
        mainFrame.add(menuPanel);
        mainFrame.add(cvHolder);
        mainFrame.setVisible(true);
    }

    private void startComparison() {
    }

}
