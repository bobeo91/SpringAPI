package com.vti.entity;

public enum PositionName {
	D_1("DEV1"), D_2("DEV2"), T("TEST"), P("PM"), LD("LEADER"), SCM("SCRUM_MASTER");

	private String value;

	private PositionName(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static PositionName toEnum(String text) {
		for (PositionName x : PositionName.values()) {
			if (x.getValue().equals(text)) {
				return x;
			}
		}

		return null;

	}

}
