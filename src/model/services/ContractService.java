package model.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;

	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		
		Double basicQuota = contract.getTotalValue() / months;
		Date dateInstallment = contract.getDate();
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(dateInstallment);
		
		
		for (int i = 1; i <= months; i++) {
			
			calendar.add(Calendar.MONTH, 1);
			dateInstallment = calendar.getTime();
			
			double updatedQuota = basicQuota +onlinePaymentService.interest(basicQuota, i); 
			double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
			
			contract.setInstallment(new Installment(dateInstallment, fullQuota));
			
		}
		
	}
	

}
