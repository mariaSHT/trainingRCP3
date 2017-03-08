package com.sogeti.rental.ui;


import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Rental;
import com.sogeti.rental.core.RentalActivator;

public class RentalPropertyView extends ViewPart {
	
	private Label rentedObjectLabel;

	private Label whoLabel;
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.BORDER);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.DM_FILL_BACKGROUND;
		rentedObjectLabel.setLayoutData(gd);
		
		Text whoText = new Text(infoGroup, SWT.BORDER);
		whoText.setText("Lou� �:");
		whoLabel = new Label(infoGroup, SWT.BORDER);

		
		setRental(RentalActivator.getAgency().getRentals().get(0));
		setCustomer(RentalActivator.getAgency().getRentals().get(0));
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	private void setRental(Rental r) {
		rentedObjectLabel.setText(r.getRentedObject().getName());
	}
	
	private void setCustomer(Rental r) {
		whoLabel.setText(r.getCustomer().getFirstName() + " " + r.getCustomer().getLastName());
	}
	
}
