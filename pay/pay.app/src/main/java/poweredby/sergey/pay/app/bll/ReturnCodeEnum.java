package poweredby.sergey.pay.app.bll;

public enum ReturnCodeEnum {
	C201	(201, "Missing parameter USER in the request."),
	C202	(202, "Missing parameter PASSWORD in the request."),
	C203	(203, "Missing parameter RECEIVEREMAIL in the request."),
	C204	(204, "Missing parameter AMOUNT in the request."),
	C205	(205, "Missing parameter CURRENCY in the request."),
	C206	(206, "Missing parameter PURCHASETYPE in the request."),
	  	 
	C211	(211, "Invalid format for parameter USER. Value must be a valid e-mail address in the following format: username@example.com"),
	C212	(212, "Invalid format for parameter PASSWORD. Value must be a 16 character alpha-numeric string."),
	C213	(213, "Invalid format for parameter AMOUNT. Value must be numeric."),
	C214	(214, "Invalid value for parameter CURRENCY. Value must be a three character string representing an ISO-4217 currency code accepted by AlertPay."),
	C215	(215, "Invalid format for parameter RECEIVEREMAIL. Value must be a valid e-mail address in the following format: username@example.com"),
	C216	(216, "The format for parameter NOTE is invalid."),
	C217	(217, "Invalid value for parameter TESTMODE. Value must be either 0 or 1."),
	C218	(218, "Invalid value for parameter PURCHASETYPE. Value must be an integer number between 0 and 3."),
	C219	(219, "Invalid format for parameter SENDEREMAIL. Value must be a valid e-mail address in the following format: username@example.com"),
	  	 
	C221	(221, "Cannot perform the request. Invalid USER and PASSWORD combination."),
	C222	(222, "Cannot perform the request. API Status is disabled for this account."),
	C223	(223, "Cannot perform the request. Action cannot be performed from this IP address."),
	C224	(224, "Cannot perform the request. USER account is not active."),
	C225	(225, "Cannot perform the request. USER account is locked."),
	C226	(226, "Cannot perform the request. Too many failed authentications. The API has been momentarily disabled for your account. Please try again later."),
	  	 
	C231	(231, "Incomplete transaction. Amount to be sent must be positive and greater than 1.00."),
	C232	(232, "Incomplete transaction. Amount to be sent cannot be greater than the maximum amount."),
	C233	(233, "Incomplete transaction. You have insufficient funds in your account."),
	C234	(234, "Incomplete transaction. You are attempting to send more than your sending limit."),
	C235	(235, "Incomplete transaction. You are attempting to send more than your monthly sending limit."),
	C236	(236, "Incomplete transaction. You are attempting to send money to yourself."),
	C237	(237, "Incomplete transaction. You are attempting to send money to an account that cannot accept payments."),
	C238	(238, "Incomplete transaction. The recipient of the payment does not accept payments from unverified members."),
	C239	(239, "Invalid value for parameter NOTE. The field cannot exceed 1000 characters."),
	C240	(240, "Error with parameter SENDEREMAIL. The specified e-mail is not associated with your account."),
	C241	(241, "Error with parameter SENDEREMAIL. The specified e-mail has not been validated."),
	C242	(242, "Incomplete transaction. The recipient’s account is temporarily suspended and cannot receive money."),
	C243	(243, "Incomplete transaction. The recipient only accepts funds from members in the same country."),
	C244	(244, "Incomplete transaction. The recipient cannot receive funds at this time, please try again later."),
	C245	(245, "Incomplete transaction. The amount you are trying to send exceeds your transaction limit as an Unverified Member."),
	C246	(246, "Incomplete transaction. Your account must be Verified in order to transact money."),
	C247	(247, "Unsuccessful refund. Transaction does not belong to this account."),
	C248	(248, "Unsuccessful refund. Transaction does not exist in our system."),
	C249	(249, "Unsuccessful refund. Transaction is no longer refundable."),
	  	 
	C250	(250, "Unsuccessful cancellation. Subscription does not belong to this account."),
	C251	(251, "Unsuccessful cancellation. Subscription does not exist in our system."),
	C252	(252, "Unsuccessful cancellation. Subscription is already canceled."),
	C260	(260, "Unsuccessful query. The specified CURRENCY balance is NOT open in your account."),
	  	 
	C299	(299, "An unexpected error occurred.");
	
	private final int value;
	public int getValue() {
		return value;
	}

	private final String description;
	public String getDescription() {
		return description;
	}

	private ReturnCodeEnum(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getDescription();
	}
}
