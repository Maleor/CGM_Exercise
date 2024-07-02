package dodardmathieu.cgmexercise.core.components.visit.dto;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum VisitType {
	
	HOME("home"),
	OFFICE("office");
	
	private String name;
	
	VisitType(String name) {
		this.name = name;
	}
	
	public static VisitType fromName(String name) {
		return Arrays.stream(VisitType.values()).filter(type -> type.name.equals(name)).findFirst().orElse(null);
	}

}
