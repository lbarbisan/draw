package fr.free.hd.servers.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import fr.free.hd.servers.LPCDraw;
import fr.free.hd.servers.entities.Phonem;

public class JPicture extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 911569452984025191L;
    
    private BufferedImage hand;
    private Image mouth;
    private Image face;
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
        //Face
        if (face == null) {
            face = getFace();
        }
        g.drawImage(face, 0, 0, dimension.width, dimension.height, this);
        
        
        //Mouth
        if (mouth == null) {
        	mouth = getMouth();
        }
        g.drawImage(mouth,dimension.width/4, 3*dimension.height/5, dimension.width/2, dimension.height/4, this);
        
        
        if (hand == null || hand.getWidth() != dimension.width || hand.getHeight() != dimension.height) {
        	drawHand((Graphics2D)g, dimension.width, dimension.height, 45);
        }        
    }
    
    private void drawHand(Graphics2D g, int width, int height, int deg)
    {
    	Image hand = getToolkit().getImage(LPCDraw.class.getResource("hand\\hand_cvs.jpg"));
    	 try {
             MediaTracker tracker = new MediaTracker(this);
             tracker.addImage(hand, 3);
             tracker.waitForID(3);
    	 } catch (Exception e) {}
    	     	
    	g.rotate(Math.toRadians(deg));
        g.translate(50,50);
        g.drawImage(hand, 0, 0, this);
    }
    
    private Image getMouth()
    {
        Image mouth = getToolkit().getImage(LPCDraw.class.getResource("mouth\\mouth_a.JPG"));
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(mouth, 0);
            tracker.waitForID(0);
        } catch (Exception e) {}
        
        return mouth;  
    }
    
    private Image getFace()
    {
    	Image face = getToolkit().getImage(LPCDraw.class.getResource("visage.jpg"));
        try {
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(face, 1);
            tracker.waitForID(1);
        } catch (Exception e) {}
        
        return face;
    }
}
