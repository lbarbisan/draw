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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.richclient.application.support.AbstractView;
import org.springframework.richclient.dialog.CloseAction;

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
        
		//final PhonemListModel model = new PhonemListModel();
		/*final JList list = new JList(model);*/
		final JList list = new JList();
		final JScrollPane sp = new JScrollPane(list);
        
		final JTextField field = new JTextField();
		
		view.add(sp, BorderLayout.CENTER);
		view.add(field, BorderLayout.SOUTH);
        
		
		PropertyChangeListener property  = new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				//model.setPhonem(field.getText());
			}
		};
		field.addPropertyChangeListener("Text", property);
		
        return view;
	}

}