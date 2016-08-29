/**
 * 
 */
package org.validation.demo;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import com.cts.jaxb.pain00100107.Document;

/**
 * @author 141214
 *
 */
public class PaymentValidator {

	/**
	 * 
	 */
	public PaymentValidator() {
	}
	
	
	
	public Boolean validatePaymntInfoId(final Document inputPaymnet) {
		
		Boolean result = false;
		
		if ( inputPaymnet != null ) {
		
			final ExpressionParser parser = new SpelExpressionParser();
			final StandardEvaluationContext evalContext = new StandardEvaluationContext(inputPaymnet);
			
			final Expression paymntInfoIdValidator = parser.parseExpression("cstmrCdtTrfInitn?.pmtInf[0]?.pmtInfId != null");
			
			result = paymntInfoIdValidator.getValue(evalContext, Boolean.class);
		}
		
		return result;
	}
	
	
	public Boolean validatePaymentMethod(final Document inputPaymnet){
		
		Boolean result = false;
		
		if ( inputPaymnet != null ) {
		
			final ExpressionParser parser = new SpelExpressionParser();
			final StandardEvaluationContext evalContext = new StandardEvaluationContext(inputPaymnet);
			
			final Expression paymentMethodValidator = parser.parseExpression("cstmrCdtTrfInitn?.pmtInf[0]?.pmtMtd != null");
			
			result = paymentMethodValidator.getValue(evalContext, Boolean.class);
		}
		
		return result;
	}
	

}
