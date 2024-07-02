package dodardmathieu.cgmexercise.core.components.visit.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitCreationRequestDto {

	@NotNull
	private UUID patient;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private OffsetDateTime start;
	
	private Integer duration;
	
	private String type;
	private String reason;
	private String familyHistory;

}
