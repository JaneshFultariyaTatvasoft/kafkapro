package com.tatva.datafaker;

import uk.co.jemos.podam.api.AbstractRandomDataProviderStrategy;

public class CustomDataProviderStrategy extends AbstractRandomDataProviderStrategy {
	@Override
	public int getMaxDepth(Class<?> type) {
		return 3;
	}

}
