package dodardmathieu.cgmexercise.dataproviders.visit;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dodardmathieu.cgmexercise.core.providers.visit.VisitRepository;

@Repository
public class VisitRepositoryImpl implements VisitRepository{
	
	private final VisitJpaRepository visitJpaRepository;
	
	@Autowired
	public VisitRepositoryImpl(VisitJpaRepository visitJpaRepository) {
		this.visitJpaRepository = visitJpaRepository;
	}
	
	@Override
	public Optional<VisitEntity> find(UUID visitId) {
		return visitJpaRepository.findById(visitId);
	}
	
	@Override
	public List<VisitEntity> findAll() {
		return visitJpaRepository.findAll();
	}

	@Override
	public VisitEntity save(VisitEntity visitEntity) {
		return visitJpaRepository.save(visitEntity);
	}
	
	@Override
	public void delete(UUID visitId) {
		visitJpaRepository.deleteById(visitId);
	}
}
