package dodardmathieu.cgmexercise.core.components.visit.usecase;

import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dodardmathieu.cgmexercise.common.CgmExerciseResource;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitReason;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitType;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitUpdateRequestDto;
import dodardmathieu.cgmexercise.core.providers.visit.VisitRepository;
import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;
import dodardmathieu.cgmexercise.error.exception.ResourceNotFoundException;

@Service
public class UpdateVisitUseCase {

	private final VisitRepository visitRepository;
	
	@Autowired
	public UpdateVisitUseCase(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}
	
	@FunctionalInterface
	public interface UpdateVisitPresenter<T> {
		T apply(VisitEntity visitEntity);
	}

	@Transactional
	public <T> T update(@NotNull UUID visitId, @NotNull VisitUpdateRequestDto visitDto, @NotNull UpdateVisitPresenter<T> presenter) {
		
		VisitEntity existingVisit = visitRepository.find(visitId)
				.orElseThrow(() -> new ResourceNotFoundException(CgmExerciseResource.VISIT, visitId.toString()));
		
		existingVisit.setType(VisitType.fromName(visitDto.getType()));
		existingVisit.setReason(VisitReason.fromName(visitDto.getReason()));
		existingVisit.setFamilyHistory(visitDto.getFamilyHistory());
		
		VisitEntity newVisit = visitRepository.save(existingVisit);
	
		return presenter.apply(newVisit);
	}
}
