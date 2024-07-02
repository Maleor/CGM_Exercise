package dodardmathieu.cgmexercise.core.components.visit.usecase;

import java.util.Optional;
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

/**
 * Class dedicated to {@link VisitEntity} update
 * 
 * @author Maleor
 *
 */
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

	/**
	 * Updates a {@link VisitEntity}
	 * 
	 * @param <T> Expected return type
	 * @param visitId - The id of visit we want to update
	 * @param visitDto - The dto to update the existing visit
	 * @param presenter - Presenter to convert the VisitEntity into the expected return type
	 * 
	 * @return Result of presenter application
	 */
	@Transactional
	public <T> T update(@NotNull UUID visitId, @NotNull VisitUpdateRequestDto visitDto, @NotNull UpdateVisitPresenter<T> presenter) {
		
		VisitEntity existingVisit = visitRepository.find(visitId)
				.orElseThrow(() -> new ResourceNotFoundException(CgmExerciseResource.VISIT, visitId.toString()));
		
		Optional.ofNullable(visitDto.getType()).ifPresent(newType -> existingVisit.setType(VisitType.fromName(newType)));
		Optional.ofNullable(visitDto.getReason()).ifPresent(newReason -> existingVisit.setReason(VisitReason.fromName(newReason)));
		Optional.ofNullable(visitDto.getFamilyHistory()).ifPresent(newFamilyHistory -> existingVisit.setFamilyHistory(newFamilyHistory));;
		
		VisitEntity newVisit = visitRepository.save(existingVisit);
	
		return presenter.apply(newVisit);
	}
}
