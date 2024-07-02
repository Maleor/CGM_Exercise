package dodardmathieu.cgmexercise.core.components.visit.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dodardmathieu.cgmexercise.core.components.patient.mapper.PatientDtoMapper;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitDto;
import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;

@Service
public class VisitDtoMapper {
	
	private final PatientDtoMapper patientDtoMapper;
	
	@Autowired
	public VisitDtoMapper(PatientDtoMapper patientDtoMapper) {
		this.patientDtoMapper = patientDtoMapper;
	}

	/**
	 * Builds a {@link VisitDto} from a {@link VisitEntity} 
	 * 
	 * @param visitEntity The persisted VisitEntity
	 * 
	 * @return The VisitDto
	 */
	public VisitDto getVisitDto(VisitEntity visitEntity) {
		return VisitDto.builder()
				.id(visitEntity.getId())
				.patient(patientDtoMapper.getPatientDto(visitEntity.getPatient()))
				.start(visitEntity.getStart())
				.duration(visitEntity.getDuration())
				.type(visitEntity.getType().getName())
				.reason(visitEntity.getReason().getName())
				.familyHistory(visitEntity.getFamilyHistory())
				.build();

	}
}
