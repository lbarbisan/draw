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

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.dialog.CloseAction;

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
		c.weighty = 0.75;
		c.weightx = 0.15;
		c.fill = GridBagConstraints.BOTH;
		JList facesList = new JList();
		view.add(facesList, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		JButton btnNew = new JButton("Nouveau");
		view.add(btnNew, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		JButton btnModified = new JButton("Modifier");
		view.add(btnModified, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		JButton btnDelete = new JButton("Delete");
		view.add(btnDelete, c);
		
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 4;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.60;
		JLabel lblFace = new JLabel("Face");
		view.add(lblFace, c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.15;
		c.fill = GridBagConstraints.BOTH;
		JPanel pnlHand = new JPanel(new FlowLayout());
		view.add(pnlHand, c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.gridheight = 3;
		c.fill = GridBagConstraints.BOTH;
		JPanel pnlMouth = new JPanel(new FlowLayout());
		view.add(pnlMouth, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 2;
		c.fill = GridBagConstraints.BOTH;
		JTextField txtPath = new JTextField();
		view.add(txtPath, c);
		
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 4;
		c.fill = GridBagConstraints.BOTH;
		JButton btnBrownse= new JButton("Browse");
		view.add(btnBrownse, c);
		
		return view;
	}
}