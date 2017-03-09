package com.sogeti.rental.ui.pref;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.RentalAgencyView;
import com.sogeti.rental.ui.RentalUIActivator;

public class RentalColorPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public final static String PREF_CUSTOMERCOLOR = "COLOR_CUST";
	public final static String PREF_RENTALCOLOR = "COLOR_RENT";
	public final static String PREF_OBJECTSCOLOR = "COLOR_OBJE";
	
	public RentalColorPreferences() {
		super(GRID);
		setPreferenceStore(RentalUIActivator.getDefault().getPreferenceStore());
	}


	@Override
	public void init(IWorkbench workbench) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_CUSTOMERCOLOR, "Customer: ", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_RENTALCOLOR, "Rental: ", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_OBJECTSCOLOR, "Objects: ", getFieldEditorParent()));
	}

}
