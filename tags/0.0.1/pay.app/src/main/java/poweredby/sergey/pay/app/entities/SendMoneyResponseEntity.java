package poweredby.sergey.pay.app.entities;

public class SendMoneyResponseEntity {
	private String retyrnCode;
	public String getRetyrnCode() {
		return retyrnCode;
	}
	public void setRetyrnCode(String retyrnCode) {
		this.retyrnCode = retyrnCode;
	}

	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	private String referenceNumber;
	public String getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	private String testMode;
	public String getTestMode() {
		return testMode;
	}
	public void setTestMode(String testMode) {
		this.testMode = testMode;
	}
}
