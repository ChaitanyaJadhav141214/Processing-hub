package com.cts.jaxb.pain00100107;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class JaxbCreator extends TestCase {

	@Test
	public void createXMlFile() {

	}

	public Document createTestData() {

		final Document testDoc = new Document();
		final CustomerCreditTransferInitiationV07 cctri = new CustomerCreditTransferInitiationV07();
		final List<PaymentInstruction20> instructionList = cctri.getPmtInf();
		final PaymentInstruction20 paymentInstruction = new PaymentInstruction20();
		List<CreditTransferTransaction26> creditTransferTxnList = null;
		CreditTransferTransaction26 creditTransferTxn = new CreditTransferTransaction26();
		GroupHeader48 grpHeader = new GroupHeader48();
		grpHeader.setCreDtTm(new XMLGregorianCalendarImpl());
		
		
		testDoc.setCstmrCdtTrfInitn(cctri);
		
		testDoc.cstmrCdtTrfInitn = cctri;
		cctri.grpHdr = grpHeader;
		cctri.pmtInf = instructionList;
		instructionList.add(paymentInstruction);
		
		// setting the method code
		paymentInstruction.setPmtMtd(PaymentMethod3Code.TRF);
		paymentInstruction.pmtInfId = "testId";
		
		creditTransferTxnList = paymentInstruction.getCdtTrfTxInf();
		setCreditTransferAmount(creditTransferTxnList, creditTransferTxn);
		setBeneAccountNumber(creditTransferTxn);
		setBeneCreditInstitution(creditTransferTxn);
		
		

		return testDoc;
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
