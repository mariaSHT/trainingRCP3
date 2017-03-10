package com.sogeti.rental.ui.pref;

import java.util.Map;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.sogeti.rental.ui.PaletteDescriptor;
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
//		addField(new ColorFieldEditor(PREF_CUSTOMERCOLOR, "Customer: ", getFieldEditorParent()));
//		addField(new ColorFieldEditor(PREF_RENTALCOLOR, "Rental: ", getFieldEditorParent()));
//		addField(new ColorFieldEditor(PREF_OBJECTSCOLOR, "Objects: ", getFieldEditorParent()));
		
		// Extract the double String array for name and color provider (value is
		// the key)
		Map<String, PaletteDescriptor> palettes = RentalUIActivator.getDefault().getPaletteMap();

		String[][] comboValues = new String[palettes.size()][2];
		int i = 0;
		for (PaletteDescriptor p : palettes.values())
		{
			comboValues[i][0] = p.getName(); // Displayed name
			comboValues[i][1] = p.getId(); // Returned value if selected
			i++;
		}

		addField(new ComboFieldEditor("PREF_PALETTE", "Palette :", comboValues, getFieldEditorParent()));

	}

}
