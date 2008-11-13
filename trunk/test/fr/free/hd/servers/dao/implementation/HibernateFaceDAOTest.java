package fr.free.hd.servers.dao.implementation;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import fr.free.hd.servers.dao.FaceDAO;
import fr.free.hd.servers.entities.Face;

@ContextConfiguration(locations={"HibernatePhonemDAOTests-context.xml"})
public class HibernateFaceDAOTest extends AbstractDAOTests {

	@Autowired
	protected FaceDAO faceDAO;
	
	@Test
	public void testStoreFace() {
		
		Face face = new Face();
		face.setPicture("Picture.jpg");
		this.faceDAO.storeFace(face);
		Assert.assertEquals("Insert", super.countRowsInTable("FACE"), 1);
		
		face = new Face();
		face.setPicture("Picture2.jpg");
		this.faceDAO.storeFace(face);
		Assert.assertEquals("Insert", super.countRowsInTable("FACE"), 2);
	}

	
	@Test
	public void testGetFaces() {
		testStoreFace();
		Collection<Face>  faces = this.faceDAO.getFaces();
		Assert.assertEquals(2, faces.size());
	}

	@Test
	public void testFindFace() {
		testStoreFace();
		Face face = this.faceDAO.findFace("Picture.jpg");
		Assert.assertNotNull(face);
	}

	
}
