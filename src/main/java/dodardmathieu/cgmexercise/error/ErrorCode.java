package dodardmathieu.cgmexercise.error;

import lombok.Getter;

public enum ErrorCode {

	ERROR_NOT_FOUND("Not found");
	
	@Getter
	private String message;
	
	private ErrorCode(String message) {
		this.message = message;
	}
	
}
