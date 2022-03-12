package tool;

public class ExceptionMessages {
	public static final String PASSPORT_DATA_FORMAT =
			"Incorrectly entered passport data format";
	public static final String INVALID_AMOUNT =
			"Incorrectly entered amount for withdrawal, deposit or transfer of funds";
	public static final String INVALID_CURRENT_ACCUMULATED_AMOUNT =
			"Incorrectly entered bank account accumulated amount";
	public static final String INVALID_NEW_CURRENT_DATE =
			"New current date cannot be earlier than current date";
	public static final String INVALID_BALANCE =
			"Incorrectly entered bank account balance";
	public static final String INSUFFICIENT_FUNDS =
			"Insufficient funds to complete the transaction";
	public static final String DEPOSIT_HAS_NOT_EXPIRED =
			"You cannot withdraw funds until the end of the deposit term";
	public static final String INVALID_FIXED_COMMISSION =
			"Incorrectly entered fixed commission for credit bank account";
	public static final String INVALID_CREDIT_LIMIT =
			"Incorrectly entered credit limit for credit bank account";
	public static final String INVALID_TRANSACTION_LIMIT =
			"Incorrectly entered bank account transaction limit";
	public static final String TRANSACTION_LIMIT_EXCEEDED =
			"You have exceeded the limit on the transaction, " +
					"enter your passport data so that the transaction is successful";
	public static final String INVALID_DEBIT_INTEREST =
			"Incorrectly entered debit interest on bank account balance";
	public static final String INVALID_DEPOSIT_TERM =
			"Incorrectly entered deposit term";
	public static final String INVALID_DEPOSIT_RATES_BY_BOUND_DATA =
			"Invalid data in sorted set of deposit rates by bound";
	public static final String CLIENT_ALREADY_EXISTS =
			"Client with the given passport data is already registered";
	public static final String INVALID_CLIENT_ID =
			"Client with given id not found";
	public static final String ACCOUNT_ALREADY_EXISTS =
			"You cannot open more than one account of the same type";
	public static final String BANK_ALREADY_EXISTS =
			"Bank with given name already exists";
	public static final String INVALID_BANK =
			"Bank not found";
	public static final String INVALID_ACCOUNT =
			"Account not found";
	public static final String INVALID_TRANSACTION =
			"Incorrectly entered transaction data";
	public static final String INVALID_TRANSACTION_ID =
			"Transaction not found";
	public static final String INVALID_ACCOUNT_TYPE =
			"Incorrectly entered account type";
}
