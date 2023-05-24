package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		System.out.println("Numero: ");
		int number = sc.nextInt();
		System.out.println("Data (dd/MM/yyyy): ");
		LocalDate date = LocalDate.parse(sc.next(), fmt);
		System.out.println("Valor do contrato: ");
		double totalValue = sc.nextDouble();
		
		Contract ct = new Contract(number, date, totalValue);
		
		System.out.println("Entre com o numero de parcelas: ");
		int n = sc.nextInt();
		
		ContractService service = new ContractService(new PaypalService());
		service.processContract(ct, n);
		
		for(Installment installment : ct.getInstallments()) {
			System.out.println(installment);
		}
		
		sc.close();
	}

}
