package com.bugt.reva.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bugs")
public class BugForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String bugTitle;
	private String bugDescription;
	private String createdBy;
	private long createdOn;
	private String testingType;
	private String attachement;
	private String assignedTo;
	private long modifiedOn;
	private long targetDate;
	private String status;
	private String priority;
	
	public BugForm() {
		super();
	}

	public BugForm(long id, String bugTitle, String bugDescription, String createdBy, long createdOn,
			String testingType, String attachement, String assignedTo, long modifiedOn, long targetDate, String status,
			String priority) {
		super();
		this.id = id;
		this.bugTitle = bugTitle;
		this.bugDescription = bugDescription;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.testingType = testingType;
		this.attachement = attachement;
		this.assignedTo = assignedTo;
		this.modifiedOn = modifiedOn;
		this.targetDate = targetDate;
		this.status = status;
		this.priority = priority;
	}

	public long getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(long targetDate) {
		this.targetDate = targetDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBugTitle() {
		return bugTitle;
	}

	public void setBugTitle(String bugTitle) {
		this.bugTitle = bugTitle;
	}

	public String getBugDescription() {
		return bugDescription;
	}

	public void setBugDescription(String bugDescription) {
		this.bugDescription = bugDescription;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public String getTestingType() {
		return testingType;
	}

	public void setTestingType(String testingType) {
		this.testingType = testingType;
	}

	public String getAttachement() {
		return attachement;
	}

	public void setAttachement(String attachement) {
		this.attachement = attachement;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BugForm [id=" + id + ", bugTitle=" + bugTitle + ", bugDescription=" + bugDescription + ", createdBy="
				+ createdBy + ", createdOn=" + createdOn + ", testingType=" + testingType + ", attachement="
				+ attachement + ", assignedTo=" + assignedTo + ", modifiedOn=" + modifiedOn + ", status=" + status
				+ ", priority=" + priority + "]";
	}
}
