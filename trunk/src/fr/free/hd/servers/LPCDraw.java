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

import org.apache.commons.logging.LogFactory;
import org.springframework.richclient.application.ApplicationLauncher;

/**
 * Main driver that starts the pet clinic rich client sample application.
 */
public class LPCDraw {

    public static void main(String[] args) {
        String rootContextDirectoryClassPath = "/fr/free/hd/servers/";

        //String startupContextPath = rootContextDirectoryClassPath + "richclient-startup-context.xml";

        String richclientApplicationContextPath = rootContextDirectoryClassPath
                + "LPCDraw-application-context.xml";

        String businessLayerContextPath = rootContextDirectoryClassPath + "/LPCDraw-business-context.xml";

        //String securityContextPath = rootContextDirectoryClassPath + "/standalone/security-context.xml";

        try {
            new ApplicationLauncher(null, new String[] { richclientApplicationContextPath,
                    businessLayerContextPath /*, securityContextPath */ });
        } catch (RuntimeException e) {
            LogFactory.getLog(LPCDraw.class).error("RuntimeException during startup", e);
        }
    }

}