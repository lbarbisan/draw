package fr.free.hd.servers.gui.tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
import fr.free.hd.servers.entities.Face;
import fr.free.hd.servers.entities.Phonem;

public class FaceGeneratorHelper {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911569452984025191L;

	private static Map<String, Image> cachedImage = new HashMap<String, Image>();
	private static Map<Phonem, Image> cachedFinalImage = new HashMap<Phonem, Image>();
	
	private static int initialHeightSize = 200;
		
	private static Dimension dimension  = null;
	
    public static Image Create(Phonem phonem, Face face) {
    	
    	if(!cachedFinalImage.containsKey(phonem))
    	{
	    	//Get Face
	    	Image faceImage = getFace();
	    	
	    	//Create final image
	    	int width = initialHeightSize* faceImage.getWidth(null) / faceImage.getHeight(null);
	    	dimension = new Dimension(width, initialHeightSize);
    		BufferedImage finalImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D g2 = (Graphics2D)finalImage.createGraphics();
	    	
	    	//Draw Face
	    	g2.drawImage(faceImage, 0, 0, dimension.width, dimension.height, null);
	    	//DrawMouth
	    	drawMouth(g2, phonem, face);
	    	//Draw Hand
	        drawHand(g2, phonem, face);
	        cachedFinalImage.put(phonem, finalImage);
    	}
		return cachedFinalImage.get(phonem);
    }
    
    private static void drawHand(Graphics2D g, Phonem phonem, Face face)
    {
    	String handImagePath = "hand\\" + phonem.getHandKey().toString() + ".JPG";
    	Image hand = null;
    	if(!cachedImage.containsKey(handImagePath))
    	{
    		try {
				hand = ImageIO.read(LPCDraw.class.getResource(handImagePath));
				int width = face.getHandRatio()*dimension.width/1000;
    			int height = width * hand.getHeight(null)/ hand.getWidth(null);
    			hand = getScaledImage(hand, width, height);
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
    		g.translate(dimension.width*face.getBoucheX() /1000,
    					dimension.height*face.getBoucheY() /1000);
            g.rotate(Math.toRadians(90));
    		break;
    	case HAND_POSITION_COTE:
    		g.translate(dimension.width*face.getCoteX() /1000,
						dimension.height*face.getCoteY() /1000);
    		break;
    	case HAND_POSITION_COU:
            g.translate(dimension.width*face.getCouX() /1000,
						dimension.height*face.getCouY() /1000);
            g.rotate(Math.toRadians(90));
    		break;
    	case HAND_POSITION_MENTON:
            g.translate(dimension.width*face.getMentonX() /1000,
						dimension.height*face.getMentonY() /1000);
            g.rotate(Math.toRadians(90));
    		break;
    	case HAND_POSITION_PAUMETTE:
    		g.translate(dimension.width*face.getPaumetteX() /1000,
						dimension.height*face.getPaumetteY() /1000);
    		g.rotate(Math.toRadians(60));
    		break;
    	}
        g.drawImage(hand, 0, 0, null);
    }
    
    private static void drawMouth(Graphics2D g, Phonem phonem, Face face)
    {
    	String mouthImagePath = "mouth/" + phonem.getMouthVowel().toString() + ".png";
    	Image mouth = null;
    	if(!cachedImage.containsKey(mouthImagePath))
    	{
    		try {
    			mouth = ImageIO.read(LPCDraw.class.getResource(mouthImagePath));
    			mouth = makeColorTransparent(mouth, Color.GREEN);	
    			int width = face.getMouthRatio()*dimension.width/1000;
    			int height = width * mouth.getHeight(null)/ mouth.getWidth(null);
    			mouth = getScaledImage(mouth, width, height);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
            
            cachedImage.put(mouthImagePath, mouth);
       	
    	}
    	else
    	{
    		mouth = cachedImage.get(mouthImagePath);
    	}
        
    	g.drawImage(mouth,	face.getMouthXPercent()*dimension.width/1000 - mouth.getWidth(null)/2,
    						face.getMouthYPercent()*dimension.height/1000 - mouth.getHeight(null)/2, null);
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
    
    
    /**
     * Resizes an image using a Graphics2D object backed by a BufferedImage.
     * @param srcImg - source image to scale
     * @param w - desired width
     * @param h - desired height
     * @return - the new resized image
     */
    private static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
