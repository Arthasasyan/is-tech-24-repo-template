package ui;

import java.math.BigDecimal;

public interface UserInterface<T> {
	String readStringValue(String message);
	long readLongValue(String message);
	BigDecimal readBigDecimalValue(String message);
	void writeMessage(String message);
	void writeReturnedValue(String valueName, T value);
	void writeWarning(String message);
}
