package com.acme.craft.fixme.solid.single.responsibility.repository;

import com.acme.craft.fixme.solid.single.responsibility.error.ErrorHandler;

import lombok.extern.java.Log;

@Log
public class CustomerRepository {

	public void add() {
		try {
			/*
			 * do some database stuff here
			 */
		} catch (Exception e) {
			ErrorHandler.handleError("database error");
		}
	}

}
