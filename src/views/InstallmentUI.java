package views;

import model.entities.Contract;
import model.exceptions.DomainException;

public class InstallmentUI {	

	public void showInstallment(Contract contract) throws DomainException {
		System.out.println("INSTALLMENTS:");
		contract.getInstallments().forEach(System.out::println);
	}
	
}
