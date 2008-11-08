package fr.free.hd.servers.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import fr.free.hd.servers.LPCDraw;
import fr.free.hd.servers.entities.Phonem;

public class FaceGenerator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911569452984025191L;

	private static Map<String, Image> cachedImage = new HashMap<String, Image>();
	private static Map<Phonem, Image> cachedFinalImage = new HashMap<Phonem, Image>();
	private static Dimension dimension  = new Dimension(248, 350);
	private static MediaTracker tracker = new MediaTracker(null);
	
    public static Image Create(Phonem phonem) {
    	if(!cachedFinalImage.containsKey(phonem))
    	{
    		BufferedImage finalImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D g2 = (Graphics2D)finalImage.createGraphics();
	    	Image mouth = getMouth(phonem);
	    	Image face = getFace();
	    	g2.drawImage(face, 0, 0, dimension.width, dimension.height, null);
	    	g2.drawImage(mouth,(dimension.width - mouth.getWidth(null))/2, 70*dimension.height/100 - mouth.getHeight(null)/2, null);
	        drawHand(g2, dimension.width, dimension.height, phonem);
	        cachedFinalImage.put(phonem, finalImage);
    	}
		return cachedFinalImage.get(phonem);
    }
    
    private static void drawHand(Graphics2D g, int width, int height, Phonem phonem)
    {
    	String handImagePath = "hand\\" + phonem.getHandKey().toString() + ".JPG";
    	Image hand = null;
    	if(!cachedImage.containsKey(handImagePath))
    	{
    		try {
				hand = ImageIO.read(LPCDraw.class.getResource(handImagePath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
            hand = makeColorTransparent(hand, Color.GREEN);
            cachedImage.put(handImagePath, hand);
       	 	
    	}
    	else
    	{
    		hand = cachedImage.get(handImagePath);
    	}
    	     	
 		
    	switch(phonem.getHandPosition())
    	{
    	case HAND_POSITION_BOUCHE:
            g.translate(90,240);
            g.rotate(Math.toRadians(90));
    		break;
    	case HAND_POSITION_COTE:
    		g.translate(0,180);
    		break;
    	case HAND_POSITION_COU:
            g.translate(100,310);
            g.rotate(Math.toRadians(90));
    		break;
    	case HAND_POSITION_MENTON:
            g.translate(110,280);
            g.rotate(Math.toRadians(90));
    		break;
    	case HAND_POSITION_PAUMETTE:
    		g.translate(50,225);
    		g.rotate(Math.toRadians(60));
    		break;
    	}
        g.drawImage(hand, 0, 0, null);
    }
    
    private static Image getMouth(Phonem phonem)
    {
    	String mouthImagePath = "mouth/" + phonem.getMouthVowel().toString() + ".png";
    	Image mouth = null;
    	if(!cachedImage.containsKey(mouthImagePath))
    	{
    		try {
    			mouth = ImageIO.read(LPCDraw.class.getResource(mouthImagePath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
            //mouth = ImageResizeHelper.resize(mouth, dimension.height/4, dimension.width/4);
            //ImageResizeHelper.resize(mouth, dimension.height/4, dimension.width/4)
            mouth = makeColorTransparent(mouth, Color.GREEN);
            cachedImage.put(mouthImagePath, mouth);
       	
    	}
    	else
    	{
    		mouth = cachedImage.get(mouthImagePath);
    	}
        
        return mouth;  
    }
    
    private static Image getFace()
    {
    	String faceImagePath = "visage.jpg";
    	Image face = null;
    	if(!cachedImage.containsKey(faceImagePath))
    	{
    		try {
    			face = ImageIO.read(LPCDraw.class.getResource(faceImagePath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	}
    	else
    	{
    		face = cachedImage.get(faceImagePath);
    	}
        return face;
    }
    
    private static  Image makeColorTransparent
    (Image im, final Color color) {
    ImageFilter filter = new RGBImageFilter() {
      // the color we are looking for... Alpha bits are set to opaque
      public int markerRGB = color.getRGB() | 0xFF000000;

      public final int filterRGB(int x, int y, int rgb) {
        if ( ( rgb | 0xFF000000 ) == markerRGB ) {
          // Mark the alpha bits as zero - transparent
          return 0x00FFFFFF & rgb;
          }
        else {
          // nothing to do
          return rgb;
          }
        }
      }; 

    ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
    return Toolkit.getDefaultToolkit().createImage(ip);
    }
}
