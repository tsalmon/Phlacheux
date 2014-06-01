import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

import model.movable.*;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.PathIterator;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.TransferHandler;

import model.movable.circle.Circle;
import model.movable.line.Line;
import model.movable.line.Segment;
import model.movable.polygon.*;


public class ImgTransferHandler extends TransferHandler {
	private static final long serialVersionUID = 1L;
	private DataFlavor cdf;
	private Placheux.PanElem dropPanel;

	public ImgTransferHandler(Placheux.PanElem p) {
		cdf = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType+
				";class="+Color.class.getName(),"Couleur java");
		dropPanel = p;
	}
	public boolean canImport(TransferHandler.TransferSupport ts) {
		if (!ts.isDrop()) return false;
		return ts.isDataFlavorSupported(cdf);
	}

	public ArrayList<PointPlacheux> conversionShapeToArrayList(Shape s){
		ArrayList<PointPlacheux> liste = new ArrayList<PointPlacheux>();
		
		PathIterator pi = s.getPathIterator(null);

		while (pi.isDone() == false) {
		  double[] c = new double[2];
		  int type = pi.currentSegment(c);
		  if(type < 2 ) liste.add(new PointPlacheux(c[0],c[1]));
		  pi.next();
		}
		
		return liste;
	}

	public Figure addTriangleEqui(){
		Shape s = dropPanel.draw_equi();
		ArrayList<PointPlacheux> points = conversionShapeToArrayList(s);		
		return new EquilateralTriangle(points.get(1), points.get(2));
	}
	
	public Figure addPolygonPerso(Shape s){
		ArrayList<PointPlacheux> points = 
				conversionShapeToArrayList(s);
		return new PolygonPerso(points);
	}
	
	public Figure nouvelleFigure(int id_fig, int x, int y){
		Figure f = null;
		switch(id_fig){
		case 0: return new Square(50, new PointPlacheux(x, y));
		case 1: return new Rectangle(100, 50, new PointPlacheux(x, y));
		case 2: return new Circle(new PointPlacheux(x, y), 50);
		case 3: return addPolygonPerso(dropPanel.draw_cross());
		case 4: ;/*TriangleEqui*/ return addTriangleEqui();
		case 5: ;/*ligne*/ return new Segment(new PointPlacheux(x, y), new PointPlacheux(x+50, y+50));
		case 6: ;/*Fleche*/ return addPolygonPerso(dropPanel.draw_arrow());
		case 7: ;/*Star*/ dropPanel.init_a_b(x+10, y+10); 
							return addPolygonPerso(dropPanel.draw_star());
		}
		return f;
	}
	
	public boolean importData(TransferHandler.TransferSupport ts) {
		Transferable t = ts.getTransferable();
		int x = ts.getDropLocation().getDropPoint().x;
		int y = ts.getDropLocation().getDropPoint().y;
		try {
			int id_fig = (Integer)t.getTransferData(cdf);
			dropPanel.init_x_y(x, y);
			dropPanel.init_a_b(x+50, y+50);
			System.out.println(id_fig + " (" + x + " " + y + ")");
			dropPanel.data.addMovable(nouvelleFigure(id_fig, x, y));
			dropPanel.createNodes();
			dropPanel.repaint();
			//System.out.println(dropPanel.data);
			
			return true;
		} catch(UnsupportedFlavorException ue) {
			ue.printStackTrace();
			return false;
		} catch(IOException ie) {
			ie.printStackTrace();
			return false;
		}
	}
}
