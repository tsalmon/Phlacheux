import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.TransferHandler;


public class ImgTransferHandler extends TransferHandler {
	private DataFlavor cdf;
	private JPanel dropPanel;

	public ImgTransferHandler(JPanel p) {
		cdf = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType+
				";class="+Color.class.getName(),"Couleur java");
		dropPanel = p;
	}
	public boolean canImport(TransferHandler.TransferSupport ts) {
		if (!ts.isDrop()) return false;
		return ts.isDataFlavorSupported(cdf);
	}
	public boolean importData(TransferHandler.TransferSupport ts) {
		Transferable t = ts.getTransferable();
		try {
			int id_fig = (int)t.getTransferData(cdf);
			System.out.println(id_fig);
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
