package com.sogeti.rental.rcp;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import com.sogeti.rental.ui.perspective.RentalPerspective;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	@Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }
    
    @Override
	public String getInitialWindowPerspectiveId() {
		return RentalPerspective.RENTAL_PERSPECTIVE_ID;
	}
}
