package dodardmathieu.cgmexercise.core.components.visit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitUpdateRequestDto {

	// I did not want to use the creation dto for updates, so I firstly duplicated it because they might become different in the future.
	// For instance, here I commented those following fields to show that there might be data that cannot be updated after creation.
	
	//private UUID patient;
	//private OffsetDateTime start;
	//private Integer duration;
	
	private String type;
	private String reason;
	private String familyHistory;

}
