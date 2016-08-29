package com.cts.jaxb.pain00100107;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.Assert;
import org.junit.Test;
import org.validation.demo.PaymentValidator;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class PaymentValidatorTest {

	@Test
	public void validatePaymentInfo() {
		Document currentDoc = createTestData();
		Assert.assertTrue(new PaymentValidator().validatePaymntInfoId(currentDoc));
	}

	@Test
	public void validatePaymentInfoNullDocument(){
		
		Assert.assertFalse(new PaymentValidator().validatePaymntInfoId(null));
	}
	
	
	@Test
	public void validatePaymentInfoNullPaymentInfo(){
		
		Document currentDoc = createTestData();
		currentDoc.getCstmrCdtTrfInitn().getPmtInf().get(0).pmtInfId = null;
		
		Assert.assertFalse(new PaymentValidator().validatePaymntInfoId(currentDoc));
		
	}
	
	@Test
	public void validatePaymentMethod(){
		
		Document currentDoc = createTestData();
		Assert.assertTrue(new PaymentValidator().validatePaymentMethod(currentDoc));
	}
	
	@Test
	public void validatePaymentMethodCHKMethodType(){
		
		Document currentDoc = createTestData();
		currentDoc.getCstmrCdtTrfInitn().getPmtInf().get(0).pmtMtd = PaymentMethod3Code.CHK;
		Assert.assertTrue(new PaymentValidator().validatePaymentMethod(currentDoc));
	}
	
	@Test
	public void validatePaymentMethodNullInput(){
		Assert.assertFalse(new PaymentValidator().validatePaymentMethod(null));
	}

	public Document createTestData() {

		final Document testDoc = new Document();
		final CustomerCreditTransferInitiationV07 cctri = new CustomerCreditTransferInitiationV07();
		final List<PaymentInstruction20> instructionList = cctri.getPmtInf();
		final PaymentInstruction20 paymentInstruction = new PaymentInstruction20();
		List<CreditTransferTransaction26> creditTransferTxnList = null;
		CreditTransferTransaction26 creditTransferTxn = null;
		GroupHeader48 grpHeader = new GroupHeader48();
		Calendar currentCal = Calendar.getInstance();
		
		XMLGregorianCalendar creDtTm = new XMLGregorianCalendarImpl();
		creDtTm.setYear(currentCal.get(Calendar.YEAR));
		creDtTm.setMonth(currentCal.get(Calendar.MONTH));
		creDtTm.setDay(currentCal.get(Calendar.DATE));
		creDtTm.setHour(currentCal.get(Calendar.HOUR_OF_DAY));
		creDtTm.setMinute(currentCal.get(Calendar.MINUTE));
		creDtTm.setSecond(currentCal.get(Calendar.SECOND));
		creDtTm.setMillisecond(currentCal.get(Calendar.MILLISECOND));
		creDtTm.setTimezone(currentCal.get(Calendar.ZONE_OFFSET));
		grpHeader.setCreDtTm(creDtTm);
		
		
		testDoc.setCstmrCdtTrfInitn(cctri);
		
		testDoc.cstmrCdtTrfInitn = cctri;
		cctri.grpHdr = grpHeader;
		cctri.pmtInf = instructionList;
		
		
		instructionList.add(paymentInstruction);
		
		// setting the method code
		paymentInstruction.setPmtMtd(PaymentMethod3Code.TRF);
		paymentInstruction.pmtInfId = "testId";
		
		
		for (int index = 0; index < 10; ++index) {
			createCreditRecord(paymentInstruction);			
		}

		
		

		return testDoc;
	}

	/**
	 * @param paymentInstruction
	 */
	private void createCreditRecord(
			final PaymentInstruction20 paymentInstruction) {
		List<CreditTransferTransaction26> creditTransferTxnList;
		CreditTransferTransaction26 creditTransferTxn;
		creditTransferTxnList = paymentInstruction.getCdtTrfTxInf();
		creditTransferTxn = new CreditTransferTransaction26();
		setCreditTransferAmount(creditTransferTxnList, creditTransferTxn);
		setBeneAccountNumber(creditTransferTxn);
		setBeneCreditInstitution(creditTransferTxn);
	}

	private void setBeneCreditInstitution(
			CreditTransferTransaction26 creditTransferTxn) {
		BranchAndFinancialInstitutionIdentification5 cdtrAgt = new BranchAndFinancialInstitutionIdentification5();
		FinancialInstitutionIdentification8 finInstnId = new FinancialInstitutionIdentification8();
		ClearingSystemMemberIdentification2 clrSysMmbId = new ClearingSystemMemberIdentification2();
		
		creditTransferTxn.setCdtrAgt(cdtrAgt);
		cdtrAgt.setFinInstnId(finInstnId);
		finInstnId.setClrSysMmbId(clrSysMmbId);
		
		
		creditTransferTxn.getCdtrAgt().getFinInstnId().getClrSysMmbId().mmbId = "BenefeciaryCreditInstitution";
	}

	private void setBeneAccountNumber(CreditTransferTransaction26 creditTransferTxn) {
		CashAccount24 cdtrAcct = new CashAccount24();
		AccountIdentification4Choice accountIdInstance = new AccountIdentification4Choice();
		GenericAccountIdentification1 other = new GenericAccountIdentification1();
		
		creditTransferTxn.setCdtrAcct(cdtrAcct);
		cdtrAcct.setId(accountIdInstance);
		accountIdInstance.setOthr(other);
		
		creditTransferTxn.getCdtrAcct().getId().getOthr().id = "123456";
	}

	private void setCreditTransferAmount(
			List<CreditTransferTransaction26> creditTransferTxnList,
			CreditTransferTransaction26 creditTransferTxn) {
		
		creditTransferTxnList.add(creditTransferTxn);
		AmountType4Choice crdTrnsferAmt = new AmountType4Choice();
		ActiveOrHistoricCurrencyAndAmount crdTrnsferAmtInstd = new ActiveOrHistoricCurrencyAndAmount();
		crdTrnsferAmtInstd.value = new BigDecimal(20);
		crdTrnsferAmt.setInstdAmt(crdTrnsferAmtInstd);
		creditTransferTxn.setAmt(crdTrnsferAmt);
	}

}
