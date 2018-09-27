import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PointAndPlotPanel extends JPanel {

    Graphics2D g2;
    Timer timer;
    //for best quality use values from 1 to 300
    private double speed1 = 40;
    private double speed2 = 120;
    private double speed3 = 200;
    //
    private double amp1 = 60;
    private double amp2 = 20;
    private double amp3 = 12;

    private double phi1;
    private double phi2;
    private double phi3;
    private double increment = 1;
    private double t;
    //the lower the better
    private int fps = 60;
    private boolean rainbow = false;
    private int option = 0;
    private int NPX = 120;
    private int NPY = 120;
    private int GNPX = 420;

    private ArrayList<Integer> history = new ArrayList<>();

    private ArrayList<Integer> historyGreen = new ArrayList<>();
    private ArrayList<Integer> historyYellow = new ArrayList<>();
    private ArrayList<Integer> historyRed = new ArrayList<>();


    public PointAndPlotPanel() {


        this.setBackground(Color.BLACK);
        this.setSize(800, 300);
        int timerDelay = 1000 / fps;
        history.add(0);
        timer = new Timer(timerDelay, e -> redraw());
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        g2.setColor(Color.white);

        //Coordinates for the pointers
        g2.drawLine(120, 20, 120, 220);
        g2.drawLine(20, 120, 220, 120);
        //Coodinates for plotting
        g2.drawLine(420, 20, 420, 220);
        g2.drawLine(410, 120, 760, 120);

        g2.setColor(Color.green);
        g2.setStroke(new BasicStroke(3));

        double omega1 = speed1 / 30;
        double omega2 = speed2 / 30;
        double omega3 = speed3 / 30;

        //added
        if(option == 0){
            int v1x = NPX + (int) (amp1 * Math.cos((-t * omega1 / fps)+Math.toRadians(-this.phi1)));
            int v1y = NPY + (int) (amp1 * Math.sin((-t * omega1 / fps)+Math.toRadians(-this.phi1)));

            int v2x = v1x + (int) (amp2 * Math.cos((-t * omega2 / fps)+ Math.toRadians(-this.phi2)) );
            int v2y = v1y + (int) (amp2 * Math.sin((-t * omega2 / fps)+ Math.toRadians(-this.phi2)));

            int v3x = v2x + (int) (amp3 * Math.cos((-t * omega3 / fps)+ Math.toRadians(-this.phi3)));
            int v3y = v2y + (int) (amp3 * Math.sin((-t * omega3 / fps)+ Math.toRadians(-this.phi3)));

            g2.drawLine(NPX, NPY, v1x, v1y);

            g2.setColor(Color.yellow);

            g2.drawLine(v1x, v1y, v2x, v2y);

            g2.setColor(Color.RED);

            g2.drawLine(v2x, v2y, v3x, v3y);

            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.white);
            g2.drawLine(v3x, v3y, GNPX, v3y);

            if (t / increment % 3 == 0) {
                history.add(v3y);
            }
            if (history.size() > 1) {
                while (history.size() >= 300) {
                    history.remove(0);
                }

                for (int i = 1; i < history.size(); i++) {

                    g2.setStroke(new BasicStroke(2));
                    if(rainbow==false){
                        g2.setColor(Color.WHITE);
                    }else{
                        g2.setColor(Color.getHSBColor(i * 0.01f, 1, 1));

                    }
                    g2.drawLine(i - 1 + GNPX, history.get(i - 1),
                            i + GNPX, history.get(i));
                }
            }
            g2.dispose();
        }
        //seperate
        if(option == 1){
            int v1x = NPX + (int) (amp1 * Math.cos((-t * omega1 / fps)+Math.toRadians(-this.phi1)));
            int v1y = NPY + (int) (amp1 * Math.sin((-t * omega1 / fps)+Math.toRadians(-this.phi1)));

            int v2x = NPX + (int) (amp2 * Math.cos((-t * omega2 / fps)+ Math.toRadians(-this.phi2)) );
            int v2y = NPY + (int) (amp2 * Math.sin((-t * omega2 / fps)+ Math.toRadians(-this.phi2)));

            int v3x = NPX + (int) (amp3 * Math.cos((-t * omega3 / fps)+ Math.toRadians(-this.phi3)));
            int v3y = NPY + (int) (amp3 * Math.sin((-t * omega3 / fps)+ Math.toRadians(-this.phi3)));

            g2.drawLine(NPX, NPY, v1x, v1y);

            g2.setColor(Color.yellow);

            g2.drawLine(NPX, NPY, v2x, v2y);

            g2.setColor(Color.RED);

            g2.drawLine(NPX, NPY, v3x, v3y);

            g2.setStroke(new BasicStroke(1));

            g2.setColor(Color.RED);
            g2.drawLine(v3x, v3y, GNPX, v3y);
            g2.setColor(Color.YELLOW);
            g2.drawLine(v2x, v2y, GNPX, v2y);
            g2.setColor(Color.GREEN);
            g2.drawLine(v1x, v1y, GNPX, v1y);

            if (t / increment % 3 == 0) {
                historyGreen.add(v1y);
                historyYellow.add(v2y);
                historyRed.add(v3y);
            }
            if (historyGreen.size() > 1 && historyRed.size() > 1 && historyGreen.size() > 1 ) {
                while (historyGreen.size() >= 300 && historyRed.size() >= 300 && historyYellow.size() >= 300) {
                    historyGreen.remove(0);
                    historyRed.remove(0);
                    historyYellow.remove(0);

                }

                for (int i = 1; i < historyGreen.size(); i++) {

                    g2.setStroke(new BasicStroke(2));
                    if(rainbow==false){
                        g2.setColor(Color.GREEN);
                        g2.drawLine(i - 1 + GNPX, historyGreen.get(i - 1),
                                i + GNPX, historyGreen.get(i));
                        g2.setColor(Color.YELLOW);
                        g2.drawLine(i - 1 + GNPX, historyYellow.get(i - 1),
                                i + GNPX, historyYellow.get(i));
                        g2.setColor(Color.RED);
                        g2.drawLine(i - 1 + GNPX, historyRed.get(i - 1),
                                i + GNPX, historyRed.get(i));
                    }else{
                        g2.setColor(Color.getHSBColor(i * 0.01f, 1, 1));
                        g2.drawLine(i - 1 + GNPX, historyGreen.get(i - 1),i + GNPX, historyGreen.get(i));
                        g2.setColor(Color.getHSBColor(i * 0.04f, 1, 1));
                        g2.drawLine(i - 1 + GNPX, historyYellow.get(i - 1), i + GNPX, historyYellow.get(i));
                        g2.setColor(Color.getHSBColor(i * 0.005f, 1, 1));
                        g2.drawLine(i - 1 + GNPX, historyRed.get(i - 1), i + GNPX, historyRed.get(i));
                    }
                }
            }
            g2.dispose();
        }
        //resulting vector
        if(option == 2){
            int v1x = NPX + (int) (amp1 * Math.cos((-t * omega1 / fps)+Math.toRadians(-this.phi1)));
            int v1y = NPY + (int) (amp1 * Math.sin((-t * omega1 / fps)+Math.toRadians(-this.phi1)));

            int v2x = v1x + (int) (amp2 * Math.cos((-t * omega2 / fps)+ Math.toRadians(-this.phi2)) );
            int v2y = v1y + (int) (amp2 * Math.sin((-t * omega2 / fps)+ Math.toRadians(-this.phi2)));

            int v3x = v2x + (int) (amp3 * Math.cos((-t * omega3 / fps)+ Math.toRadians(-this.phi3)));
            int v3y = v2y + (int) (amp3 * Math.sin((-t * omega3 / fps)+ Math.toRadians(-this.phi3)));

            g2.setColor(Color.ORANGE);

            g2.drawLine(NPX, NPY, v3x, v3y);


            g2.setStroke(new BasicStroke(1));
            g2.setColor(Color.white);

            if (t / increment % 3 == 0) {
                history.add(v3y);
            }
            if (history.size() > 1) {
                while (history.size() >= 300) {
                    history.remove(0);
                }

                for (int i = 1; i < history.size(); i++) {

                    g2.setStroke(new BasicStroke(2));
                    if(rainbow==false){
                        g2.setColor(Color.WHITE);
                    }else{
                        g2.setColor(Color.getHSBColor(i * 0.01f, 1, 1));

                    }
                    g2.drawLine(i - 1 + GNPX, history.get(i - 1),
                            i + GNPX, history.get(i));
                }
            }
            g2.dispose();
        }

    }

    private void redraw() {
        t += increment;
        repaint();
    }

    public void setParameters(double amp1, double amp2, double amp3, double speed1, double speed2, double speed3, double phase1, double phase2, double phase3, boolean rainbow, int option) {

        this.amp1 = amp1;
        this.amp2 = amp2;
        this.amp3 = amp3;

        this.speed1 = speed1;
        this.speed2 = speed2;
        this.speed3 = speed3;

        this.phi1 =phase1;
        this.phi2 =phase2;
        this.phi3 = phase3;

        this.rainbow = rainbow;
        this.option = option;

    }

    public void resetPlot() {
        t = 0;
        history.clear();
        historyRed.clear();
        historyGreen.clear();
        historyYellow.clear();
    }
}