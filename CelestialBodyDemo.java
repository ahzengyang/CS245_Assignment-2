import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class CelestialBodyDemo extends JPanel implements ActionListener {

    private Timer t = new Timer(5, this);
    private List<CelestialBody> celestialBodies;
    public static double scale;

    /**
     * Reads file and instantiates a List structure filled with Celestial bodies, both specified in file
     * @param fileName name of file (or path) to be read
     * @throws Exception configuration file does not follow a specific format
     */
    private void readBodies(String fileName) throws Exception{
        try {
            File myFile = new File(fileName);
            Scanner scan = new Scanner(myFile); // Possible FileNotFoundException
            String dataStructure = scan.nextLine();
            celestialBodies = (List<CelestialBody>) Class.forName(dataStructure).getDeclaredConstructor().newInstance(); // case sensitive
            scale = Double.parseDouble(scan.nextLine()); // meters/pixel
            while (scan.hasNextLine()) {
                String[] body = scan.nextLine().split(",");
                celestialBodies.add(new CelestialBody(body[0],Double.parseDouble(body[1]),Integer.parseInt(body[2]),Integer.parseInt(body[3]),Double.parseDouble(body[4]),Double.parseDouble(body[5]),Integer.parseInt(body[6])));
            }
            scan.close();
        }
        catch (Exception e) {
            throw new Exception("Error detected in configuration file\n" + e.getMessage());
        }
    }

    /**
     * Renders celestial bodies on the Jframe (disclaimer: supposition)
     * @param g Graphics object (you're welcome)
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < celestialBodies.size(); i++) {
            CelestialBody body = celestialBodies.get(i);
            float[] rgb = body.getRgb();
            g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
            int size = body.getSize();
            g.fillOval((int)body.getXCoord(), (int)body.getYCoord(), size, size);
        }
        t.start();
    }

    /**
     * Helps update the celestial bodies on the Jframe (disclaimer: supposition)
     * @param e ActionEvent object (once again, you're welcome)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < celestialBodies.size(); i++) {
            celestialBodies.get(i).update(celestialBodies);
        }
        repaint();
    }

    public static void main(String[] args) throws Exception {
        CelestialBodyDemo cb = new CelestialBodyDemo();
        if (args.length == 0)
            throw new IndexOutOfBoundsException("Failed to provide file path as a command line argument.");
        cb.readBodies(args[0]);
        cb.setBackground(Color.black);
        JFrame frame = new JFrame();
        frame.setTitle("Celestial Bodies Demonstration");
        frame.setSize(768, 768);
        frame.add(cb);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
