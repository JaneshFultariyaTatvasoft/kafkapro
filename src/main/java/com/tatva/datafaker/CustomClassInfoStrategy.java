package com.tatva.datafaker;

import uk.co.jemos.podam.api.AbstractClassInfoStrategy;
import uk.co.jemos.podam.api.ClassAttribute;

public class CustomClassInfoStrategy extends AbstractClassInfoStrategy {

	@Override
	public boolean approve(ClassAttribute attribute) {
		try {
			boolean s = super.approve(attribute);
			if (s) {
				return !attribute.getAttribute().getName().equalsIgnoreCase("id");
			}
			return s;
		}
		catch(Exception ex) {
			return true;
		}
	}
	
	
	
}
