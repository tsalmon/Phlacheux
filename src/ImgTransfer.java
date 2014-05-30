import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;


public class ImgTransfer implements Transferable {
	private int id_fig;
	private DataFlavor []dfs;
	public ImgTransfer(int id_fig) {
		dfs = new DataFlavor[1];
		dfs[0] = new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType+
				";class="+Color.class.getName(),"id fig");
		this.id_fig = id_fig;
	}
	public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException,IOException {
		if (!df.equals(dfs[0])) throw new UnsupportedFlavorException(df);
		return id_fig;
	}
	public DataFlavor [] getTransferDataFlavors() {
		return dfs;
	}
	public boolean isDataFlavorSupported(DataFlavor df) {
		return df.equals(dfs[0]);
	}
}
