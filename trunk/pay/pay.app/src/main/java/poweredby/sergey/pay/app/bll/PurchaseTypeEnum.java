package poweredby.sergey.pay.app.bll;

public enum PurchaseTypeEnum {
	SERVICE			(0, "Service"),
	GOODS			(1, "Goods"),
	AUCTION_GOODS	(2, "Auction-Goods"),
	OTHERS			(3, "Others");

	private final int value;
	public int getValue() {
		return value;
	}

	private final String description;
	public String getDescription() {
		return description;
	}

	private PurchaseTypeEnum(int value, String description) {
		this.value = value;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return getDescription();
	}
}
