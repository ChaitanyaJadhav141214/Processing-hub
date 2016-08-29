/**
 * 
 */
package com.cts.jaxb.pain00100107;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.google.gson.Gson;

/**
 * @author 141214
 *
 */
public class TestJsonGenerator {

	@Test
	public void generateJson() {
		
		final Document currentPayment = new PaymentValidatorTest().createTestData();
		final Gson     convertor = new Gson();
		final String   jsonPayment = convertor.toJson(currentPayment);
		System.out.println(jsonPayment);
	
		assertTrue("Json Object is null", jsonPayment != null);
		
	}
}
