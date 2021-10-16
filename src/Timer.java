import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Timer {
    private static double current = 0.0;
    private static boolean stopped = true;
    private static JFrame frame;
    private static JLabel time;

    public static void main(String[] args) throws InterruptedException {
        init();

        while (!Thread.currentThread().isInterrupted()) {
            while (stopped) {
                Thread.sleep(100);
            }
            current += 0.1;
            Thread.sleep(100);
            time.setText(String.format("%.1f", current));
        }
    }

    private static void init() {
        frame = new JFrame("TIMER");
        frame.setLocationRelativeTo(null);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        time = new JLabel(String.valueOf(current), JLabel.RIGHT);
        time.setVerticalAlignment(JLabel.TOP);
        time.setHorizontalAlignment(JLabel.CENTER);
        time.setSize(50, 50);
        time.setFont(new Font("Verdana", Font.PLAIN, 35));

        frame.add(time);
        frame.addKeyListener(new KeyAdapter() {
                                 @Override
                                 public void keyPressed(KeyEvent e) {
                                     if (e.getKeyCode() == 32) stopped = !stopped;
                                     if (e.getKeyCode() == 10) {
                                         current = 0;
                                         time.setText(String.valueOf(current));
                                     }
                                     if (e.getKeyCode() == 27) {
                                         System.exit(0);
                                     }
                                 }
                             });
        frame.pack();
        frame.setVisible(true);
    }
}
