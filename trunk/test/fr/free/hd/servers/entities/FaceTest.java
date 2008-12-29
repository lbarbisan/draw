package fr.free.hd.servers.entities;

import org.junit.Assert;
import org.junit.Test;

public class FaceTest {

	@Test
	public void testSetterGetter() {
		Face face = new Face();
		face.setBoucheX(1);
		face.setBoucheY(2);
		face.setCoteX(3);
		face.setCoteY(4);
		face.setCouX(5);
		face.setCouY(6);
		face.setBoucheRatio(7);
		face.setId(8);
		face.setMentonX(9);
		face.setMentonY(10);
		face.setMouthRatio(11);
		face.setMouthXPercent(12);
		face.setMouthYPercent(13);
		face.setPaumetteX(14);
		face.setPaumetteY(15);
		face.setPicture("Test");
		
		Assert.assertEquals(1, face.getBoucheX());
		Assert.assertEquals(2, face.getBoucheY());
		Assert.assertEquals(3, face.getCoteX());
		Assert.assertEquals(4, face.getCoteY());
		Assert.assertEquals(5, face.getCouX());
		Assert.assertEquals(6, face.getCouY());
		Assert.assertEquals(7, face.getBoucheRatio());
		Assert.assertEquals((Integer)8, face.getId());
		Assert.assertEquals(9, face.getMentonX());
		Assert.assertEquals(10, face.getMentonY());
		Assert.assertEquals(11, face.getMouthRatio());
		Assert.assertEquals(12, face.getMouthXPercent());
		Assert.assertEquals(13, face.getMouthYPercent());
		Assert.assertEquals(14, face.getPaumetteX());
		Assert.assertEquals(15, face.getPaumetteY());
		Assert.assertEquals("Test", face.getPicture());
	}

}
