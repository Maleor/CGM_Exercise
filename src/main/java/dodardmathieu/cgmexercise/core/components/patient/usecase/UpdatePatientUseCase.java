package dodardmathieu.cgmexercise.core.components.patient.usecase;

import java.util.Optional;
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

/**
 * Class dedicated to {@link PatientEntity} update
 * 
 * @author Maleor
 *
 */
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

	/**
	 * Updates a {@link PatientEntity}
	 * 
	 * @param <T> Expected return type
	 * @param patientId - The id of patient we want to update
	 * @param patientDto - The dto to update the existing patient
	 * @param presenter - Presenter to convert the PatientEntity into the expected return type
	 * 
	 * @return Result of presenter application
	 */
	@Transactional
	public <T> T update(@NotNull UUID patientId, @NotNull PatientUpdateRequestDto patientDto, @NotNull UpdatePatientPresenter<T> presenter) {
		
		PatientEntity existingPatient = patientRepository.find(patientId)
				.orElseThrow(() -> new ResourceNotFoundException(CgmExerciseResource.PATIENT, patientId.toString()));
		
		Optional.ofNullable(patientDto.getName()).ifPresent(newName -> existingPatient.setName(newName));
		Optional.ofNullable(patientDto.getSurname()).ifPresent(newSurname -> existingPatient.setSurname(newSurname));
		
		PatientEntity newPatient = patientRepository.save(existingPatient);
	
		return presenter.apply(newPatient);
	}
}
