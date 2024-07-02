package dodardmathieu.cgmexercise.core.components.patient.usecase;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.core.components.patient.dto.PatientCreationRequestDto;
import dodardmathieu.cgmexercise.core.providers.patient.PatientRepository;
import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;

/**
 * Class dedicated to {@link PatientEntity} creation
 * 
 * @author Maleor
 *
 */
@Service
public class CreatePatientUseCase {

	private final PatientRepository patientRepository;
	
	@Autowired
	public CreatePatientUseCase(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	@FunctionalInterface
	public interface CreatePatientPresenter<T> {
		T apply(PatientEntity patientEntity);
	}

	/**
	 * Creates a new {@link PatientEntity}
	 * 
	 * @param <T> - Expected return type
	 * @param patientDto - Creation request
	 * @param presenter - Presenter to convert the PatientEntity into the expected return type
	 * 
	 * @return Result of presenter application
	 */
	@Transactional
	public <T> T create(@NotNull PatientCreationRequestDto patientDto, @NotNull CreatePatientPresenter<T> presenter) {
		PatientEntity newPatient = patientRepository.save(PatientEntity.builder()
				.name(patientDto.getName())
				.surname(patientDto.getSurname())
				.socialSecurityNumber(patientDto.getSocialSecurityNumber())
				.build());
	
		return presenter.apply(newPatient);
	}
}
