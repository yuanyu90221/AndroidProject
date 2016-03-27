package com.example.multiactivitysdemo;

/**
 * 用來限定職業型態的enum
 * 
 * @author Yuanyu
 *
 */
public enum JobTypes {
	STU("Student", 1), 
	ENG("Engineers", 2), 
	ARM("Army", 3), 
	HSK("HouseKeeper", 4), 
	OFF("Officer", 5), 
	TRA("TravelRelated", 6), 
	HSP("HospitalRelated", 7), 
	RES("RestuarantRelated", 8), 
	FRE("FreeJobers", 9);

	private String typeName;
	private int id;

	private JobTypes(String typeName, int id) {
		this.typeName = typeName;
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
