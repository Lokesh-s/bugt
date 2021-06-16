package com.bugt.reva.service;

import java.util.List;
import java.util.Optional;

import com.bugt.reva.model.BugForm;

public interface BugService {
	List<BugForm> findAll();
	Optional<BugForm> findById(long id);
	BugForm save(BugForm bugForm);
	BugForm update(BugForm bugForm);
	void deleteById(long id);
}
