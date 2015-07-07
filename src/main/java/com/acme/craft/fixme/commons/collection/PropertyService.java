package com.acme.craft.fixme.commons.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

public class PropertyService {

	public List<String> x = new ArrayList<>(Arrays.asList("property1", "property2", "property3", "property4"));

	public List<String> defaultProperties() {
		List<String> properties = new ArrayList<>();
		properties.add("property1");
		properties.add("property2");
		properties.add("property3");
		properties.add("property4");

		return properties;
	}

	public boolean valid(List<String> properties) {
		if (CollectionUtils.isEmpty(properties)) {
			boolean isValid = true;
			for (String property : properties) {
				isValid = isValid && valid(property);
			}
		}
		return false;
	}

	private boolean valid(String property) {
		return property != null && !property.isEmpty();
	}
}
