package poweredby.sergey.pay.app.bll;

public enum CurrencyEnum {
	AUD	("Australian Dollar"),
	BGN	("Bulgarian Lev"),
	CAD	("Canadian Dollar"),
	CHF	("Swiss Franc"),
	CZK	("Czech Koruna"),
	DKK	("Danish Krone"),
	EEK	("Estonia Kroon"),
	EUR	("Euro"),
	GBP	("Pound Sterling"),
	HKD	("Hong Kong Dollar"),
	HUF	("Hungarian Forint"),
	INR	("Indian Rupee"),
	LTL	("Lithuanian Litas"),
	MYR	("Malaysian Ringgit"),
	MKD	("Macedonian Denar"),
	NOK	("Norwegian Krone"),
	NZD	("New Zealand Dollar"),
	PLN	("Polish Zloty"),
	RON	("Romanian New Leu"),
	SEK	("Swedish Krona"),
	SGD	("Singapore Dollar"),
	USD	("U.S. Dollar"),
	ZAR	("South African Rand");
	
	private final String description;
	public String getDescription() {
		return description;
	}

	private CurrencyEnum(String description) {
		this.description = description;
	}
}
