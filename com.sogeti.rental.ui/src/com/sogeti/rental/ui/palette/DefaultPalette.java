package com.sogeti.rental.ui.palette;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.RentalUIActivator;
import com.sogeti.rental.ui.pref.RentalColorPreferences;

public class DefaultPalette implements IColorProvider {

	public DefaultPalette() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {

			String lColorV = RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.PREF_CUSTOMERCOLOR);
			Color lColor = RentalUIActivator.getDefault().getColor(lColorV);
			return lColor;
		}
		else if (element instanceof RentalObject) {
			String lColorVR = RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.PREF_OBJECTSCOLOR);
			Color lColorR = RentalUIActivator.getDefault().getColor(lColorVR);
			return lColorR;
		}
		else if (element instanceof Rental) {
			String lColorV = RentalUIActivator.getDefault().getPreferenceStore().getString(RentalColorPreferences.PREF_RENTALCOLOR);
			Color lColor = RentalUIActivator.getDefault().getColor(lColorV);
			return lColor;
		}
		
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
