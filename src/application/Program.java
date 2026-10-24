package application;

import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;

import application.services.ContractApplicationService;
import model.exceptions.DomainException;
import views.ContractUI;

public class Program {

	public static void main(String[] args) {

		ApplicationConfig config = new ApplicationConfig();

		try {
			ContractUI contractUI = new ContractUI();
			contractUI.start(new ContractApplicationService(
					config.createContractService(), config.createContractRepository()));
		} catch (DomainException e) {
			System.out.println("Error DomainException: " + e.getMessage());
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		} catch (DateTimeParseException e) {
			System.out.println(e.getMessage());
		}
	}
}
