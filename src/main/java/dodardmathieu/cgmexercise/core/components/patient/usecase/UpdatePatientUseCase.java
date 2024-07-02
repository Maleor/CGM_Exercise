package dodardmathieu.cgmexercise.core.components.patient.usecase;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.common.CgmExerciseResource;
import dodardmathieu.cgmexercise.core.components.patient.dto.PatientUpdateRequestDto;
import dodardmathieu.cgmexercise.core.providers.patient.PatientRepository;
import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;
import dodardmathieu.cgmexercise.error.exception.ResourceNotFoundException;

@Service
public class UpdatePatientUseCase {

	private final PatientRepository patientRepository;
	
	@Autowired
	public UpdatePatientUseCase(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	@FunctionalInterface
	public interface UpdatePatientPresenter<T> {
		T apply(PatientEntity patientEntity);
	}

	@Transactional
	public <T> T update(@NotNull UUID patientId, @NotNull PatientUpdateRequestDto patientDto, @NotNull UpdatePatientPresenter<T> presenter) {
		
		PatientEntity existingPatient = patientRepository.find(patientId)
				.orElseThrow(() -> new ResourceNotFoundException(CgmExerciseResource.PATIENT, patientId.toString()));
		
		existingPatient.setName(patientDto.getName());
		existingPatient.setSurname(patientDto.getSurname());
		
		PatientEntity newPatient = patientRepository.save(existingPatient);
	
		return presenter.apply(newPatient);
	}
}
