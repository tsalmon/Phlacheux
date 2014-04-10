package tests;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

class Boule{
	Point p;
	int r;
	Color c;

	Boule(int x, int y, int r, Color c){
		this.p = new Point(x, y);
		this.r = r;
		this.c = c;
	}    
}

class BouleAffineTransform extends Thread{
    Boule boule;
    Graphics2D g2;
    double v, v1, v2;

    BouleAffineTransform(Boule b, Graphics2D g2, double v, double v1, double v2){
        this.boule = b;
        this.g2 = g2;
        this.v = v;
        this.v1 = v1;
        this.v2 = v2;
    }

    public void run(){
        AffineTransform at = new AffineTransform();
        at.rotate(v, v1, v2);
        at.transform(boule.p, boule.p);

        g2.setColor(boule.c);
        g2.fillOval(boule.p.x, boule.p.y, boule.r, boule.r);
    }
}

public class Panneau extends JPanel {
	private static final long serialVersionUID = 1L;
	private int choix_fig;
	int np = 0; double centerX, centerY;
	float rWidth = 10.0F, rHeight = 7.5F, eps = rWidth/100F, pixelSize;
	 int posX = 0, 
			posY = 0, 
			theta = 0, 
			delta = 0; 

	private Boule b[];
	private GeneralPath dessin;
	boolean pos = true;
	
	int id_dessin;

	Panneau(int i){
		this.id_dessin = i;
		switch(i){
		case 0: this.init_titre(); break;
		case 1: this.init_boule(); break;
		}
	}

	Panneau(Point2D P0, Point2D P1, Point2D P2, Point2D P3){
		this.init_bezier( P0,  P1,  P2,  P3);
	}
	
	void initgr() {
		Dimension d = getSize();
		System.out.println(d);
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
		centerX = maxX/2; centerY = maxY/2;
	}



	void init_titre(){            
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

	void init_boule(){
		b = new Boule[9];
		b[0] = new Boule(100, 100, 40, Color.BLUE);
		b[1] = new Boule(100, 300, 40, Color.RED);
		b[2] = new Boule(300, 100, 40, Color.RED);
		b[3] = new Boule(300, 300, 40, Color.BLUE);

		b[4] = new Boule(b[0].p.x+0, b[0].p.y+0, 20, Color.RED);
		b[5] = new Boule(b[1].p.x+0, b[1].p.y+0, 20, Color.BLUE);
		b[6] = new Boule(b[2].p.x+0, b[2].p.y+0, 20, Color.BLUE);
		b[7] = new Boule(b[3].p.x+0, b[3].p.y+0, 20, Color.RED);
		b[8] = new Boule(200, 200, 10, Color.BLACK);
	}

	public void init_bezier(Point2D P0, Point2D P1, Point2D P2, Point2D P3){
		
	}	
	
	public void dessin_titre(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		AffineTransform at = AffineTransform.getTranslateInstance(posX, posY);
		g2.setTransform(at);
		g2.rotate(delta);
		g2.translate(2, 2);
		g2.draw(dessin);            
	}

	public void dessin_boule(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 0; i < 4; i++) {
            BouleAffineTransform bt = new BouleAffineTransform(b[i], g2, 0.1,200,200);
            bt.start();
//			AffineTransform at = new AffineTransform();
//			at.rotate(0.1, 200, 200);
//			at.transform(b[i].p, b[i].p);
//
//			g2.setColor(b[i].c);
//			g2.fillOval(b[i].p.x, b[i].p.y, b[i].r, b[i].r);
		}

		for(int i = 4; i < 8; i++) {
            BouleAffineTransform bt = new BouleAffineTransform(b[i], g2, -0.1,200,200);
            bt.start();
//            AffineTransform at = new AffineTransform();
//			at.rotate(-0.1, 200, 200);
//			at.transform(b[i].p, b[i].p);
//
//			g2.setColor(b[i].c);
//			g2.fillOval(b[i].p.x, b[i].p.y, b[i].r, b[i].r);
		}

		g2.setColor(b[8].c);
		g2.fillOval(b[8].p.x - b[8].r/2, b[8].p.y- b[8].r/2, b[8].r, b[8].r);


		if(b[8].r < 0 || b[8].r > 100)
			pos =!pos;
		int val = (pos ) ? 1 : -1;

		b[8].r+=val;
	}

	Point2D middle(Point2D A, Point2D B){
		return new Point2D.Double((A.getX() + B.getX())/2, (A.getY() + B.getY())/2);
	}

	public void dessin_bezier(Graphics g){
		System.out.println("Dessine");
		g.setColor(Color.BLUE);
		g.fillOval(this.posX, this.posY, 10, 10);
	}

	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);
		switch(this.id_dessin){
		case 0: this.dessin_titre(g); break;
		case 1: this.dessin_boule(g); break;
		case 2: this.dessin_bezier(g); break;
		}
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