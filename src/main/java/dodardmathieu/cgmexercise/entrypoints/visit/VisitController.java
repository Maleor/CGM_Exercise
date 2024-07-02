package dodardmathieu.cgmexercise.entrypoints.visit;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import dodardmathieu.cgmexercise.core.components.visit.dto.VisitCreationRequestDto;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitDto;
import dodardmathieu.cgmexercise.core.components.visit.dto.VisitUpdateRequestDto;
import dodardmathieu.cgmexercise.core.components.visit.mapper.VisitDtoMapper;
import dodardmathieu.cgmexercise.core.components.visit.usecase.CreateVisitUseCase;
import dodardmathieu.cgmexercise.core.components.visit.usecase.DeleteVisitUseCase;
import dodardmathieu.cgmexercise.core.components.visit.usecase.GetVisitUseCase;
import dodardmathieu.cgmexercise.core.components.visit.usecase.UpdateVisitUseCase;
import dodardmathieu.cgmexercise.dataproviders.visit.VisitEntity;

@RestController
@RequestMapping(path = "/visit")
public class VisitController {
	
	private final GetVisitUseCase getVisitUseCase;
	
	private final CreateVisitUseCase createVisitUseCase;
	
	private final DeleteVisitUseCase deleteVisitUseCase;
	
	private final UpdateVisitUseCase updateVisitUseCase;
	
	private final VisitDtoMapper visitDtoMapper;
	
	@Autowired
	public VisitController(GetVisitUseCase getVisitUseCase, VisitDtoMapper visitDtoMapper
			, CreateVisitUseCase createVisitUseCase, DeleteVisitUseCase deleteVisitUseCase,
			UpdateVisitUseCase updateVisitUseCase) {
		this.getVisitUseCase = getVisitUseCase;
		this.visitDtoMapper = visitDtoMapper;
		this.createVisitUseCase = createVisitUseCase;
		this.deleteVisitUseCase = deleteVisitUseCase;
		this.updateVisitUseCase = updateVisitUseCase;
	}
	
	@GetMapping("/{id}")
	public VisitDto findOne(@PathVariable UUID id) {
		return getVisitUseCase.getVisit(id, visitDtoMapper::getVisitDto);
	}

	@GetMapping()
	public List<VisitDto> findAll() {
		return getVisitUseCase.getAll(visitDtoMapper::getVisitDto);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UUID> save(@RequestBody @Valid VisitCreationRequestDto visit) {
		
		UUID newVisitId = createVisitUseCase.create(visit, VisitEntity::getId);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newVisitId)
                .toUri())
				.body(newVisitId);


	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public VisitDto update(@PathVariable UUID id, @RequestBody @Valid VisitUpdateRequestDto patient) {
		return updateVisitUseCase.update(id, patient, visitDtoMapper::getVisitDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOne(@PathVariable UUID id) {
		deleteVisitUseCase.delete(id);
	}
}
 