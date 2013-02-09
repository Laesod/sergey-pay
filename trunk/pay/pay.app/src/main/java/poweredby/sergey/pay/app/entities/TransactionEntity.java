package poweredby.sergey.pay.app.entities;

public class TransactionEntity {
	public static final String NAME = "Transaction";
	
	private String date;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	private String referenceNumber;
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	private String transactionType;
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	private String nameEmail;
	public String getNameEmail() {
		return nameEmail;
	}
	public void setNameEmail(String nameEmail) {
		this.nameEmail = nameEmail;
	}

	private String transactionState;
	public String getTransactionState() {
		return transactionState;
	}
	public void setTransactionState(String transactionState) {
		this.transactionState = transactionState;
	}

	private String amount;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	private String currency;
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	private String purchaseType;
	public String getPurchaseType() {
		return purchaseType;
	}
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	private String shippingDetails;
	public String getShippingDetails() {
		return shippingDetails;
	}
	public void setShippingDetails(String shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	private String note;
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	private String time;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public String[] serializeToStrings() {
		String[] result = new String[11];
		
		result[0] = date;
		result[1] = referenceNumber;
		result[2] = transactionType;
		result[3] = nameEmail;
		result[4] = transactionState;
		result[5] = amount;
		result[6] = currency;
		result[7] = purchaseType;
		result[8] = shippingDetails;
		result[9] = note;
		result[10] = time;
		
		return result;
	}

	public void deserializeFromStrings(String[] values) {
		date = values[0];
		referenceNumber = values[1];
		transactionType = values[2];
		nameEmail = values[3];
		transactionState = values[4];
		amount = values[5];
		currency = values[6];
		purchaseType = values[7];
		shippingDetails = values[8];
		note = values[9];
		time = values[10];
	}
}
