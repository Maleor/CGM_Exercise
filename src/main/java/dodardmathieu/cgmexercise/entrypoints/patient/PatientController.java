package dodardmathieu.cgmexercise.entrypoints.patient;

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

import dodardmathieu.cgmexercise.core.components.patient.dto.PatientCreationRequestDto;
import dodardmathieu.cgmexercise.core.components.patient.dto.PatientDto;
import dodardmathieu.cgmexercise.core.components.patient.dto.PatientUpdateRequestDto;
import dodardmathieu.cgmexercise.core.components.patient.mapper.PatientDtoMapper;
import dodardmathieu.cgmexercise.core.components.patient.usecase.CreatePatientUseCase;
import dodardmathieu.cgmexercise.core.components.patient.usecase.DeletePatientUseCase;
import dodardmathieu.cgmexercise.core.components.patient.usecase.GetPatientUseCase;
import dodardmathieu.cgmexercise.core.components.patient.usecase.UpdatePatientUseCase;
import dodardmathieu.cgmexercise.dataproviders.patient.PatientEntity;

@RestController
@RequestMapping(path = "/patient")
public class PatientController {
	
	private final GetPatientUseCase getPatientUseCase;
	
	private final CreatePatientUseCase createPatientUseCase;
	
	private final DeletePatientUseCase deletePatientUseCase;
	
	private final UpdatePatientUseCase updatePatientUseCase;
	
	private final PatientDtoMapper patientDtoMapper;
	
	@Autowired
	public PatientController(GetPatientUseCase getPatientUseCase, PatientDtoMapper patientDtoMapper, 
			CreatePatientUseCase createPatientUseCase, DeletePatientUseCase deletePatientUseCase,
			UpdatePatientUseCase updatePatientUseCase) {
		this.getPatientUseCase = getPatientUseCase;
		this.patientDtoMapper = patientDtoMapper;
		this.createPatientUseCase = createPatientUseCase;
		this.deletePatientUseCase = deletePatientUseCase;
		this.updatePatientUseCase = updatePatientUseCase;
	}
	
	@GetMapping("/{id}")
	public PatientDto findOne(@PathVariable UUID id) {
		return getPatientUseCase.getPatient(id, patientDtoMapper::getPatientDto);
	}

	@GetMapping()
	public List<PatientDto> findAll() {
		return getPatientUseCase.getAll(patientDtoMapper::getPatientDto);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UUID> save(@RequestBody @Valid PatientCreationRequestDto patient) {
		
		UUID newPatientId = createPatientUseCase.create(patient, PatientEntity::getId);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPatientId)
                .toUri())
				.body(newPatientId);


	}
	
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public PatientDto update(@PathVariable UUID id, @RequestBody @Valid PatientUpdateRequestDto patient) {
		return updatePatientUseCase.update(id, patient, patientDtoMapper::getPatientDto);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOne(@PathVariable UUID id) {
		deletePatientUseCase.delete(id);
	}
}
 