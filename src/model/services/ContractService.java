package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;
import model.exceptions.DomainException;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;	

	public ContractService(OnlinePaymentService onlinePaymentService) throws DomainException {
			setOnlinePaymentService(onlinePaymentService);
	}	
	
	public OnlinePaymentService getOnlinePaymentService() {
		return onlinePaymentService;
	}

	public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) throws DomainException {
		if (onlinePaymentService == null) {
			throw new DomainException("onlinePaymentService is null!");
		}
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract, int months) throws DomainException {
		if (months <= 0) {
			throw new DomainException(
					"The number of installments must be positive");
		}		
		double basicQuota = contract.getTotalValue() / months;
		for (int i = 1; i <= months; i++) {
			LocalDate date = contract.getDate().plusMonths(i);
			double interest = onlinePaymentService.interest(basicQuota, i);
			double fee = onlinePaymentService.paymentFee(basicQuota + interest);
			double quota = basicQuota + interest + fee;			
			contract.addInstallment(new Installment(date, quota));
		}		
	}	
}
