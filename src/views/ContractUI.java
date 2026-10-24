package views;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import application.services.ContractApplicationService;
import model.exceptions.DomainException;

public class ContractUI {	

	public void start(ContractApplicationService contractApplicationService) throws DomainException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		char answer;
		while (true) {
			System.out.println("Enter the contract data:");
			System.out.print("Number: ");
			Integer number = sc.nextInt();
			sc.nextLine();
			System.out.print("Data (dd/MM/yyyy): ");
			LocalDate date = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
			System.out.print("Contract value: ");
			Double totalValue = sc.nextDouble();
			System.out.print("Enter the number of installments: ");
			int months = sc.nextInt();
			System.out.println();			
			contractApplicationService.createContract(number, date, totalValue, months);
			System.out.println();
			while (true) {
				System.out.print("Do you wish to continue (y/n)? ");
				answer = sc.next().toLowerCase().charAt(0);
				if (answer != 'y' && answer != 'n') {
					System.out.println();
					System.out.println("Invalid answer!\nTry Again!");
					System.out.println();
					continue;
				}
				break;
			}
			if (answer == 'y') {
				System.out.println();
				continue;
			}
			break;
		}
		sc.close();
	}
}
