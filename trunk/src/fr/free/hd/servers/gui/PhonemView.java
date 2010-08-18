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

import java.awt.BorderLayout;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.command.support.GlobalCommandIds;
import org.springframework.richclient.dialog.CloseAction;

import fr.free.hd.servers.dao.FaceDAO;
import fr.free.hd.servers.dao.PhonemsDAO;
import fr.free.hd.servers.entities.Face;
import fr.free.hd.servers.entities.Phonem;
import fr.free.hd.servers.gui.command.CopyCommandPhonem;
import fr.free.hd.servers.gui.command.FaceSelectorCommand;
import fr.free.hd.servers.gui.command.PrintCommand;

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
public class PhonemView extends AbstractView implements ApplicationListener {

	protected PhonemsDAO phonemsDAO;
	protected FaceDAO facesDao;
	protected Face face;
	protected JList list = null;
	
	protected PrintCommand printCommand = new PrintCommand();
	protected CopyCommandPhonem copyCommand = new CopyCommandPhonem();

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
		final JPanel view = new JPanel(new BorderLayout());

		Collection<Phonem> phonesList = phonemsDAO.getPhonems();
		Map<String, Phonem> mapList = new HashMap<String, Phonem>();
		for (Phonem phonem : phonesList) {
			mapList.put(phonem.getPhonem(), phonem);
		}

		final StatementListModel model = new StatementListModel(mapList);
		
		printCommand.setModel(model);
		printCommand.setFace(face);
		copyCommand.setModel(model);
		copyCommand.setFace(face);

		list = new JList(model);
		final JScrollPane sp = new JScrollPane(list);
		final JTextField field = new JTextField();
		field.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {
				model.setString(field.getText());
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				model.setString(field.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				model.setString(field.getText());
			}

		});

		final PhonemListModel phonemModel = new PhonemListModel((List<Phonem>) phonesList);
		final JList phonemList = new JList(phonemModel);
		final JScrollPane spPhonemList = new JScrollPane(phonemList);
		phonemList.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					// private int oldIndex = -1;
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (e.getValueIsAdjusting() == false) {
							Phonem innerPhonem = (Phonem) phonemModel
									.getElementAt(phonemList.getSelectedIndex());
							field.setText(field.getText()
									+ innerPhonem.getPhonem());
						}
					}
				});
		phonemList.setCellRenderer(new PhonemListRenderer());
		list.setCellRenderer(new StatementPhonemListRenderer(face));
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(1);
		
		view.add(spPhonemList, BorderLayout.WEST);
		view.add(sp, BorderLayout.CENTER);
		view.add(field, BorderLayout.SOUTH);

		field.requestFocus();
		
		return view;
	}


	public void setFaceName(String faceName) {
		face = facesDao.findFace(faceName);
	}

	@Override
	protected void registerLocalCommandExecutors(PageComponentContext context) {
		context.register("PrintCommand", printCommand);
		context.register(GlobalCommandIds.COPY, copyCommand);
		Collection<Face> faces = facesDao.getFaces(); 
		FaceSelectorCommand command = null;
		int index = 0;
		for(Face face : faces)
		{
			command = new FaceSelectorCommand(this, face, "face" + index);
			context.register("face" + index, command);
			index++;
		}
	}

	public PhonemsDAO getPhonemsDAO() {
		return phonemsDAO;
	}

	public void setPhonemsDAO(PhonemsDAO phonemsDAO) {
		this.phonemsDAO = phonemsDAO;
	}

	public FaceDAO getFacesDAO() {
		return facesDao;
	}

	public void setFacesDAO(FaceDAO facesDao) {
		this.facesDao = facesDao;
	}

	public void setFace(Face face) {
		this.face = face;
		printCommand.setFace(face);
		copyCommand.setFace(face);
		list.setCellRenderer(new StatementPhonemListRenderer(face));
	}
}