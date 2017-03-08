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
	private Label labelDuDate;
	private Label labelAuDate;
	
	public RentalPropertyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Group infoGroup = new Group(parent, SWT.NONE);
		infoGroup.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infoGroup.setText("Informations");
		infoGroup.setLayout(new GridLayout(2, false));
		
		rentedObjectLabel = new Label(infoGroup, SWT.NONE);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.DM_FILL_BACKGROUND;
		rentedObjectLabel.setLayoutData(gd);
		
		Text whoText = new Text(infoGroup, SWT.READ_ONLY);
		whoText.setText("Loué à:");
		whoLabel = new Label(infoGroup, SWT.NONE);
		
		Group grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		Label lblDu = new Label(grpDatesDeLocation, SWT.NONE);
		lblDu.setSize(20, 20);
		lblDu.setText("du:");
		
		labelDuDate = new Label(grpDatesDeLocation, SWT.NONE);
		labelDuDate.setSize(0, 20);
		
		Label lblAu = new Label(grpDatesDeLocation, SWT.NONE);
		lblAu.setText("au:");
		
		labelAuDate = new Label(grpDatesDeLocation, SWT.NONE);
		labelAuDate.setSize(0, 20);

		
		setRental(RentalActivator.getAgency().getRentals().get(0));
		setCustomer(RentalActivator.getAgency().getRentals().get(0));
		setDuDate(RentalActivator.getAgency().getRentals().get(0));
		setAuDate(RentalActivator.getAgency().getRentals().get(0));
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
	
	private void setDuDate(Rental r) {
		labelDuDate.setText(r.getStartDate().toString());
	}
	
	private void setAuDate(Rental r) {
		labelAuDate.setText(r.getEndDate().toString());
	}
}
