package dodardmathieu.cgmexercise.core.components.patient.mapper;

import org.springframework.stereotype.Service;

import dodardmathieu.cgmexercise.core.components.patient.dto.PatientDto;
import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;

@Service
public class PatientDtoMapper {

	/**
	 * Builds a {@link PatientDto} from a {@link PatientEntity} 
	 * 
	 * @param patientEntity The persisted PatientEntity
	 * 
	 * @return The PatientDto
	 */
	public PatientDto getPatientDto(PatientEntity patientEntity) {
		return PatientDto.builder()
				.id(patientEntity.getId())
				.name(patientEntity.getName())
				.surname(patientEntity.getSurname())
				.socialSecurityNumber(patientEntity.getSocialSecurityNumber())
				.build();

	}
}
