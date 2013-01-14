package com.anthony.playstation.executor;

public enum JobStatus {
	
	Default,
	Inited,
	Submitted,
	Running,
	Succeed,
	failed;
	
	public String toString(){	
    	return name();
    }
}
