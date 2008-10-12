package fr.free.hd.servers.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import fr.free.hd.servers.LPCDraw;
import fr.free.hd.servers.entities.Phonem;

public class JPicture extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911569452984025191L;
    
	private static Map<String, Image> cachedImage = new HashMap<String, Image>();
	
    private Phonem phonem;
    
    private Dimension dimension = new Dimension(248, 350);

    public double angdeg;
    

    public JPicture(Phonem phonem)
    {
    	this.phonem = phonem;    
    }


    @Override
	public Dimension getMaximumSize() {
		// TODO Auto-generated method stub
		return dimension;
	}


	@Override
	public Dimension getMinimumSize() {
		// TODO Auto-generated method stub
		return dimension;
	}


	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return dimension;
	}

    public void paint(Graphics g) {
    	//buffer = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
    	//Graphics2D g2 = buffer.createGraphics();
    	Graphics2D g2 = (Graphics2D)g;
    	g2.drawImage(getFace(), 0, 0, dimension.width, dimension.height, null);
    	Image mouth = getMouth();
    	g2.drawImage(getMouth(),(dimension.width - mouth.getWidth(null))/2, 3*dimension.height/5 +20, null);
        
        drawHand(g2, dimension.width, dimension.height);
    }
    
    private void drawHand(Graphics2D g, int width, int height)
    {
    	String handImagePath = "hand\\" + phonem.getHandKey().toString() + ".JPG";
    	Image hand = null;
    	if(!cachedImage.containsKey(handImagePath))
    	{
    		hand = getToolkit().getImage(LPCDraw.class.getResource(handImagePath));
    		try {
              /*  MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(hand, 0);
                tracker.waitForID(0);*/
            hand = makeColorTransparent(hand, Color.GREEN);
            cachedImage.put(handImagePath, hand);
       	 } catch (Exception e) {e.printStackTrace();}
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
    		g.translate(25,180);
    		g.rotate(Math.toRadians(45));
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
    
    private Image getMouth()
    {
    	String mouthImagePath = "mouth/" + phonem.getMouthVowel().toString() + ".png";
    	Image mouth = null;
    	if(!cachedImage.containsKey(mouthImagePath))
    	{
    		mouth = getToolkit().getImage(LPCDraw.class.getResource(mouthImagePath));
    		try {
            /*    MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(mouth, 1);
                tracker.waitForID(1);*/
            //mouth = ImageResizeHelper.resize(mouth, dimension.height/4, dimension.width/4);
                //ImageResizeHelper.resize(mouth, dimension.height/4, dimension.width/4)
            mouth = makeColorTransparent(mouth, Color.GREEN);
            cachedImage.put(mouthImagePath, mouth);
       	 } catch (Exception e) {e.printStackTrace();}
    	}
    	else
    	{
    		mouth = cachedImage.get(mouthImagePath);
    	}
        
        return mouth;  
    }
    
    private Image getFace()
    {
    	String faceImagePath = "visage.jpg";
    	Image face = null;
    	if(!cachedImage.containsKey(faceImagePath))
    	{
    		face = getToolkit().getImage(LPCDraw.class.getResource(faceImagePath));
    		try {
            /*    MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(face, 2);
                tracker.waitForID(2);*/
       	 } catch (Exception e) {e.printStackTrace();}
    	}
    	else
    	{
    		face = cachedImage.get(faceImagePath);
    	}
        return face;
    }
    
    public Image makeColorTransparent
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
