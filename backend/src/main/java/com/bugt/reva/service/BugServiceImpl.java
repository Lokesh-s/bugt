package com.bugt.reva.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugt.reva.model.BugForm;
import com.bugt.reva.repository.BugRepository;
@Service
public class BugServiceImpl implements BugService {

	@Autowired
	BugRepository bugRepository;
	
	@Override
	public List<BugForm> findAll() {
		return bugRepository.findAll();
	}

	@Override
	public Optional<BugForm> findById(long id) {
		return bugRepository.findById(id);
	}

	@Override
	public BugForm save(BugForm bugForm) {
		return bugRepository.save(bugForm);
	}

	@Override
	public void deleteById(long id) {
		bugRepository.deleteById(id);
	}

	@Override
	public BugForm update(BugForm bugForm) {
		return bugRepository.save(bugForm);
	}
}
