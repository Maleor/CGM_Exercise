package dodardmathieu.cgmexercise.common;

import dodardmathieu.cgmexercise.error.IResource;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CgmExerciseResource implements IResource {

	PATIENT("PATIENT"),
	VISIT("VISIT");
	
	private final String resourceName;

}
