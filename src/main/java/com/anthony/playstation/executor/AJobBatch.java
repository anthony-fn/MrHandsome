package com.anthony.playstation.executor;

import com.anthony.playstation.exceptions.JobBatchException;

import mstar.production.common.ConfigManager;

public abstract class AJobBatch
{
	public static int MAX_JOB_NUMBER = Integer.parseInt(ConfigManager.getInstance().getString("MaxJobInBatch"));
	public abstract int getJobNum();
	public abstract int getRemaining();
	public abstract AJob popOneJob()  throws JobBatchException;
	public abstract boolean isFinished();
	public abstract void pushOneJob(AJob job) throws JobBatchException;
	public abstract void getFailedJobs();
}
