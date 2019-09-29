package com.criminalRecord.dao;

public abstract class ThreadAccessThread<T, T1> extends Thread {
	T t;
	T1 name;
	public ThreadAccessThread(T t, T1 name) {
		this.t = t;
		this.name = name;
	}
	
	public abstract void run();

}
