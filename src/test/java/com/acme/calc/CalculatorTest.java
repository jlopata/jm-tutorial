package com.acme.calc;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

	private Calculator calculator = new Calculator();;

	@Test
	public void additionShouldReturnCorrectResult() {
		// given
		double firstnumber = 5.0;
		double secondnumber = 6.0;
		// when
		Double result = calculator.add(firstnumber, secondnumber);
		// then
		Assert.assertTrue(result == 11);

	}

	@Test
	public void subtractionShouldReturnCorrectResult() {
		// given
		double firstnumber = 6.0;
		double secondnumber = 6.0;
		// when
		Double result = calculator.subtract(firstnumber, secondnumber);
		// then
		Assert.assertTrue(result == 0);
	}

	// TODO division and multiplication test!
	@Test
	public void multiplicationShouldReturnCorrectResult() {
		// given
		double firstnumber = 2.0;
		double secondnumber = 2.0;
		// when
		Double result = calculator.multiply(firstnumber, secondnumber);
		// then
		Assert.assertTrue(result == 4.0);
	}

	@Test
	public void divisionShouldReturnCorrectResult() {
		// given
		double firstnumber = 8.0;
		double secondnumber = 2.0;
		// when
		Double result = calculator.divide(firstnumber, secondnumber);
		// then
		Assert.assertTrue(result == 4.0);
	}

	@Test
	public void divisionShouldThrowException() {
		boolean thrown = false;
		// given
		double firstnumber = 8.0;
		double secondnumber = 0.0;
		// when
		try {
			Double result = calculator.divide(firstnumber, secondnumber);
		} catch (DivisorCannotBeZeroException e) {
			thrown = true;
		}
		// then
		Assert.assertTrue(thrown);
	}
}
