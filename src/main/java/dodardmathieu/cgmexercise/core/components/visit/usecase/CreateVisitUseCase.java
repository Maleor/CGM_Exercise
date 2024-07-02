package dodardmathieu.cgmexercise.core.components.visit.usecase;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.common.CgmExerciseResource;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitCreationRequestDto;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitReason;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitType;
import dodardmathieu.cgmexercise.core.providers.patient.PatientRepository;
import dodardmathieu.cgmexercise.core.providers.visit.VisitRepository;
import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;
import dodardmathieu.cgmexercise.error.exception.ResourceNotFoundException;

@Service
public class CreateVisitUseCase {

	private final VisitRepository visitRepository;
	
	private final PatientRepository patientRepository;
	
	@Autowired
	public CreateVisitUseCase(VisitRepository visitRepository, PatientRepository patientRepository) {
		this.visitRepository = visitRepository;
		this.patientRepository = patientRepository;
	}
	
	@FunctionalInterface
	public interface CreateVisitPresenter<T> {
		T apply(VisitEntity visitEntity);
	}

	@Transactional
	public <T> T create(@NotNull VisitCreationRequestDto visitDto, @NotNull CreateVisitPresenter<T> presenter) {
		
		VisitEntity toSave = patientRepository.find(visitDto.getPatient()).map(patientEntity -> visitRepository.save(VisitEntity.builder()
				.patient(patientEntity)
				.start(visitDto.getStart().toInstant())
				.duration(visitDto.getDuration())
				.type(VisitType.fromName(visitDto.getType()))
				.reason(VisitReason.fromName(visitDto.getReason()))
				.familyHistory(visitDto.getFamilyHistory())
				.build()))
				.orElseThrow(() -> new ResourceNotFoundException(CgmExerciseResource.PATIENT, visitDto.getPatient().toString()));
		
		VisitEntity newVisit = visitRepository.save(toSave);

		return presenter.apply(newVisit);
	}
}
