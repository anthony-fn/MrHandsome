package com.anthony.playstation.executor;

import java.util.concurrent.Callable;

public abstract class AJob implements Callable<Integer>
{
	public abstract int execute();
	public abstract boolean isFinished();
}
