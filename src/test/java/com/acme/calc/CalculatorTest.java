package com.acme.calc;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

<<<<<<< HEAD
	private Calculator calculator = new Calculator();;
=======
	private Calculator calculator = new Calculator();
>>>>>>> upstream/master

	@Test
	public void additionShouldReturnCorrectResult() {
		// given
<<<<<<< HEAD
		double firstnumber = 5.0;
		double secondnumber = 6.0;
		// when
		Double result = calculator.add(firstnumber, secondnumber);
		// then
		Assert.assertTrue(result == 11);

=======
		double firstNumber = 5.0;
		double secondNumber = 6.0;
		// when
		Double result = calculator.add(firstNumber, secondNumber);
		// then
		Assert.assertFalse(result.isNaN());
		Assert.assertTrue(result == 11);
>>>>>>> upstream/master
	}

	private double firstNumber;
	private double secondNumber;
	private Exception thrown;
	@Test
<<<<<<< HEAD
	public void subtractionShouldReturnCorrectResult() {
		// given
		double firstnumber = 6.0;
		double secondnumber = 6.0;
		// when
		Double result = calculator.subtract(firstnumber, secondnumber);
		// then
		Assert.assertTrue(result == 0);
=======
	public void divisionShouldThrowExceptionWhenDivisorIsZero() {
		givenNumbersWithZero();
		catchException(() -> calculator.divide(firstNumber,secondNumber));
		assertException(DivisorCannotBeZeroException.class);
	}

	private void catchException(Runnable runnable) {
		try {
			runnable.run();
		} catch (Exception e) {
			thrown = e;
		}
>>>>>>> upstream/master
	}

	private void assertException(Class<DivisorCannotBeZeroException> expectedExceptionClass) {
		Assert.assertNotNull(thrown);
		Assert.assertTrue(expectedExceptionClass.equals(thrown.getClass()));
	}
	private void assertThat(Exception e, Class<?> expectedClass) {
		Assert.assertTrue(e.getClass().equals(expectedClass));
	}
	private void thenCorrectExceptionIsThrons(Exception e) {
		Assert.assertTrue(e.getClass().equals(
				DivisorCannotBeZeroException.class));
	}
	// TODO division and multiplication test!
<<<<<<< HEAD
	@Test
	public void multiplicationShouldReturnCorrectResult() {
		// given
		double firstnumber = 2.0;
		double secondnumber = 2.0;
		// when
		Double result = calculator.multiply(firstnumber, secondnumber);
		// then
		Assert.assertTrue(result == 4.0);
=======
	private void givenNumbersWithZero() {
		firstNumber = 5.0;
		secondNumber = 0.0;
>>>>>>> upstream/master
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
