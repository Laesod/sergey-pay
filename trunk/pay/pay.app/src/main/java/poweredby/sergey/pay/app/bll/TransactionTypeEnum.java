package poweredby.sergey.pay.app.bll;

public enum TransactionTypeEnum {
	Deposit									(2, "Deposit"),
	Withdrawal								(3, "Withdrawal"),
	TransferSent							(4, "Transfer Sent"),
	TransferReceived						(5, "Transfer Received"),
	Fees									(6, "Fees"),
	RefundSent								(7, "Refund Sent"),
	RefundReceived							(8, "Refund Received"),
	RequestSent								(9, "Request Sent"),
	RequestReceived							(10, "Request Received"),
	SubscriptionPaymentTo					(11, "Subscription Payment To"),
	SubscriptionPaymentFrom					(12, "Subscription Payment From"),
	SingleItemPurchaseOut					(13, "Single Item Purchase out"),
	SingleItemPurchaseIn					(14, "Single Item Purchase in"),
	MicroPayment							(15, "Micro Payment"),
	BankTransferWireWithdrawalReturnFee		(16, "Bank Transfer/Wire Withdrawal Return Fee"),
	TransferSentNoFees						(17, "Transfer Sent - No Fees"),
	TransferReceivedNoFees					(18, "Transfer Received - No Fees"),
	CurrencyConversion						(19, "Currency Conversion"),
	CurrencyConversion2						(20, "Currency Conversion"),
	Debit									(22, "Debit"),
	Credit									(23, "Credit"),
	ChargebackFine							(24, "Chargeback Fine"),
	ServiceFee								(25, "Service  Fee"),
	BankTransferDepositReturnFee			(28, "Bank Transfer Deposit Return Fee"),
	CreditCardWithdrawalReturnFee			(29, "Credit card withdrawal return fee"),
	EmailInvoicingSent						(30, "Email Invoicing Sent"),
	EmailInvoicingReceived					(31, "Email Invoicing Received"),
	DepositByCcForNonTransferTransactions	(32, "Deposit by cc(for non transfer transactions)");

	private final int value;
	public int getValue() {
		return value;
	}

	private final String description;
	public String getDescription() {
		return description;
	}

	private TransactionTypeEnum(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getDescription();
	}
}
