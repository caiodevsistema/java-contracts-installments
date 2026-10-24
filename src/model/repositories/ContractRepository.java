package model.repositories;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.entities.Contract;
import model.entities.Installment;
import model.exceptions.DomainException;

public class ContractRepository {	
	
	public void saveContractData(Contract contract, int months) throws DomainException {
		
		File directory = new File("output", "contracts");

		if (!directory.exists() && !directory.mkdirs()) {
			throw new DomainException(
					"Could not create directory to save contract");
		}
		
		String fileName = String.valueOf(contract.getNumber());
		
		File file = new File(directory, fileName + ".txt");
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {			
			bw.write(contract.toString());
			bw.write(String.format(
					"Number of installments: %d%n"
					+ "INSTALLMENTS:%n", 
					months));			
			for (Installment installment : contract.getInstallments()) {
				bw.write(installment.toString() + "\n");				
			}			
		} catch (IOException e) {
			throw new DomainException(
					"Could not save contract!");
		}		
	}	
}
