package com.tatva.datafaker;

import uk.co.jemos.podam.api.PodamFactoryImpl;

public class CustomPodamFactory extends PodamFactoryImpl {
    static CustomDataProviderStrategy getDataProviderStrategy() {
    	CustomDataProviderStrategy myDataProviderStrategy = new CustomDataProviderStrategy();
    	// myDataProviderStrategy.setMemoization(true);
    	 myDataProviderStrategy.setDefaultNumberOfCollectionElements(3);
    	 return myDataProviderStrategy;
    }
	public CustomPodamFactory() {
    	super(getDataProviderStrategy());
    }   
}