package com.acme.craft.fixme.nullscheck;

import java.util.Optional;

public class PropertyRepository {

	public Property get(String propertyId) {
		// sth goes wrong...
		return Optional<Property>.empty();
	}

	public void save(Property property) {
		// sth sophisticated happened...
	}
}
