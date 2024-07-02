package dodardmathieu.cgmexercise.core.components.patient.dto;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
	
	@NotNull
	private UUID id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	private String surname;
	
	private long socialSecurityNumber;

}
