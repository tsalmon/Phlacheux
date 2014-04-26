import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;

public class Vue extends Canvas{
		int centerX, centerY;
		float rWidth = 16.0F, rHeight = 9F, pixelSize;
		float xP = 1000000, yP;
		int left, right, top, bottom;
		void initgr() {
			Dimension d = getSize();
			int maxX = d.width - 1, maxY = d.height - 1;
			pixelSize = Math.max(rWidth/maxX, rHeight/maxY);
			centerX = maxX/2; centerY = maxY/2;
		}

		  public void paint(Graphics g){
			    initgr();
			    
			    left = iX(-rWidth/2);
			    right = iX(rWidth/2);
				bottom = iY(-rHeight/2);
				top = iY(rHeight/2);
				
				g.drawRect(left, top, right - left, bottom - top);
			    if(xP != 1000000)
			      g.drawString("Logical coordinates of selected point: "
			        + xP + ", " + yP, 20, 100);
			  }

		
		int iX(double d){
			return (int) Math.round(centerX + d/pixelSize);
		}
		int iY(double d){
			return (int) Math.round(centerY - d/pixelSize);
		}
	}