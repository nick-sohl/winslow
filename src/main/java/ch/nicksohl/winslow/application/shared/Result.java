package ch.nicksohl.winslow.application.shared;

import ch.nicksohl.winslow.domain.enumeration.ErrorCode;

public record Result<T>(
		boolean success,
		T data,
		ErrorCode errorCode,
		String errorMessage
) {
	public static <T> Result<T> success(T data) {
		return new Result<>(true, data, null, null);
	}

	public static <T> Result<T> failure(ErrorCode code, String message) {
		return new Result<>(false, null, code, message);
	}

	public boolean isFailure() {
		return !success;
	}
}
