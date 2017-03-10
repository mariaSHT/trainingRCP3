package com.sogeti.rental.ui;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

/**
 * The activator class controls the plug-in life cycle
 */
public class RentalUIActivator extends AbstractUIPlugin implements RentalUIConstant {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.sogeti.rental.ui"; //$NON-NLS-1$

	// The shared instance
	private static RentalUIActivator plugin;
	
	private Map<String, PaletteDescriptor> paletteMap;
	
	/**
	 * The constructor
	 */
	public RentalUIActivator() {
		paletteMap = new HashMap<>();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		readPaletteExt();
	}

	private void readPaletteExt() {
		// Mettre un mecanisme pour charger
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		
		for (IConfigurationElement e : reg.getConfigurationElementsFor("com.sogeti.rental.ui.palette")) {
			
				PaletteDescriptor pd = new PaletteDescriptor();
				pd.setName(e.getAttribute("name"));
				pd.setId(e.getAttribute("id"));
				IColorProvider lIC;
				try {
					lIC = (IColorProvider) e.createExecutableExtension("paletteClass");
					pd.setCp(lIC);
				} catch (CoreException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				paletteMap.put(e.getAttribute("id"), pd);
		}
	}
	
	private void readViewExt() {
		// Mettre un mecanisme pour charger
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		
		for (IConfigurationElement e : reg.getConfigurationElementsFor("org.eclipse.ui.views")) {
			if (e.getName().equals("view")) {
				System.out.println("View: " + e.getAttribute("name"));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static RentalUIActivator getDefault() {
		return plugin;
	}

	@Override
	protected void initializeImageRegistry(ImageRegistry reg) {
		Bundle b = FrameworkUtil.getBundle(this.getClass());
		
		reg.put(IMG_CUSTOMER, ImageDescriptor.createFromURL(b.getEntry(IMG_CUSTOMER)));
		reg.put(IMG_RENTAL, ImageDescriptor.createFromURL(b.getEntry(IMG_RENTAL)));
		reg.put(IMG_OBJECT, ImageDescriptor.createFromURL(b.getEntry(IMG_OBJECT)));
		reg.put(IMG_AGENCY, ImageDescriptor.createFromURL(b.getEntry(IMG_AGENCY)));
	}
	
	public Color getColor(String key) {
		
		if (key.isEmpty()) {
			return null;
		}
		
		ColorRegistry cR = JFaceResources.getColorRegistry();
		
		Color col = cR.get(key);
		
		if (col == null) {
			cR.put(key, StringConverter.asRGB(key));
			col = cR.get(key);
		}
		
		return col;
	}
}
