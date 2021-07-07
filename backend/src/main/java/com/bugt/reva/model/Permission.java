package com.bugt.reva.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permissions")
public class Permission {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long id;
	    private String roleName;
	    private String statusList;
	    
		public Permission(Long id, String roleName, String statusList) {
			super();
			this.id = id;
			this.roleName = roleName;
			this.statusList = statusList;
		}

		public Permission() {
			super();
		}

		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * @return the roleName
		 */
		public String getRoleName() {
			return roleName;
		}

		/**
		 * @param roleName the roleName to set
		 */
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		/**
		 * @return the statusList
		 */
		public String getStatusList() {
			return statusList;
		}

		/**
		 * @param statusList the statusList to set
		 */
		public void setStatusList(String statusList) {
			this.statusList = statusList;
		}
}
