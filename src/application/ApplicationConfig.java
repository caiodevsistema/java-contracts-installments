package application;

import model.exceptions.DomainException;
import model.repositories.ContractRepository;
import model.services.ContractService;
import model.services.PaypalService;

public class ApplicationConfig {
	
	public ContractService createContractService() throws DomainException {		
		return new ContractService(new PaypalService());		
	}
	
	public ContractRepository createContractRepository() throws DomainException {
		return new ContractRepository();
	}
}
