package fr.free.hd.servers.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
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
	
    private BufferedImage buffer;
    private Phonem phonem;
    
    private Dimension dimension = new Dimension(248, 350);

    public double angdeg;
    

    public JPicture(Phonem phonem)
    {
    	this.phonem = phonem;
    	buffer = new BufferedImage(dimension.width, dimension.height, BufferedImage.TYPE_INT_ARGB);
    	Graphics2D g = buffer.createGraphics();
    	g.drawImage(getFace(), 0, 0, dimension.width, dimension.height, this);
        g.drawImage(getMouth(),dimension.width/4, 3*dimension.height/5, dimension.width/2, dimension.height/4, this);
        drawHand(g, dimension.width, dimension.height);
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
       g.drawImage(buffer, 0,0, this);
    }
    
    private void drawHand(Graphics2D g, int width, int height)
    {
    	String handImagePath = "hand\\" + phonem.getHandKey().toString() + ".JPG";
    	Image hand = null;
    	if(!cachedImage.containsKey(handImagePath))
    	{
    		hand = getToolkit().getImage(LPCDraw.class.getResource(handImagePath));
    		try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(hand, 2);
                tracker.waitForID(2);
       	 } catch (Exception e) {}
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
        g.drawImage(hand, 0, 0, this);
    }
    
    private Image getMouth()
    {
    	String mouthImagePath = "mouth\\" + phonem.getMouthVowel().toString() + ".JPG";
    	Image mouth = null;
    	if(!cachedImage.containsKey(mouthImagePath))
    	{
    		mouth = ImageResizeHelper.resize(getToolkit().getImage(LPCDraw.class.getResource(mouthImagePath)), dimension.height/5, dimension.width/5);
    		try {
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(mouth, 0);
                tracker.waitForID(0);
       	 } catch (Exception e) {}
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
                MediaTracker tracker = new MediaTracker(this);
                tracker.addImage(face, 1);
                tracker.waitForID(1);
       	 } catch (Exception e) {}
    	}
    	else
    	{
    		face = cachedImage.get(faceImagePath);
    	}
        return face;
    }
}
