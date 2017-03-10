package com.sogeti.rental.ui.palette;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class PaletteMochePalette implements IColorProvider {

	public PaletteMochePalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_GRAY) ;
	}

	@Override
	public Color getBackground(Object element) {

		return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_YELLOW);
	}

}
