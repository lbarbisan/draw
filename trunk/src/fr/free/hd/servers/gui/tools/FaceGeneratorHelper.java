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

import org.springframework.richclient.progress.ProgressMonitor;

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
	
	public static int initialWidthSize = 400;
		
	private static Dimension dimension  = null;
	
	public static void ClearCache()
	{
		cachedFinalImage.clear();
		cachedImage.clear();
	}
	
    public static Image Create(Phonem phonem, Face face, ProgressMonitor pm) {
    	
    	if(!cachedFinalImage.containsKey(phonem))
    	{
	    	//Get Face
	    	Image faceImage = getFace();
	    	if(pm!= null) pm.worked(20);
	    	faceImage = makeColorTransparent(faceImage, Color.GREEN);
	    	if(pm!= null) pm.worked(30);
	    	//Create final image
	    	int height = initialWidthSize* faceImage.getHeight(null) / faceImage.getWidth(null);
	    	dimension = new Dimension(initialWidthSize,height );
    		BufferedImage finalImage = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
	    	Graphics2D g2 = (Graphics2D)finalImage.createGraphics();
	    	if(pm!= null) pm.worked(40);
	    	
	    	//Draw Face
	    	g2.drawImage(faceImage, 0, 0, dimension.width, dimension.height, null);
	    	switch(phonem.getHandPosition())
	    	{
	    	case HAND_POSITION_BOUCHE:
	    		//Initial Move
	    		drawLines(g2, dimension.width*face.getBoucheX(), dimension.height*face.getBoucheY(), Color.RED);
	    		break;
	    	case HAND_POSITION_COTE:
	    		drawLines(g2, dimension.width*face.getCoteX()/1000, dimension.height*face.getCoteY()/1000, Color.RED);
	    		break;
	    	case HAND_POSITION_COU:
	            drawLines(g2, dimension.width*face.getCouX()/1000, dimension.height*face.getCouY()/1000, Color.RED);
	    		break;
	    	case HAND_POSITION_MENTON:
	            drawLines(g2, dimension.width*face.getMentonX()/1000, dimension.height*face.getMentonY()/1000, Color.RED);
	    		break;
	    	case HAND_POSITION_PAUMETTE:
	    		drawLines(g2, dimension.width*face.getPaumetteX()/1000, dimension.height*face.getPaumetteY()/1000, Color.RED);
	    		break;
	    	}
	    	if(pm!= null) pm.worked(60);
	    	//DrawMouth
	    	drawMouth(g2, phonem, face);
	    	if(pm!= null) pm.worked(80);
	    	//Draw Hand
	        drawHand(g2, phonem, face);
	        if(pm!= null) pm.worked(90);
	        
	        cachedFinalImage.put(phonem, finalImage);
    	}
    	if(pm!= null) pm.worked(100);
		return cachedFinalImage.get(phonem);
    }
    
    private static void drawHand(Graphics2D g, Phonem phonem, Face face)
    {
    	//Hand picture selectionne
    	String handImagePath = null;
    	switch(phonem.getHandPosition())
    	{
    	case HAND_POSITION_BOUCHE:
    		handImagePath = "hand\\" + phonem.getHandKey().toString() + "_D" + ".png";	
    		break;
    	case HAND_POSITION_COTE:
    		handImagePath = "hand\\" + phonem.getHandKey().toString() + "_V" + ".png";
    		break;
    	case HAND_POSITION_COU:
    		handImagePath = "hand\\" + phonem.getHandKey().toString() + "_D" + ".png";
    		break;
    	case HAND_POSITION_MENTON:
    		handImagePath = "hand\\" + phonem.getHandKey().toString() + "_D" + ".png";
    		break;
    	case HAND_POSITION_PAUMETTE:
    		handImagePath = "hand\\" + phonem.getHandKey().toString() + "_D" + ".png";
    		break;
    	}
    	
    	int anchorX = 0;
    	int anchorY = 0;
    	switch(phonem.getHandKey())
    	{
    	case HAND_KEY_1M:
    		anchorX = face.getAnchorX_1M();
    		anchorY = face.getAnchorY_1M();
    		break;
    	case HAND_KEY_2M:
    		anchorX = face.getAnchorX_2M();
    		anchorY = face.getAnchorY_2M();
    		break;
    	case HAND_KEY_2V:
    		anchorX = face.getAnchorX_2V();
    		anchorY = face.getAnchorY_2V();
    		break;
    	case HAND_KEY_3D:
    		anchorX = face.getAnchorX_3D();
    		anchorY = face.getAnchorY_3D();
    		break;
    	case HAND_KEY_3G:
    		anchorX = face.getAnchorX_3G();
    		anchorY = face.getAnchorY_3G();
    		break;
    	case HAND_KEY_4G:
    		anchorX = face.getAnchorX_4G();
    		anchorY = face.getAnchorY_4G();
    		break;
    	case HAND_KEY_5M:
    		anchorX = face.getAnchorX_5M();
    		anchorY = face.getAnchorY_5M();
    		break;
    	case HAND_KEY_2D :
    		anchorX = face.getAnchorX_2D();
    		anchorY = face.getAnchorY_2D();
    		break;
    	}
    	
    	//Hand picture loading
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
		
		
		// Rescaling
		int width = 0;
		int height = 0;
		switch(phonem.getHandPosition())
    	{
    	case HAND_POSITION_BOUCHE:
    		height = face.getBoucheRatio()*dimension.height/1000;
    		break;
    	case HAND_POSITION_COTE:
    		height = face.getCoteRatio()*dimension.height/1000;
    		break;
    	case HAND_POSITION_COU:
    		height = face.getCouRatio()*dimension.height/1000;
    		break;
    	case HAND_POSITION_MENTON:
    		height = face.getMentonRatio()*dimension.height/1000;
    		break;
    	case HAND_POSITION_PAUMETTE:
    		height = face.getPaumetteRatio()*dimension.height/1000;
    		break;
    	}
		
		width = height * hand.getWidth(null)/ hand.getHeight(null);
		hand = getScaledImage(hand, phonem, face, width, height);
		
		//Translate
    	switch(phonem.getHandPosition())
    	{
    	case HAND_POSITION_BOUCHE:
    		//Initial Move
    		g.translate(dimension.width*face.getBoucheX() /1000-dimension.width*anchorX/1000,
    					dimension.height*face.getBoucheY() /1000-dimension.height*anchorY/1000);
    		g.rotate(Math.toRadians(90), dimension.width*anchorX/1000, dimension.height*anchorY/1000);
    		break;
    	case HAND_POSITION_COTE:
    		g.translate(dimension.width*face.getCoteX() /1000-dimension.width*anchorX/1000,
						dimension.height*face.getCoteY() /1000-dimension.height*anchorY/1000);
    		break;
    	case HAND_POSITION_COU:
            g.translate(dimension.width*face.getCouX() /1000-dimension.width*anchorX/1000,
						dimension.height*face.getCouY() /1000-dimension.height*anchorY/1000);
            g.rotate(Math.toRadians(90), dimension.width*anchorX/1000, dimension.height*anchorY/1000);
    		break;
    	case HAND_POSITION_MENTON:
            g.translate(dimension.width*face.getMentonX() /1000-dimension.width*anchorX/1000,
						dimension.height*face.getMentonY() /1000-dimension.height*anchorY/1000);
            g.rotate(Math.toRadians(45), dimension.width*anchorX/1000, dimension.height*anchorY/1000);
    		break;
    	case HAND_POSITION_PAUMETTE:
    		g.translate(dimension.width*face.getPaumetteX() /1000-dimension.width*anchorX/1000,
						dimension.height*face.getPaumetteY() /1000 -dimension.height*anchorY/1000);
    		g.rotate(Math.toRadians(60), dimension.width*anchorX/1000, dimension.height*anchorY/1000);
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
    			mouth = getScaledImage(mouth, null, null, width, height);
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
    	String faceImagePath = "visage.png";
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
     * @param phonem 
     * @param w - desired width
     * @param h - desired height
     * @return - the new resized image
     */
    private static Image getScaledImage(Image srcImg, Phonem phonem, Face face, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        if(phonem!=null)
        {
	        int anchorX = 0;
    		int anchorY = 0;
	        switch(phonem.getHandKey())
	    	{
	    	case HAND_KEY_1M:
	    		anchorX = face.getAnchorX_1M();
	    		anchorY = face.getAnchorY_1M();
	    		break;
	    	case HAND_KEY_2M:
	    		anchorX = face.getAnchorX_2M();
	    		anchorY = face.getAnchorY_2M();
	    		break;
	    	case HAND_KEY_2V:
	    		anchorX = face.getAnchorX_2V();
	    		anchorY = face.getAnchorY_2V();
	    		break;
	    	case HAND_KEY_3D:
	    		anchorX = face.getAnchorX_3D();
	    		anchorY = face.getAnchorY_3D();
	    		break;
	    	case HAND_KEY_3G:
	    		anchorX = face.getAnchorX_3G();
	    		anchorY = face.getAnchorY_3G();
	    		break;
	    	case HAND_KEY_4G:
	    		anchorX = face.getAnchorX_4G();
	    		anchorY = face.getAnchorY_4G();
	    		break;
	    	case HAND_KEY_5M:
	    		anchorX = face.getAnchorX_5M();
	    		anchorY = face.getAnchorY_5M();
	    		break;
	    	case HAND_KEY_2D :
	    		anchorX = face.getAnchorX_2D();
	    		anchorY = face.getAnchorY_2D();
	    		break;
	    	}
	        
	        anchorX =  dimension.width*anchorX/1000;
	        anchorY = dimension.height*anchorY/1000;
	        drawLines(g2, anchorX, anchorY, Color.BLUE);
        }
        g2.dispose();
        return resizedImg;
    }
    
    private static void drawLines(Graphics2D g, int anchorX, int anchorY, Color color)
    {
    	/*g.setColor(color);
    	g.drawLine(anchorX , anchorY-10, anchorX, anchorY+ 10);
        g.drawLine(anchorX-10, anchorY, anchorX+10, anchorY);*/
    }
}
