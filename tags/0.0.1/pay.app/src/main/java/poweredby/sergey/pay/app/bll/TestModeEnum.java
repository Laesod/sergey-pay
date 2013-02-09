package poweredby.sergey.pay.app.bll;

public enum TestModeEnum {
	OFF	(0),
	ON	(1);
	
	private final int value;
	public int getValue() {
		return value;
	}

	private TestModeEnum(int value) {
		this.value = value;
	}
}
