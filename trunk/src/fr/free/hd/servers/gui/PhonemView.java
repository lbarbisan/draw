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
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.richclient.application.PageComponentContext;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.dialog.CloseAction;

import fr.free.hd.servers.dao.PhonemsDAO;
import fr.free.hd.servers.entities.Phonem;

/**
 * Shows the owners and their pets in a tree structure.
 *
 * Several dialogs are used to show detail information or messages. Notice that we're explicitly
 * setting {@link CloseAction}s to either dispose or hide the dialog as an example. Check out the default behavior of
 * the dialog type that you use to determine if you need to specify this as well.
 *
 * @author Keith Donald
 * @author Jan Hoskens
 */
public class PhonemView extends AbstractView implements ApplicationListener {

	protected PhonemsDAO phonemsDAO; 
	protected PrintCommand printCommand = new PrintCommand();
	
    public void componentClosed() {}
    @Override
    public void componentFocusGained() {}
    @Override
    public void componentFocusLost() {}
    @Override
    public void componentOpened() {}
	@Override
	public void onApplicationEvent(ApplicationEvent event) {}

	
	
	@Override
	protected JComponent createControl() {
		final JPanel view = new JPanel(new BorderLayout());
        
		Collection<Phonem> phonesList = phonemsDAO.getPhonems();
		Map<String, Phonem> mapList = new HashMap<String, Phonem>();
		for(Phonem phonem : phonesList)
		{
			mapList.put(phonem.getPhonem(), phonem);
		}
		
		final PhonenListModel model = new PhonenListModel(mapList);
		printCommand.setModel(model);
		//printCommand.setEnabled(true);
		final JList list = new JList(model);
		final JScrollPane sp = new JScrollPane(list);
		final JTextField field = new JTextField();
		field.getDocument().addDocumentListener(new DocumentListener()
		{

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
		
		list.setCellRenderer(new PhonemListRenderer());
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		list.setVisibleRowCount(1);

		view.add(sp, BorderLayout.CENTER);
		view.add(field, BorderLayout.SOUTH);
        		
        return view;
	}
	@Override
	protected void registerLocalCommandExecutors(PageComponentContext context) {
		context.register("PrintCommand" , printCommand);
	}
	
	
	public PhonemsDAO getPhonemsDAO() {
		return phonemsDAO;
	}
	public void setPhonemsDAO(PhonemsDAO phonemsDAO) {
		this.phonemsDAO = phonemsDAO;
	}

}