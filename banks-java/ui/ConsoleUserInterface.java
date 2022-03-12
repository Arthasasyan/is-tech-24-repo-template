package ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleUserInterface<T> implements UserInterface<T> {
	private final Scanner in = new Scanner(System.in);

	@Override
	public String readStringValue(String message) {
		System.out.println((char) 27 + "[35mEnter " + message + " >" + (char)27 + "[0m");
		return in.nextLine();
	}

	@Override
	public long readLongValue(String message) {
		System.out.println((char) 27 + "[35mEnter " + message + " >" + (char) 27 + "[0m");
		while (!in.hasNextLong()) {
			System.out.println((char) 27 + "[35mEnter " + message + " >" + (char) 27 + "[0m");
			in.next();
		}
		return in.nextLong();
	}

	@Override
	public BigDecimal readBigDecimalValue(String message) {
		System.out.println((char) 27 + "[35mEnter " + message + " >" + (char) 27 + "[0m");
		while (!in.hasNextBigDecimal()) {
			System.out.println((char) 27 + "[35mEnter " + message + " >" + (char) 27 + "[0m");
			in.next();
		}
		return in.nextBigDecimal();
	}

	@Override
	public void writeMessage(String message) {
		System.out.println(message);
	}

	@Override
	public void writeReturnedValue(String valueName, T value) {
		System.out.println((char) 27 + "[32m" + valueName + ": " + value + (char)27 + "[0m");
	}

	@Override
	public void writeWarning(String message) {
		System.out.println((char) 27 + "[31m" + message + (char)27 + "[0m");
	}
}
