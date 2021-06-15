import java.awt.*;
import java.awt.event.*;
import java.awt.geom.CubicCurve2D;
import java.util.Random;

public class Window {

    private Frame mainFrame;
    private Label headerLabel;
    private Label statusLabel1;
    private Label statusLabel2;
    private Panel controlPanel1;
    private Panel controlPanel2;
    TextField countInput;
    TextField timeOut1;
    TextField timeOut2;
    Button startTestButton;
    private BezierDrawer bd;
    private LibCanvas lc;

    double[] data;

    public Window(){
        prepareGUI();
    }

    public static void main(String[] args){
        Window  window = new Window();
        window.showCanvasDemo();
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
        startTestButton = new Button("Запуск тестирования");
        startTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startComparison();
            }
        });

        mainFrame = new Frame("Java AWT Examples");
        mainFrame.setSize(620,650);
        mainFrame.setResizable(false);
        mainFrame.setLayout(new GridLayout(2, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });
        Panel cvHolder = new Panel(new GridLayout(1, 2));
        headerLabel = new Label();
        headerLabel.setAlignment(Label.CENTER);
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

    private void showCanvasDemo(){
        headerLabel.setText("Control in action: Canvas");

        startComparison();
        mainFrame.setVisible(true);
    }

    private void generateRandom(int count) {
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());

        int N = count * 8;
        data = new double[N];
        for (int i = 0; i < N; i++) {
            data[i] = r.nextDouble() * 300;
        }

    }

    private void startComparison() {
        int count;
        try {
            count = Integer.parseInt(countInput.getText());
        } catch (NumberFormatException e) {
            count = 1000;
            countInput.setText("1000");
        }
        if (count > 10000000) {
            count = 10000000;
            countInput.setText("1000");
        }

        generateRandom(count);

        controlPanel1.removeAll();
        controlPanel2.removeAll();

        bd = new BezierDrawer(this, 300, 300);
        lc = new LibCanvas();

        controlPanel1.add(bd);
        controlPanel2.add(lc);
    }

    public void refreshTime() {
        timeOut1.setText(String.valueOf(bd.getTimeEstimated()));
        timeOut2.setText(String.valueOf(lc.getTimeEstimated()));
    }

    class MyCanvas extends Canvas {

        public MyCanvas () {
            setBackground (Color.GRAY);
            setSize(300, 300);
        }

        public void paint (Graphics g) {
            Graphics2D g2;
            g2 = (Graphics2D) g;
            //g2.drawString ("It is a custom canvas area", 70, 70);

            // библиотечная встроенная функция рисования кривых Безье третьего порядка
            CubicCurve2D cc2d = new CubicCurve2D.Double(0, 0, 0, 100, 100, 100, 100, 0);
            g2.draw(cc2d);
        }
    }

    class LibCanvas extends Canvas {

        private long timeEstimated;

        public LibCanvas () {
            setBackground (Color.GRAY);
            setSize(300, 300);
        }

        public long getTimeEstimated() {
            return timeEstimated;
        }

        public void paint (Graphics g) {
            long time = System.nanoTime();
            Graphics2D g2;
            g2 = (Graphics2D) g;
            //g2.drawString ("It is a custom canvas area 2", 70, 70);

            // библиотечная встроенная функция рисования кривых Безье третьего порядка

            double[] d = data;
            for (int k = 0; k < d.length / 8; k++) {
                int offset = k * 8;
                CubicCurve2D cc2d = new CubicCurve2D.Double(d[offset], d[offset + 1], d[offset + 2], d[offset + 3], d[offset + 4], d[offset + 5], d[offset + 6], d[offset + 7]);
                g2.draw(cc2d);
            }

            timeEstimated = System.nanoTime() - time;
            System.out.printf("Library estimated: %d ns\n", timeEstimated);
            Window.this.refreshTime();
        }
    }
}