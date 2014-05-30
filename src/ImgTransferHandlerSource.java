import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;

import javax.swing.JComponent;
import javax.swing.TransferHandler;


public class ImgTransferHandlerSource extends TransferHandler {
	private DataFlavor cdf;
	private DragComponent mc;
	public ImgTransferHandlerSource(DragComponent mc) {
		cdf = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType+
				";class="+Color.class.getName(),"Couleur java");
		this.mc = mc;
	}
	public int getSourceActions(JComponent c) {
		return TransferHandler.COPY;
	}
	protected Transferable createTransferable(JComponent c) {
		DragComponent d = (DragComponent) c;
		return new ImgTransfer(d.id_fig);
	}
	protected void exportDone(JComponent c,Transferable t,int a) {}
}
