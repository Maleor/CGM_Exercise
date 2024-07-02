package dodardmathieu.cgmexercise.core.providers.patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;

public interface PatientRepository {
	
	List<PatientEntity> findAll();

	PatientEntity save(@NotNull PatientEntity patientDto);

	Optional<PatientEntity> find(@NotNull UUID patientId);

	void delete(@NotNull UUID patientId);

}
