package retrofit2.adapter;

import retrofit2.Response;

public interface AdapterException {

	/** HTTP status code. */
	int code();

	/** HTTP status message. */
	String message();

	/**
	   * The full HTTP response. This may be null if the exception was serialized.
	   */
	Response<?> response();

}