package com.criminalRecord.dao;

import java.sql.Date;

import com.criminalRecord.model.Record;

public interface CRUD extends Runnable {
	
	public boolean addRecord(Record record);
	public int removeAllByName(String name);
	public int removeLastByName(String name);
	public boolean view(Date date);
	public boolean view(String name);
	public void view();
	public int transfer(String name, int oldJailNo, int oldCellNo, int newJailNo, int newCellNo);
	public int transfer(int oldJailNo, int newJailNo);

}
