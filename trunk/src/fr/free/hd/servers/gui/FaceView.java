/*
 * Copyright 2002-2004 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.free.hd.servers.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.dialog.CloseAction;

import fr.free.hd.servers.dao.FaceDAO;
import fr.free.hd.servers.entities.Face;
import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;
import fr.free.hd.servers.gui.tools.FaceGeneratorHelper;

/**
 * Shows the owners and their pets in a tree structure.
 * 
 * Several dialogs are used to show detail information or messages. Notice that
 * we're explicitly setting {@link CloseAction}s to either dispose or hide the
 * dialog as an example. Check out the default behavior of the dialog type that
 * you use to determine if you need to specify this as well.
 * 
 * @author Keith Donald
 * @author Jan Hoskens
 */
public class FaceView extends AbstractView implements ApplicationListener {

	private FaceDAO facesDAO;
	private Face face;
	private HandPositionEnum position;
	private JLabel lblFace;
	
	public void componentClosed() {
	}

	@Override
	public void componentFocusGained() {
	}

	@Override
	public void componentFocusLost() {
	}

	@Override
	public void componentOpened() {
	}

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
	}

	@Override
	protected JComponent createControl() {
		final GridBagLayout layout = new GridBagLayout();
		final JPanel view = new JPanel(layout);

		//Face list
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 3;
		c.weighty = 0.75;
		c.weightx = 0.15;
		c.fill = GridBagConstraints.BOTH;
		JList facesList = CreateList(); 
		view.add(facesList, c);
		
		// New button
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		JButton btnNew = new JButton("Nouveau");
		btnNew.setEnabled(false);
		view.add(btnNew, c);
		
		// Save button
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		JButton btnModified = new JButton("Modifier");
		btnModified.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				facesDAO.storeFace(face);
			}});
		view.add(btnModified, c);
		
		//Draw Face
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 5;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.60;
		lblFace = new JLabel();
		view.add(lblFace, c);
		
		//Hand Panel
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.15;
		c.fill = GridBagConstraints.BOTH;
		JPanel pnlHand = createPosition();
		view.add(pnlHand, c);
		
		//Mouth Panel
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.weightx = 0.15;
		c.fill = GridBagConstraints.BOTH;
		JPanel pnlMouth = new JPanel(); // createKind();
		view.add(pnlMouth, c);
		
		// Picture filename
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		JTextField txtPath = new JTextField();
		view.add(txtPath, c);
	
		//Browse Button
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 5;
		c.fill = GridBagConstraints.BOTH;
		JButton btnBrownse= new JButton("Browse");
		view.add(btnBrownse, c);
		
		return view;
	}
	
	
	private JList CreateList()
	{
		Collection<Face> faces = facesDAO.getFaces();
		final JList list = new JList(faces.toArray());
		list.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(e.getValueIsAdjusting()==false)
				{
					face = (Face)list.getSelectedValue();
				}
			}
			
		});
		return list;
	}
	
	public FaceDAO getFacesDAO() {
		return facesDAO;
	}

	public void setFacesDAO(FaceDAO facesDao) {
		this.facesDAO = facesDao;
	}
	
	private JPanel createPosition()
	{
		JPanel panel = new JPanel(new GridLayout(4,0));
		final JSlider slider =  new JSlider();
		final JSlider sliderX =  new JSlider();
		final JSlider sliderY =  new JSlider();
		slider.setMinimum(0);
		slider.setMaximum(1000);
		sliderX.setMinimum(0);
		sliderX.setMaximum(1000);
		sliderY.setMinimum(0);
		sliderY.setMaximum(1000);
		
		final JComboBox box = new JComboBox(HandPositionEnum.values());
		box.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent e) {
				position = (HandPositionEnum) e.getItem();
				updateLabel();
			}});
		panel.add(box);
		
		
		slider.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(position)
				{
				case HAND_POSITION_BOUCHE:
					face.setBoucheRatio(slider.getValue());
					break;
				case HAND_POSITION_COTE:
					face.setCoteRatio(slider.getValue());
					break;
				case HAND_POSITION_COU:
					face.setCouRatio(slider.getValue());
					break;
				case HAND_POSITION_MENTON:
					face.setMentonRatio(slider.getValue());
					break;
				case HAND_POSITION_PAUMETTE:
					face.setPaumetteRatio(slider.getValue());
					break;
				}
				updateLabel();
			}
		});
		sliderX.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(position)
				{
				case HAND_POSITION_BOUCHE:
					face.setBoucheX(sliderX.getValue());
					break;
				case HAND_POSITION_COTE:
					face.setCoteX(sliderX.getValue());
					break;
				case HAND_POSITION_COU:
					face.setCouX(sliderX.getValue());
					break;
				case HAND_POSITION_MENTON:
					face.setMentonX(sliderX.getValue());
					break;
				case HAND_POSITION_PAUMETTE:
					face.setPaumetteX(sliderX.getValue());
					break;
				}
				updateLabel();
			}
		});
		sliderY.addChangeListener(new ChangeListener(){
			@Override
			public void stateChanged(ChangeEvent e) {
				switch(position)
				{
				case HAND_POSITION_BOUCHE:
					face.setBoucheY(sliderY.getValue());
					break;
				case HAND_POSITION_COTE:
					face.setCoteY(sliderY.getValue());
					break;
				case HAND_POSITION_COU:
					face.setCouY(sliderY.getValue());
					break;
				case HAND_POSITION_MENTON:
					face.setMentonY(sliderY.getValue());
					break;
				case HAND_POSITION_PAUMETTE:
					face.setPaumetteY(sliderY.getValue());
					break;
				}
				updateLabel();
			}
		});
		panel.add(slider);
		panel.add(sliderX);
		panel.add(sliderY);
		return panel;
	}
	
	private void updateLabel()
	{
		if(position!=null)
		{
		Phonem phonem = new Phonem("Demo", HandKeyEnum.HAND_KEY_2M, position, MouthVowelEnum.MOUTH_VOWEL_A);
		FaceGeneratorHelper.initialWidthSize = 400;
		FaceGeneratorHelper.ClearCache();
		Image image = FaceGeneratorHelper.Create(phonem, face);
		lblFace.setIcon(new ImageIcon(image));
		}
	}
}