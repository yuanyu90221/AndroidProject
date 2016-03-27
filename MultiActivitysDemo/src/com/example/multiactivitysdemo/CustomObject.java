package com.example.multiactivitysdemo;

import java.io.Serializable;

/**
 * 自定義的傳輸物件
 * 
 * @author Yuanyu
 *
 */
public class CustomObject implements Serializable {
	//做版本兼容性
	private static final long serialVersionUid = 1L;
	
	private String name = "預設名";
	private JobTypes jobType = JobTypes.FRE;
	private int ages = 0;

	public CustomObject() {
		
	}
	
	public CustomObject(String name, JobTypes jobType, int ages) {
		
		this.name = name;
		this.jobType = jobType;
		this.ages = ages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public JobTypes getJobType() {
		return jobType;
	}

	public void setJobType(JobTypes jobType) {
		this.jobType = jobType;
	}

	public int getAges() {
		return ages;
	}

	public void setAges(int ages) {
		this.ages = ages;
	}
	
	
}
