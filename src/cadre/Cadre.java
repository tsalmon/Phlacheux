package cadre;

//Bezier.java: Bezier curve segments.
//Uses: Point2D (Section 1.5).

//Copied from Section 4.1 of
// Ammeraal, L. (1998) Computer Graphics for Java Programmers,
//    Chichester: John Wiley.

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;

import javax.swing.JFrame;
public class Cadre extends JFrame{  
	public static void main(String[] args){new Cadre();}

	Cadre(){  
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 300);
		this.add("Center", new CvBezier());
		this.setVisible(true);
	}
}

class CvBezier extends Canvas{
	int centerX, centerY;
	float rWidth = 16.0F, rHeight = 9F, pixelSize;

	void initgr() {
		Dimension d = getSize();
		int maxX = d.width - 1, maxY = d.height - 1;
		pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
		centerX = maxX/2; centerY = maxY/2;
	}

	int iX(double d){
		return (int) Math.round(centerX + d/pixelSize);
	}
	int iY(double d){
		return (int) Math.round(centerY - d/pixelSize);
	}

	public void paint(Graphics g){
		System.out.println("paint");
		initgr();
		int left = iX(-rWidth/2), right = iX(rWidth/2),
				bottom = iY(-rHeight/2), top = iY(rHeight/2);
		g.drawRect(left, top, right - left, bottom - top);
		g.drawOval(left+10, top+10, 50, 50);
	}
}