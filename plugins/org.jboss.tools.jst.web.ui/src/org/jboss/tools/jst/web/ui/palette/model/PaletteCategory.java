/*******************************************************************************
 * Copyright (c) 2007-2014 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/ 
package org.jboss.tools.jst.web.ui.palette.model;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.jboss.tools.common.model.XModelObject;
import org.jboss.tools.common.model.XModelObjectConstants;
import org.jboss.tools.common.model.options.SharableConstants;
import org.jboss.tools.common.model.ui.image.XModelObjectImageDescriptor;
import org.jboss.tools.jst.web.kb.taglib.IHTMLLibraryVersion;

public class PaletteCategory extends PaletteDrawer implements PaletteXModelObject {

	final static String ICON_ATTRIBUTE = "icon"; 

	private XModelObject xobject;
	private PaletteModel paletteModel;
	
	private IHTMLLibraryVersion version = null;

	private IHTMLLibraryVersion[] availableVersions = new IHTMLLibraryVersion[0];

	public PaletteCategory(XModelObject xobject, boolean open) {
		super(null);
		setXModelObject(xobject);
		if (open) {
			setInitialState(PaletteDrawer.INITIAL_STATE_OPEN);
		} else {
			setInitialState(PaletteDrawer.INITIAL_STATE_CLOSED);
		}
		setDrawerType(PaletteEntry.PALETTE_TYPE_UNKNOWN);

		if(xobject.getAttributeValue(ICON_ATTRIBUTE) !=null && xobject.getAttributeValue(ICON_ATTRIBUTE).length() > 0) {
			XModelObjectImageDescriptor icon = new XModelObjectImageDescriptor(xobject);
			setSmallIcon(icon);
		}
	}

	public PaletteModel getPaletteModel() {
		return paletteModel;
	}

	public void setPaletteModel(PaletteModel paletteModel) {
		this.paletteModel = paletteModel;
	}

	@Override
	public XModelObject getXModelObject() {
		return xobject;
	}
	
	public void setXModelObject(XModelObject xobject) {
		this.xobject = xobject;
		String label = xobject.getAttributeValue(XModelObjectConstants.ATTR_NAME);
		XModelObject p = xobject.getParent();
		if(label.startsWith(PaletteModel.VERSION_PREFIX)) {
			label = p.getAttributeValue(XModelObjectConstants.ATTR_NAME);
			p = p.getParent();
		}
		if(label.indexOf('.') >= 0) {
			label = label.substring(label.indexOf('.') + 1);
		}
		while(p != null && (PaletteModelHelper.isGroup(p) || PaletteModelHelper.isSubGroup(p))) {
			String parentName = p.getAttributeValue(XModelObjectConstants.ATTR_NAME);
			if(!SharableConstants.MOBILE_PALETTE_ROOT.equals(parentName)) {
				label = parentName + " " + label; //$NON-NLS-1$
			}
			p = p.getParent();
		}
		setLabel(label);
	}

	public IHTMLLibraryVersion getVersion() {
		return version;
	}

	public IHTMLLibraryVersion[] getAvailableVersions() {
		return availableVersions;
	}

	public void setVersion(IHTMLLibraryVersion s) {
		version = s;
	}

	public void setAvailableVersions(IHTMLLibraryVersion[] vs) {
		availableVersions = vs;
	}
}