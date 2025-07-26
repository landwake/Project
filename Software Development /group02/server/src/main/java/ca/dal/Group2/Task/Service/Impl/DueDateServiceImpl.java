package ca.dal.Group2.Task.Service.Impl;

import ca.dal.Group2.Task.Entity.DueDateEntity;
import ca.dal.Group2.Task.Repository.DueDateRepo;
import ca.dal.Group2.Task.Service.DueDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DueDateServiceImpl implements DueDateService {

	@Autowired
	DueDateRepo dueDateRepo;

	@Override
	public DueDateEntity save(DueDateEntity entity){
		
		return dueDateRepo.save(entity);
	}

	@Override
	public List<DueDateEntity> list() {
		return dueDateRepo.findAll();
	}

	@Override
	public DueDateEntity findById(Long id) {
		Optional<DueDateEntity> optional = dueDateRepo.findById(id);
		if(optional.isPresent()){
			return optional.get();
		}else{
			return null;
		}
	}
}
