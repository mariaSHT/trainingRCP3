package com.sogeti.rental.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.ui.pref.RentalColorPreferences;

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

		return super.getImage(element);
	}
	
	
	 class RentalNode {
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			result = prime * result + ((rentalAgency == null) ? 0 : rentalAgency.hashCode());
			return result;
		}
		
// Les methodes equals and hashcode permettent de ne pas avoir les noeuds qui collapsent quand on fait un refresh
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RentalNode other = (RentalNode) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			if (rentalAgency == null) {
				if (other.rentalAgency != null)
					return false;
			} else if (!rentalAgency.equals(other.rentalAgency))
				return false;
			return true;
		}


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

		private RentalProvider getOuterType() {
			return RentalProvider.this;
		}

		
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