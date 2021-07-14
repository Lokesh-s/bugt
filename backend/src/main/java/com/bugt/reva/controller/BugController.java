package com.bugt.reva.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bugt.reva.model.BugForm;
import com.bugt.reva.service.BugService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class BugController {
	
	@Autowired
	BugService bugService;
	
	@GetMapping("/bugs/{username}/{role}")
	public ResponseEntity<List<BugForm>> getAllBugs(@PathVariable("username") String username,
			@PathVariable("role") String role){
		try {
			List<BugForm> bugList = new ArrayList<BugForm>();
			if (role.equalsIgnoreCase("Manager")) {
				bugService.findAll().forEach(bugList::add);
			} else {
				List<BugForm> tempList=bugService.findAll();
				for (int i = 0; i < tempList.size(); i++) {
					BugForm bugForm=tempList.get(i);
					if (bugForm.getAssignedTo().equalsIgnoreCase(username)) {
						bugList.add(bugForm);
					}
				}
			}
			if (bugList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(bugList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/bugs/{id}")
	public ResponseEntity<BugForm> getTutorialById(@PathVariable("id") long id) {
		Optional<BugForm> bugData = bugService.findById(id);
		if (bugData.isPresent()) {
			return new ResponseEntity<>(bugData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/bugs")
	public ResponseEntity<BugForm> createBug(@RequestBody BugForm bugForm) {
		try {
			BugForm savedBugForm = bugService.save(bugForm);
			return new ResponseEntity<>(savedBugForm, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/bugs/{id}")
	public ResponseEntity<BugForm> updateBug(@PathVariable("id") long id, @RequestBody BugForm bugForm) {
		Optional<BugForm> tutorialData = bugService.findById(id);

		if (tutorialData.isPresent()) {
			BugForm bugFromDB = tutorialData.get();
			bugFromDB.setBugTitle(bugForm.getBugTitle());
			bugFromDB.setBugDescription(bugForm.getBugDescription());
			bugFromDB.setCreatedBy(bugForm.getCreatedBy());
			//bugFromDB.setCreatedOn(bugForm.getCreatedOn());
			bugFromDB.setTestingType(bugForm.getTestingType());
			bugFromDB.setAttachement(bugForm.getAttachement());
			bugFromDB.setAssignedTo(bugForm.getAssignedTo());
			bugFromDB.setModifiedOn(bugForm.getModifiedOn());
			//bugFromDB.setTargetDate(bugForm.getTargetDate());
			bugFromDB.setStatus(bugForm.getStatus());
			bugFromDB.setPriority(bugForm.getPriority());
			return new ResponseEntity<>(bugService.update(bugFromDB), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/bugs/{id}")
	public ResponseEntity<HttpStatus> deleteBug(@PathVariable("id") long id) {
		try {
			bugService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
