package com.acme.craft.fixme.solid.dependency.inversion;

import lombok.Data;

@Data
public class Lamp implements Device {

	private boolean on = false;

	public void setOn(boolean x) {
		this.on = x;
	}
}
