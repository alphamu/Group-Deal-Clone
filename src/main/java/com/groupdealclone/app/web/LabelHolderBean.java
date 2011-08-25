package com.groupdealclone.app.web;

class LabelHolderBean {
	private String label;
	private Long id;
	
	public LabelHolderBean(Long id, String label) {
		this.label = label;
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
