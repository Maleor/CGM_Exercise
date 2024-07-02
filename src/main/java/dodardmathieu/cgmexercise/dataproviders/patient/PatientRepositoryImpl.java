package dodardmathieu.cgmexercise.dataproviders.patient;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dodardmathieu.cgmexercise.core.providers.patient.PatientRepository;

@Repository
public class PatientRepositoryImpl implements PatientRepository{
	
	private final PatientJpaRepository patientJpaRepository;
	
	@Autowired
	public PatientRepositoryImpl(PatientJpaRepository patientJpaRepository) {
		this.patientJpaRepository = patientJpaRepository;
	}
	
	@Override
	public Optional<PatientEntity> find(UUID patientId) {
		return patientJpaRepository.findById(patientId);
	}
	
	@Override
	public List<PatientEntity> findAll() {
		return patientJpaRepository.findAll();
	}
	@Override
	public PatientEntity save(@NotNull PatientEntity patientDto) {
		return patientJpaRepository.save(patientDto);
	}
	
	@Override
	public void delete(UUID patientId) {
		patientJpaRepository.deleteById(patientId);
	}
}
