package com.bugt.reva.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bugt.reva.model.BugForm;

public interface BugRepository extends JpaRepository<BugForm, Long> {

}
