package com.sogeti.rental.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.Address;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;
import com.sogeti.rental.core.RentalActivator;

public class RentalAgencyView extends ViewPart {
	
	private TreeViewer treeViewer;
	
	private static final Object[] EMPTY_RESULT = new Object[0];

	public RentalAgencyView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent);
		RentalProvider provider = new RentalProvider();
		
		treeViewer.setContentProvider(provider);
		treeViewer.setLabelProvider(provider);
		
		Collection<RentalAgency> agencies = new ArrayList<>();
		
		agencies.add(RentalActivator.getAgency());
		
		treeViewer.setInput(agencies);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	
	private class RentalProvider extends LabelProvider implements ITreeContentProvider {

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof Collection<?>) {
				return ((Collection) inputElement).toArray();
			}
			return EMPTY_RESULT;
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof RentalAgency) {
				return ((RentalAgency) parentElement).getCustomers().toArray();
			}
			return EMPTY_RESULT;
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
			return super.getText(element);
		}
	}
}
