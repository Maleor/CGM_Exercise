package dodardmathieu.cgmexercise.core.components.patient.usecase;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.core.providers.patient.PatientRepository;
import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;

/**
 * Class dedicated to {@link PatientEntity} deletion
 * 
 * @author Maleor
 *
 */

@Service
public class DeletePatientUseCase {

	private final PatientRepository patientRepository;
	
	@Autowired
	public DeletePatientUseCase(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}

	/**
	 * Delete a {@link PatientEntity}
	 * 
	 * @param patientId - Id of the patient to delete
	 */
	@Transactional
	public void delete(@NotNull UUID patientId) {
		patientRepository.delete(patientId);
	}
}
