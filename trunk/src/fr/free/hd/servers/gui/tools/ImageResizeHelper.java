package fr.free.hd.servers.gui.tools;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class ImageResizeHelper {

	public static Image resize(Image image, int thumbHeight, int thumbWidth)
	{
		MediaTracker mediaTracker = new MediaTracker(new Container());
		mediaTracker.addImage(image, 0);
		try {
			mediaTracker.waitForID(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
		double thumbRatio = (double)thumbWidth / (double)thumbHeight;
		int imageWidth = image.getWidth(null);
		int imageHeight = image.getHeight(null);
		double imageRatio = (double)imageWidth / (double)imageHeight;
		if (thumbRatio < imageRatio) {
			thumbHeight = (int)(thumbWidth / imageRatio);
		} else {
			thumbWidth = (int)(thumbHeight * imageRatio);
		}
		
	    // draw original image to thumbnail image object and
	    // scale it to the new size on-the-fly
	    BufferedImage thumbImage = new BufferedImage(thumbWidth, 
	      thumbHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = thumbImage.createGraphics();
	    graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	    		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
    
	    return thumbImage;
	}
	
	public static Image fitToScreen(Image source)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int insety = (screenSize.height - source.getHeight(null))/2;
 		int insetx = (screenSize.width - source.getWidth(null))/2;
 		int width = source.getWidth(null);
 		int height = source.getHeight(null);
 		
 		//TOO big
 		if(insetx<0 || insety<0)
 		{
 			if(insetx < insety)
     		{
     			width = screenSize.width;
     			height = source.getHeight(null) * width / source.getWidth(null);
     			insetx=0;
     			insety=(screenSize.height - height)/2;
     		}
     		else
     		{
     			height = screenSize.height;
     			width = source.getWidth(null) * height / source.getHeight(null);
     			insety=0;
     			insetx=(screenSize.width - width)/2;
     		}
 		}
 		return resize(source, height, width);
	}
}
