package application.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.exceptions.DomainException;
import model.repositories.ContractRepository;
import model.services.ContractService;
import views.InstallmentUI;

public class ContractApplicationService {	

	private ContractService contractService;
	private ContractRepository contractRepository;

	public ContractApplicationService(ContractService contractService, ContractRepository contractRepository) 
			throws DomainException {
		if (contractService == null) {
			throw new DomainException("contract service is null!");
		}
		if (contractRepository == null) {
			throw new DomainException("contractRepository is null!");
		}
		this.contractService = contractService;
		this.contractRepository = contractRepository;
	}

	public void createContract(Integer number, LocalDate date, Double totalValue, int months) 
			throws DomainException{
		Contract contract = new Contract(number, date, totalValue); // Cria um objeto Contract com os dados recebidos.
		contractService.processContract(contract, months);	// Delega ao ContractService o processamento do contrato e o cálculo e a inclusão das parcelas
		saveContract(contract, months); // Solicita o salvamento dos dados do contrato no repositório.
		showContract(contract); // Solicita à interface a exibição das parcelas do contrato.
	}

	public void saveContract(Contract contract, int months) throws DomainException {
		contractRepository.saveContractData(contract, months); // Delega ao ContractRepository a persistência dos dados.
	}

	public void showContract(Contract contract) throws DomainException {
		InstallmentUI installmentUI = new InstallmentUI();
		installmentUI.showInstallment(contract); // Solicita à InstallmentUI a exibição das parcelas.
	}
}
