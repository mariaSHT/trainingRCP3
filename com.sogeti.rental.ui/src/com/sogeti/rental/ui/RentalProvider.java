package com.sogeti.rental.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.opcoach.training.rental.RentalPackage;

class RentalProvider extends LabelProvider implements ITreeContentProvider, IColorProvider, RentalUIConstant {

	@Override
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Collection<?>) {
			return ((Collection) inputElement).toArray();
		}
		return RentalAgencyView.EMPTY_RESULT;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof RentalAgency) {
			Collection<RentalNode> test = new ArrayList<>();
			
			test.add(new RentalNode(RentalNode.CUSTOMERS, (RentalAgency) parentElement));
			test.add(new RentalNode(RentalNode.LOCATIONS, (RentalAgency) parentElement));
			test.add(new RentalNode(RentalNode.OBJETCS, (RentalAgency) parentElement));	
		
			return test.toArray();
		}
		else if (parentElement instanceof RentalNode) {
			return ((RentalNode) parentElement).getChildren();
		}
		
		return null;
	}

	@Override
	public Object getParent(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof RentalAgency) {
			return true;
		}
		else if (element instanceof RentalNode) {
			return true;
		}
		
		return false;
	}
	
	
	@Override
	public String getText(Object element) {
		if (element instanceof RentalAgency) {
			return ((RentalAgency) element).getName();
		}
		else if (element instanceof Customer) {
			return ((Customer) element).getDisplayName();
		}
		else if (element instanceof RentalObject) {
			return ((RentalObject) element).getName();
		}
		else if (element instanceof RentalNode) {
			return ((RentalNode) element).getLabel();
		}
		return super.getText(element);
	}
	
	@Override
	public Image getImage(Object element) {
		if (element instanceof RentalAgency) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_AGENCY);
		}
		else if (element instanceof Customer) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_CUSTOMER);
		}
		else if (element instanceof RentalObject) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_OBJECT);
		}
		else if (element instanceof Rental) {
			return RentalUIActivator.getDefault().getImageRegistry().get(IMG_RENTAL);
		}
//		else if (element instanceof RentalNode) {
//			return niu
//		}

		return super.getImage(element);
	}
	
	
	 class RentalNode {
		protected static final String CUSTOMERS = "Clients";
		protected static final String LOCATIONS = "Locations";
		protected static final String OBJETCS = "Objets à louer";
		
		private String label;
		
		private RentalAgency rentalAgency;
		
		public RentalNode(String label, RentalAgency rentalAgency) {
			super();
			this.label = label;
			this.rentalAgency = rentalAgency;
		}
		
		public Object[] getChildren() {
			if (label == CUSTOMERS) {
				return rentalAgency.getCustomers().toArray();
			}
			else if (label == LOCATIONS) {
				return rentalAgency.getRentals().toArray();
			}
			else if (label == OBJETCS) {
				return rentalAgency.getObjectsToRent().toArray();
			}
			
			return null;
		}
		

		public String getLabel() {
			return label;
		}

		
	}


	@Override
	public Color getForeground(Object element) {
		if (element instanceof Customer) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
		}
		else if (element instanceof RentalObject) {
			
			return Display.getCurrent().getSystemColor(SWT.COLOR_GREEN);
		}
		else if (element instanceof RentalObject) {
			return Display.getCurrent().getSystemColor(SWT.COLOR_YELLOW);
		}
		
		return null;
	}

	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}
}