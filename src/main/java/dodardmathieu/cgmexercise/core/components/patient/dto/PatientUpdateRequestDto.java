package dodardmathieu.cgmexercise.core.components.patient.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientUpdateRequestDto {

	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	// I did not want to use the creation dto for updates, so I firstly duplicated it because they might become different in the future.
	// For instance, here I commented the following field to show that there might be data that cannot be updated after creation.
		
	//private long socialSecurityNumber;


}
