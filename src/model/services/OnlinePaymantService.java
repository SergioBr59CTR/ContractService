package model.services;

public interface OnlinePaymantService {
	double paymentFee(double amount);
	double interest(double amount, int months);
}