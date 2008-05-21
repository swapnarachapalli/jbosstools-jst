/*******************************************************************************
 * Copyright (c) 2007 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.jst.web.project.handlers;

import java.util.Properties;

import org.jboss.tools.common.meta.action.impl.AbstractHandler;
import org.jboss.tools.common.model.XModelException;
import org.jboss.tools.common.model.XModelObject;
import org.jboss.tools.common.model.util.EclipseResourceUtil;
import org.jboss.tools.jst.web.project.helpers.AbstractWebProjectTemplate;
import org.jboss.tools.jst.web.project.helpers.WebProjectTemplateFactory;

public class CreateTemplateFromPageHandler extends AbstractHandler {

    public boolean isEnabled(XModelObject object) {
    	if(object == null) return false;
        return EclipseResourceUtil.getModelNature(EclipseResourceUtil.getProject(object)) != null;
    }

    public void executeHandler(XModelObject object, Properties p) throws XModelException {
    	AbstractWebProjectTemplate template = WebProjectTemplateFactory.getTemplate(object);
    	if(template != null) {
    		template.addPageTemplate(object);
    	}
    }

}
