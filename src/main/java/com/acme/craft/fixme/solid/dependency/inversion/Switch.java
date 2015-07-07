package com.acme.craft.fixme.solid.dependency.inversion;

public class Switch {

	private Device device;
	private boolean pressed;

	public Switch(Device device) {
		this.device = device;
	}

	private void pressSwitch() {
		pressed = !pressed;
		if (pressed) {
			device.setOn(true);
		} else {
			device.setOn(false);
		}
	}
}
