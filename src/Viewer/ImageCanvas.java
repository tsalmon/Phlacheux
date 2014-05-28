package Viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;


public class ImageCanvas extends JPanel{

    private static final int defaultWidth = 640,
                             defaultHeight = 480;
    private BufferedImage image = new BufferedImage(defaultWidth, defaultHeight, BufferedImage.TYPE_INT_ARGB);


    public void setImage(BufferedImage img){
        image = img;
        repaint();
    }

    public BufferedImage getImage(){
        return image;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D)graphics;
        AffineTransform resize = new AffineTransform();

        resize.scale((double)getWidth()/(double)image.getWidth(), (double)getHeight()/(double)image.getHeight());
        AffineTransformOp scaleOp = new AffineTransformOp(resize, AffineTransformOp.TYPE_BILINEAR);

        g2.drawImage(image, scaleOp, 0, 0);
    }

    public ImageCanvas(){
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.green);
    }

    public ImageCanvas(int width, int height){
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.green);
    }
}
