package dodardmathieu.cgmexercise.core.components.visit.dto;

import java.time.Instant;
import java.util.UUID;

import dodardmathieu.cgmexercise.core.components.patient.dto.PatientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitDto {
	private UUID id;
	private PatientDto patient;
	private Instant start;
	private Integer duration;
	private String type;
	private String reason;
	private String familyHistory;

}
