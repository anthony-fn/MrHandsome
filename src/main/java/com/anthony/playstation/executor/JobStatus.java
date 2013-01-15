package com.anthony.playstation.executor;

public enum JobStatus {
	
	Default,
	Inited,
	Submitted,
	Running,
	Succeed,
	Failed;
	
	public String toString(){	
    	return name();
    }
}
