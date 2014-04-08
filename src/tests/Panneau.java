package tests;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

import javax.swing.JPanel;

public class Panneau extends JPanel {
	private static final long serialVersionUID = 1L;
	private int posX = 0;
	private int posY = 0;
	private int theta;
	private int delta;
	private GeneralPath dessin;
	
	Panneau(){
		Font f = new Font("Serif", Font.BOLD, 100);
	    FontRenderContext frc = new FontRenderContext(null, false, false);

	    dessin = new GeneralPath();

	    TextLayout tl = new TextLayout("LOL",f,frc);
	    Shape s = tl.getOutline(null);
	    dessin.append(s.getPathIterator(null),false);
	    int l = tl.getPixelBounds(frc,0,0).width;
	    int h = tl.getPixelBounds(frc,0,0).height;
	    dessin.moveTo(0,20);
	    dessin.lineTo(l,20);
	    dessin.lineTo(l,30);
	    dessin.lineTo(0,30);
	    dessin.lineTo(0,20);

	    dessin.closePath();
	}
	
	public void paintComponent(Graphics g) {
	    Graphics2D g2 = (Graphics2D)g;
	    AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
	    g2.setTransform(at);
	    g2.rotate(delta);
	    g2.translate(2, 2);
	    g2.draw(dessin);
	}

	public int getPosX() {
		return posX;
	}

	public void setTheta(int Theta){
		this.theta = Theta;
	}

	public int getTheta(){
		return this.theta;
	}

	public void setDelta(int Delta){
		this.delta = Delta;
	}

	public int getDelta(){
		return this.delta;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
}