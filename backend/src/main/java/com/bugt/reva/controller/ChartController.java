package com.bugt.reva.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bugt.reva.model.BugForm;
import com.bugt.reva.service.BugService;
import com.bugt.reva.service.ChartService;

@RestController
@RequestMapping
public class ChartController {

	@Autowired
	ChartService chartService;
	
	@Autowired
	BugService bugService;
	
	@GetMapping("/chart/bugs/{username}/{role}")
	public ResponseEntity<List<List<Object>>> getBugzforChart(@PathVariable("username") String username,
			@PathVariable("role") String role) {
		int open=0;
		int closed=0;
		int retest=0;
		int fixed=0;
		int deferred=0;
		try {
			List<List<Object>> finalList=new ArrayList<List<Object>>();
			List<Object> headerList=new ArrayList<Object>();
			headerList.add("Status");
			headerList.add("count");
			finalList.add(headerList);
			
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
			
			for (int i = 0; i < bugList.size(); i++) {
				BugForm bugForm=bugList.get(i);
				if (bugForm.getStatus().equalsIgnoreCase("open")) {
					open++;
				} else if(bugForm.getStatus().equalsIgnoreCase("close")) {
					closed++;
				} else if(bugForm.getStatus().equalsIgnoreCase("fixed")) {
					fixed++;
				} else if(bugForm.getStatus().equalsIgnoreCase("Re-Test")) {
					retest++;
				} else if(bugForm.getStatus().equalsIgnoreCase("deferred")) {
					deferred++;
				}
			}
			
			List<Object> openList=new ArrayList<Object>();
			openList.add("open");
			openList.add(open);
			finalList.add(openList);
			
			List<Object> closeList=new ArrayList<Object>();
			closeList.add("close");
			closeList.add(closed);
			finalList.add(closeList);
			
			List<Object> reTestList=new ArrayList<Object>();
			reTestList.add("Re-test");
			reTestList.add(retest);
			finalList.add(reTestList);
			
			List<Object> fixedList=new ArrayList<Object>();
			fixedList.add("fixed");
			fixedList.add(fixed);
			finalList.add(fixedList);
			
			List<Object> deferedList=new ArrayList<Object>();
			deferedList.add("Defered List");
			deferedList.add(deferred);
			finalList.add(deferedList);
			
			if (finalList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(finalList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}