package model.entities;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.exceptions.DomainException;

public class Installment {
	
	private LocalDate dueDate;
	private Double amount;
	
	public Installment(LocalDate dueDate, Double amount) throws DomainException {
		setDueDate(dueDate);
		setAmount(amount);
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) throws DomainException {
		if (dueDate == null) {
			throw new DomainException("dueDate is null!");
		}
		this.dueDate = dueDate;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) throws DomainException {
		if (amount == null || amount < 0.0) {
			throw new DomainException("invalid amount!");
		}
		this.amount = amount;
	}

	@Override
	public String toString() {
		return dueDate.format(
				DateTimeFormatter.ofPattern(
						"dd/MM/yyyy"))
				+ " - " 
				+ String.format(
						"%.2f", amount);
	}
	
	
}
