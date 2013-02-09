package poweredby.sergey.pay.app.bll;

public enum TransactionStateEnum {
	Pending								(1, "Pending"),
	Completed							(2, "Completed"),
	WithdrawalCreated					(3, "Withdrawal - Created"),
	DepositCreated						(4, "Deposit - Created"),
	WithdrawalPendingProcessing			(5, "Withdrawal - Pending Processing"),
	RequestPendingAcceptance			(6, "Request - Pending Acceptance"),
	TransferPendingAccountUpgrade		(7, "Transfer - Pending Account Upgrade"),
	TransactionCancelled				(8, "Transaction Cancelled"),
	TransactionExpired					(9, "Transaction Expired"),
	ACHWithdrawalProcessed				(10, "ACH- Withdrawal Processed"),
	TransactionDeclined					(11, "Transaction Declined"),
	DepositPendingProcessing			(12, "Deposit - Pending Processing"),
	ACHDepositProcessed					(13, "ACH - Deposit Processed"),
	TransferSent						(14, "Transfer Sent"),
	RequestSent							(15, "Request Sent"),
	RequestSentEmailVerification		(16, "Request Sent - Email Verification"),
	RequestDeclined						(17, "Request Declined"),
	SubscriptionFailed					(18, "Subscription failed"),
	Reversed							(19, "Reversed"),
	BankAccountConfirmation				(20, "Bank Account Confirmation"),
	BankAccountExport					(21, "Bank Account Export"),
	BankTransactionReturned				(22, "Bank Transaction Returned"),
	DisbursementPendingForVerification	(23, "Disbursement Pending for Verification"),
	ACHProcessing						(24, "ACH Processing"),
	TransactionRefunded					(25, "Transaction Refunded"),
	TransactionRefundedAffectingBalance	(26, "Transaction Refunded affecting Balance"),
	BankTransactionReversed				(27, "Bank Transaction Reversed"),
	OnHold								(28, "On Hold"),
	PendingEmailValidation				(30, "Pending Email Validation"),
	Chargeback							(31, "Chargeback"),
	PendingAwaitingReview				(32, "Pending-Awaiting Review"),
	PendingCreditCardRefund				(33, "Pending CreditCard Refund"),
	PendingCreditCardValidation			(34, "Pending CreditCard Validation"),
	CompletedDoesNotEffectCcUsage		(35, "Completed - does not effect CC usage"),
	PendigCreditCardReversal			(36, "Pendig CreditCard Reversal");
	
	private final int value;
	public int getValue() {
		return value;
	}

	private final String description;
	public String getDescription() {
		return description;
	}

	private TransactionStateEnum(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getDescription();
	}
}
