package dodardmathieu.cgmexercise.core.components.visit.dto;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum VisitReason {
	
	FIRST_VISIT("first"),
	RECURRING_VISIT("recurring"),
	EMERGENCY("emergency");
	
	private String name;
	
	VisitReason(String name) {
		this.name = name;
	}
	
	public static VisitReason fromName(String name) {
		return Arrays.stream(VisitReason.values()).filter(type -> type.name.equals(name)).findFirst().orElse(null);
	}

}
