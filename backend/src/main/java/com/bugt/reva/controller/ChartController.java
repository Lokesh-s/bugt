package com.bugt.reva.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

	@GetMapping("/chart/users")
	public ResponseEntity<List<List<Object>>> getBugsForUser() {
		try {
			List<List<Object>> finalList=new ArrayList<List<Object>>();
			List<Object> headerList=new ArrayList<Object>();
			headerList.add("Name");
			headerList.add("Open");
			headerList.add("Closed");
			headerList.add("Fixed");
			finalList.add(headerList);

			HashMap<String, HashMap<String,Integer>> finalMap=new HashMap<>();

			List<BugForm> bugList = new ArrayList<BugForm>();
			bugService.findAll().forEach(bugList::add);

			for (int i =0; i <bugList.size(); i++) {
				BugForm bugForm=bugList.get(i);
				String assignedTo=bugForm.getAssignedTo();
				String bugStatus=bugForm.getStatus();
				if (bugStatus.equalsIgnoreCase("open") || bugStatus.equalsIgnoreCase("close") || bugStatus.equalsIgnoreCase("fixed")) {
					if (finalMap.containsKey(assignedTo)) {
						HashMap<String, Integer> statisticsMap=finalMap.get(assignedTo);
						if (statisticsMap.get(bugStatus)!=null) {
							Integer count=statisticsMap.get(bugStatus);
							statisticsMap.put(bugStatus,count+1);
						}else {
							statisticsMap.put(bugStatus, 1);
						}
					}else {
						HashMap<String, Integer> statisticsMap=new HashMap<String,Integer>();
						statisticsMap.put(bugStatus, 1);
						finalMap.put(assignedTo, statisticsMap);
					}
				}
			}
			for (String userName : finalMap.keySet()) {
				HashMap<String,Integer> individualDetails=finalMap.get(userName);
				List<Object> individualList=new ArrayList<Object>();
				individualList.add(userName);
				if(individualDetails.get("Open")!=null) {
					individualList.add(individualDetails.get("Open"));
				}else {
					individualList.add(0);
				}
				if(individualDetails.get("Close")!=null) {
					individualList.add(individualDetails.get("Close"));
				}else {
					individualList.add(0);
				}
				if(individualDetails.get("Fixed")!=null) {
					individualList.add(individualDetails.get("Fixed"));
				}else {
					individualList.add(0);
				}
				finalList.add(individualList);
			}
			if(finalList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(finalList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}