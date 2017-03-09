package com.sogeti.rental.ui;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.part.ViewPart;

import com.opcoach.training.rental.RentalAgency;
import com.sogeti.rental.core.RentalActivator;

public class RentalAgencyView extends ViewPart implements IPropertyChangeListener {
	
	private TreeViewer treeViewer;
	
	public static final Object[] EMPTY_RESULT = new Object[0];

	public RentalAgencyView() {
		RentalUIActivator.getDefault().getPreferenceStore().addPropertyChangeListener(this);
	}

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Button btnCollapseAll = new Button(parent, SWT.CHECK);
		btnCollapseAll.setText("Collapse All");
		
		btnCollapseAll.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				treeViewer.collapseAll();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Button btnExpandAll = new Button(parent, SWT.CHECK);
		btnExpandAll.setText("Expend All");
		btnExpandAll.setToolTipText("Expand All");
		
		btnExpandAll.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				treeViewer.expandAll();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		treeViewer = new TreeViewer(parent);
		Tree tree = treeViewer.getTree();
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		RentalProvider provider = new RentalProvider();
		
		treeViewer.setContentProvider(provider);
		treeViewer.setLabelProvider(provider);
		
		Collection<RentalAgency> agencies = new ArrayList<>();
		
		agencies.add(RentalActivator.getAgency());
		
		treeViewer.setInput(agencies);
		
		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		// Do nothing

	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		treeViewer.refresh();
		
	}

	
}
