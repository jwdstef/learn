package org.zgf.spring.annotation.tx;
/**
 * 自定义异常
 */
public class AccountException extends RuntimeException {

	private static final long serialVersionUID = 9071014115917755007L;

	public AccountException() {
		super();
	}

	public AccountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AccountException(String message, Throwable cause) {
		super(message, cause);
	}

	public AccountException(String message) {
		super(message);
	}

	public AccountException(Throwable cause) {
		super(cause);
	}
	
}
