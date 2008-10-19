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
package fr.free.hd.servers;

import java.awt.Dimension;

import org.springframework.richclient.application.ApplicationWindow;
import org.springframework.richclient.application.config.ApplicationWindowConfigurer;
import org.springframework.richclient.application.config.DefaultApplicationLifecycleAdvisor;

/**
 * Custom application lifecycle implementation that configures the petclinic app at well defined points within
 * its lifecycle.
 *
 * @author Keith Donald
 */
public class LPCDrawLifecycleAdvisor extends DefaultApplicationLifecycleAdvisor
{

    /**
     * Show a setup wizard before actual applicationWindow is created. This should happen only on Application
     * startup and only once. (note: for this to happen only once, a state should be preserved, which is not
     * the case with this sample)
     */
    public void onPreStartup(){}

    /**
     * Additional window configuration before it is created.
     */
    public void onPreWindowOpen(ApplicationWindowConfigurer configurer)
    {
        super.onPreWindowOpen(configurer);
        // comment out to hide the menubar, toolbar, or reduce window size...
        configurer.setShowMenuBar(false);
        configurer.setShowToolBar(true);
        configurer.setInitialSize(new Dimension(800, 300));
    }

    /**
     * When commands are created, lookup the login command and execute it.
     */
    public void onCommandsCreated(ApplicationWindow window){}
}