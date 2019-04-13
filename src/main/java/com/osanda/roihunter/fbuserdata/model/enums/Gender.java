package com.osanda.roihunter.fbuserdata.model.enums;

public enum Gender {

	MALE("male"), FEMALE("female");

	private final String fieldDescription;

	private Gender(String value) {
		fieldDescription = value;
	}

	public String getFieldDescription() {
		return fieldDescription;
	}

	public static Gender getGender(String gen) {
		return Gender.valueOf(gen.toUpperCase());
	}

}
