package com.groupdealclone.app.domain;


public class ChangePassword {
	
	private String currentpwd;
	
	private String newpwd;
	
	private String confirmpwd;
	
	public String getCurrentpwd() {
		return currentpwd;
	}
	
	public void setCurrentpwd(String currentpwd) {
		this.currentpwd = currentpwd;
	}
	
	public String getNewpwd() {
		return newpwd;
	}
	
	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}

	public String getConfirmpwd() {
		return confirmpwd;
	}

	public void setConfirmpwd(String confirmpwd) {
		this.confirmpwd = confirmpwd;
	}
	
	
}
