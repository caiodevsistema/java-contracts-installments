package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.exceptions.DomainException;

public class Contract {
	
	private Integer number;
	private LocalDate date;
	private Double totalValue;
	
	private List<Installment> installments = new ArrayList<>();

	public Contract(Integer number, LocalDate date, Double totalValue) throws DomainException {
		setNumber(number);
		setDate(date);
		setTotalValue(totalValue);
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) throws DomainException {
		if (number == null ) {
			throw new DomainException("number is null!");
		}
		if (number < 0) {
			throw new DomainException("invalid contract number!");
		}
		this.number = number;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) throws DomainException {
		if (date == null ) {
			throw new DomainException("date is null!");
		}
		this.date = date;
	}

	public Double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(Double totalValue) throws DomainException {
		if (totalValue == null ) {
			throw new DomainException(
					"totalValue is null!");
		}
		if (totalValue < 0.0) {
			throw new DomainException(
					"invalid totalValue!");
		}
		this.totalValue = totalValue;
	}
	
	public void addInstallment(Installment installment) throws DomainException {
		if (installment == null) {
			throw new DomainException(
					"installment is null");
		}
		installments.add(installment);
	}	
	
	public List<Installment> getInstallments(){
		return new ArrayList<>(installments);
	}
	
	public String toString() {
		return String.format(
				"Number: %d%n"
				+ "Data: %s%n"
				+ "Contract Value: %.2f%n", 
				number, 
				date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
				totalValue
				);
	}
}
