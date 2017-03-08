package com.sogeti.rental.ui;

import java.util.ArrayList;
import java.util.Collection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalActivator;

public class RentalAgencyView extends ViewPart {
	
	private TreeViewer treeViewer;
	
	public static final Object[] EMPTY_RESULT = new Object[0];

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
		treeViewer.expandAll();
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
}
