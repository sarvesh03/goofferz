package com.goofferz.framework;

import java.util.ResourceBundle;

public abstract class PropertyLocatorBase {

	protected final static String RESOURCE_BUNDLE_NAME = "";
	protected ResourceBundle resBundle;

	/**
	 * Gets the value of locator from the resource bundle using the name of the
	 * method that called this method.
	 * 
	 * @return the value of the locator from the resource bundle
	 */
	public String getValue() {
		String locatorKey = new Exception().getStackTrace()[1].getMethodName();
		try {
			return resBundle.getString(locatorKey);
		} catch (Exception e) {
			System.out.println("Unable to find the locator for '" + locatorKey
					+ "' in the resource bundle '" + RESOURCE_BUNDLE_NAME
					+ "'.");
			e.printStackTrace();
		}
		return null;
	}

}