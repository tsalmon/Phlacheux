package Viewer;

import ch.randelshofer.media.avi.AVIOutputStream;
import model.movable.Film;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Viewer  extends JFrame{
    ActionListener timerEvent = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!isTapeEmpty()){
                if (!repeatMode && currentFrame ==(tape.size()-1)){
                    setCurrentFrame(0);
                    stopAnimation();
                } else {
                    setCurrentFrame((currentFrame+1)%tapeSize());
                }

            }
        }
    };

    ChangeListener progressEvent = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent changeEvent) {
            setCurrentFrame(((JSlider)changeEvent.getSource()).getValue());
        }
    };

    ChangeListener frameRateEvent = new ChangeListener() {
        @Override
        public void stateChanged(ChangeEvent e) {
            setTimeScale(((JSlider)e.getSource()).getValue());
        }
    };

    private ArrayList<BufferedImage> tape = new ArrayList<BufferedImage>();
    private JSlider progress = new JSlider(JSlider.HORIZONTAL, 0, tape.size(), 0);
    private int timeScale = 1000;
    private int currentFrame = 0;
    private boolean repeatMode = false;
    private boolean isPlaying = false;
    private Timer timer = new Timer(timeScale, timerEvent);
    private ImageCanvas canvas = new ImageCanvas(640, 480);
    private JButton playPauseButton, fastForward, rewind, toFile;
    private JToggleButton repeat;
    private BufferedImage playIcon, pauseIcon, rewindIcon, fastForwardIcon, repeatIcon, toFileIcon;
    private JSlider frameRateSlider = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);


    public void setTimeScale(int f){
        this.timeScale = f;
        timer.setDelay(this.timeScale);
    }

    public int getTimeScale(){
        return this.timeScale;
    }

    public void setTape(ArrayList<BufferedImage> images){
        tape = images;
        refreshProgress();
    }

    public void addFrame(BufferedImage frame){
        tape.add(frame);
        refreshProgress();
    }

    public void clearTape(){
        tape.clear();
        refreshProgress();
    }

    public void setCurrentFrame(int f){
        currentFrame = f;
        refreshCanvas();
        refreshProgress();
    }

    private void refreshProgress(){
        progress.setMinimum(0);
        progress.setMaximum(tapeSize()>0?(tapeSize()-1):0);
        progress.setValue(currentFrame);
    }

    private void refreshCanvas(){
        if (!isTapeEmpty()){
            canvas.setImage(tape.get(currentFrame));
        }
    }

    public int tapeSize(){
        return tape.size();
    }

    public boolean isTapeEmpty(){
        return tape.size() == 0;
    }

    public void startAnimation(){
        isPlaying = true;
        playPauseButton.setIcon(new ImageIcon(pauseIcon));
        timer.start();
    }

    public void stopAnimation(){
        isPlaying = false;
        playPauseButton.setIcon(new ImageIcon(playIcon));
        timer.stop();
    }

    public Viewer(){
        /* Icons loading */
        try {
            playIcon = ImageIO.read(new File("Resources/Viewer/play.png"));
            pauseIcon = ImageIO.read(new File("Resources/Viewer/pause.png"));
            rewindIcon = ImageIO.read(new File("Resources/Viewer/rewind.png"));
            fastForwardIcon = ImageIO.read(new File("Resources/Viewer/fast_forward.png"));
            repeatIcon = ImageIO.read(new File("Resources/Viewer/repeat.png"));
            toFileIcon = ImageIO.read(new File("Resources/Viewer/tofile.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(new BorderLayout());
        setTitle("Animation Viewer");
        getContentPane().add(canvas, BorderLayout.CENTER);


        JPanel controlPanel = new JPanel(new FlowLayout());

        /* Setting Play-Pause button */
        playPauseButton = new JButton(new ImageIcon(playIcon));
        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (isPlaying) {
                    stopAnimation();
                } else {
                    startAnimation();
                }
            }
        });

        /* Setting rewind icon */
        rewind = new JButton(new ImageIcon(rewindIcon));
        rewind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setCurrentFrame(0);
                stopAnimation();
            }
        });

        /* Setting fast-forward icon */
        fastForward = new JButton(new ImageIcon(fastForwardIcon));
        fastForward.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setCurrentFrame(tapeSize()-1);
                stopAnimation();
            }
        });

        /* Setting repeat button*/
        repeat = new JToggleButton(new ImageIcon(repeatIcon));
        repeat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                repeatMode = !repeatMode;
                refreshCanvas();
                refreshProgress();
            }
        });

        /* Setting output button */
        toFile = new JButton(new ImageIcon(toFileIcon));
        toFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    exportToAvi(new File("viewer-jpg.avi"), AVIOutputStream.VideoFormat.JPG, 24, 1f, timeScale, canvas.getWidth(), canvas.getHeight());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        JButton toXML = new JButton("XML");
        toXML.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        /* Setting progress bar */
        progress.setMinorTickSpacing(1);
        progress.setPaintTicks(true);
        progress.setPaintLabels(true);
        progress.setFont(new Font("Serif", Font.ITALIC, 15));
        progress.setPreferredSize(new Dimension(500, 40));
        progress.addChangeListener(progressEvent);
        refreshProgress();

        /* Setting framerate slider */
        frameRateSlider.setMinorTickSpacing(10);
        frameRateSlider.setPaintTicks(true);
        frameRateSlider.setPaintLabels(true);
        frameRateSlider.setPreferredSize(new Dimension(100, 40));
        frameRateSlider.addChangeListener(frameRateEvent);

        controlPanel.add(frameRateSlider);
        controlPanel.add(rewind);
        controlPanel.add(playPauseButton);
        controlPanel.add(fastForward);
        controlPanel.add(repeat);
        controlPanel.add(toFile);
        controlPanel.add(toXML);
        controlPanel.add(progress);


        getContentPane().add(controlPanel, BorderLayout.SOUTH);
    }


    private void exportToAvi(File file, AVIOutputStream.VideoFormat format, int depth, float quality, int timescale, int width, int height) throws IOException {
        System.out.println("Writing " + file);
        AVIOutputStream out = null;
        Graphics2D g = null;
        try {
            out = new AVIOutputStream(file, format, depth);
            out.setVideoCompressionQuality(quality);

            out.setTimeScale(timescale);
            out.setFrameRate(30);

            Random rnd = new Random(0); // use seed 0 to get reproducable output
            BufferedImage img;
            switch (depth) {
                case 24:
                default: {
                    img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    break;
                }
                case 8: {
                    byte[] red = new byte[256];
                    byte[] green = new byte[256];
                    byte[] blue = new byte[256];
                    for (int i = 0; i < 255; i++) {
                        red[i] = (byte) rnd.nextInt(256);
                        green[i] = (byte) rnd.nextInt(256);
                        blue[i] = (byte) rnd.nextInt(256);
                    }
                    rnd.setSeed(0); // set back to 0 for reproducable output
                    img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_INDEXED, new IndexColorModel(8, 256, red, green, blue));
                    break;
                }
                case 4: {
                    byte[] red = new byte[16];
                    byte[] green = new byte[16];
                    byte[] blue = new byte[16];
                    for (int i = 0; i < 15; i++) {
                        red[i] = (byte) rnd.nextInt(16);
                        green[i] = (byte) rnd.nextInt(16);
                        blue[i] = (byte) rnd.nextInt(16);
                    }
                    rnd.setSeed(0); // set back to 0 for reproducable output
                    img = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY, new IndexColorModel(4, 16, red, green, blue));
                    break;
                }
            }
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.clearRect(0, 0, img.getWidth(), img.getHeight());

            for (BufferedImage frame : tape) {
                AffineTransform resize = new AffineTransform();

                resize.scale((double)width/(double)frame.getWidth(), (double)height/(double)frame.getHeight());
                AffineTransformOp scaleOp = new AffineTransformOp(resize, AffineTransformOp.TYPE_BILINEAR);
                g.drawImage(frame, scaleOp, 0, 0);
                out.writeFrame(img);
            }

        } finally {
            if (g != null) {
                g.dispose();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
