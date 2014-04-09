package tests;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

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


public class Panneau extends JPanel {
	private static final long serialVersionUID = 1L;
	private int posX;
	private int posY;
	private int theta;
	private int delta;
        private Boule b[];
        private GeneralPath dessin;

         int id_dessin;
        
        void init_titre(){            
		Font f = new Font("Serif", Font.BOLD, 100);
		FontRenderContext frc = new FontRenderContext(null, false, false);

		int nb = 1;
		
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
            b = new Boule[4];
            b[0] = new Boule(100, 100, 40, Color.BLUE);
            b[1] = new Boule(100, 300, 40, Color.RED);
            b[2] = new Boule(300, 100, 40, Color.RED);
            b[3] = new Boule(300, 300, 40, Color.BLUE);
        }
        
	Panneau(int i){
            this.id_dessin = i;
            switch(i){
                case 0: this.init_titre(); break;
                case 1: this.init_boule(); break;
            }
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
            System.out.println("in");
            Graphics2D g2 = (Graphics2D)g;
            for(Boule bi : b) {
                
                AffineTransform at = new AffineTransform();
                at.rotate(0.1, 200, 200);
                at.transform(bi.p, bi.p);
            	
                g2.setColor(bi.c);
                g2.drawOval(bi.p.x, bi.p.y, bi.r, bi.r);
            }
            System.out.println("out");
        }
        
	public void paintComponent(Graphics g) {
            switch(this.id_dessin){
                case 0: this.dessin_titre(g); break;
                case 1: this.dessin_boule(g); break;
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