package model.services;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymantService onlinePaymantService;

	public ContractService(OnlinePaymantService onlinePaymantService) {
		this.onlinePaymantService = onlinePaymantService;
	}

	public void processContract(Contract contract, int months) {

		double basicQuota = contract.getTotalValue() / months;

		for (int i = 1; i <= months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);

			double updatedQuota = basicQuota + onlinePaymantService.interest(basicQuota, i);
            double fullQuota =  updatedQuota + onlinePaymantService.paymentFee(updatedQuota);

			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}
	

}
