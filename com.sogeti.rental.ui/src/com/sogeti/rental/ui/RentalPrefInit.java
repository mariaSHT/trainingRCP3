package com.sogeti.rental.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;

import com.sogeti.rental.ui.pref.RentalColorPreferences;

public class RentalPrefInit extends AbstractPreferenceInitializer {

	public RentalPrefInit() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = RentalUIActivator.getDefault().getPreferenceStore();
		
		store.setDefault(RentalColorPreferences.PREF_CUSTOMERCOLOR, StringConverter.asString(new RGB(10,20,30)));
		store.setDefault(RentalColorPreferences.PREF_OBJECTSCOLOR,  StringConverter.asString(new RGB(150,50,30)));//SWT.COLOR_YELLOW);
		store.setDefault(RentalColorPreferences.PREF_RENTALCOLOR,  StringConverter.asString(new RGB(50,60,30)));//SWT.COLOR_GREEN);
		

	}

}
