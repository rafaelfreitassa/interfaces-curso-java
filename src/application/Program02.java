package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.services.ContractService;
import model.services.PaypalService;

public class Program02 {

	public static void main(String[] args) throws ParseException {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		System.out.println("Enter contract data");
		System.out.print("Number: ");
		Integer number = sc.nextInt();
		sc.nextLine();
		System.out.print("Date (dd/MM/yyyy): ");
		Date date = sdf.parse(sc.nextLine());
		if (date == null) {
			date = sdf.parse("01/01/2000");
		}

		System.out.print("Contract value: ");
		Double totalContract = sc.nextDouble();
		
		Contract contract = new Contract(number, date, totalContract);
		
		System.out.print("Enter number of installments: ");
		Integer months = sc.nextInt();
		
		ContractService contractService = new ContractService(new PaypalService());
		
		contractService.processContract(contract, months);
		
		System.out.println("Installments:");
		for (int i = 0; i < contract.getInstallments().size(); i++) {
			System.out.println(sdf.format(contract.getInstallments().get(i).getDueDate()) + " - " + contract.getInstallments().get(i).getAmount());
		}
		
		sc.close();

	}

}
