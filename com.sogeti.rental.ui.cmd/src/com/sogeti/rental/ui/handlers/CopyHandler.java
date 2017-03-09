package com.sogeti.rental.ui.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.handlers.HandlerUtil;

import com.opcoach.training.rental.Customer;

public class CopyHandler extends AbstractHandler {

@Override
public Object execute(ExecutionEvent event) throws ExecutionException {
	ISelection sel = HandlerUtil.getCurrentSelection(event);
	
	if (sel instanceof IStructuredSelection) {
		IStructuredSelection isel = (IStructuredSelection) sel;
		
		Object lSel = isel.getFirstElement();
		
		if (lSel instanceof Customer) {
			Customer lCust = (Customer) lSel;
			
			Clipboard clipboard = new Clipboard(Display.getCurrent());
			String textData = lCust.getDisplayName();
			String rtfData = "{\\rtf1\\b\\i " + textData + "}";
			TextTransfer textTransfer = TextTransfer.getInstance();
			RTFTransfer rtfTransfer = RTFTransfer.getInstance();
			Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
			Object[] data = new Object[]{textData, rtfData};
			clipboard.setContents(data, transfers);
			clipboard.dispose();

		}
	}
	return null;
}
}
