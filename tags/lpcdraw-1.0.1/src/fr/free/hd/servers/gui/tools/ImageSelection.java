package fr.free.hd.servers.gui.tools;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ImageSelection implements Transferable {

	  public ImageSelection(Image image) {
	    this.image = image;
	  }
	  public DataFlavor[] getTransferDataFlavors() {
	    return SUPPORTED_FLAVORS;
	  }
	  public boolean isDataFlavorSupported(DataFlavor flavor) {
	    return DataFlavor.imageFlavor.equals(flavor);
	  }
	  public Object getTransferData(DataFlavor flavor)
	    throws UnsupportedFlavorException,IOException {
	    
	    if (!isDataFlavorSupported(flavor)) {
	      throw new UnsupportedFlavorException(flavor);
	    }
	    return image;
	  }
	  private final Image image;
	  private static final DataFlavor[] SUPPORTED_FLAVORS=
	    new DataFlavor[] {DataFlavor.imageFlavor};
}

