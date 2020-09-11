package com.shristi.sb.elk.constants;

public enum DataConstants {
	
	SUCCESS("Success"),
	FAILURE("Failure"),
	EMPTY_INDEX("ES index is not having data."),
	EMPTY_COLLECTION("MongoDB collection is not having data."),
	DELETED("Data deleted."),
	DUPLICATE_DATA_SET("While adding we encountered duplicate data; flushed."),
	PARTIALLY_DUPLICATE_DATA_SET("While adding; duplicate data flushed, unique data are added."),
	DATA_SET("Added successfully");
	
	private final String reasonPhrase;


	DataConstants(String reasonPhrase) {
		this.reasonPhrase = reasonPhrase;
	}

	
	public String value() {
		return this.reasonPhrase;
	}

	/**
	 * Return the reason phrase of this status code.
	 */
	public String getReasonPhrase() {
		return this.reasonPhrase;
	}

	/**
	 * Return a string representation of this status code.
	 */
	@Override
	public String toString() {
		return  this.reasonPhrase + name();
	}
			
}
