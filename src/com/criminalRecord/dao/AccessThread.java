package com.criminalRecord.dao;

public abstract class AccessThread<T> implements Runnable{
	T value;
	public abstract void initialiseValue();
	AccessThread() { 
		initialiseValue();
	}
}
