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
public class PatientCreationRequestDto {

	@NotBlank
	private String name;
	@NotBlank
	private String surname;

	private long socialSecurityNumber;

}
