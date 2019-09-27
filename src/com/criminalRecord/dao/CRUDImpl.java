package com.criminalRecord.dao;

import java.sql.Date;

import com.criminalRecord.model.Record;

public class CRUDImpl implements CRUD  {

	@Override
	public boolean addRecord(Record record) {
		return Add.addRecord(record);
	}

	@Override
	public int removeAllByName(String name) {
		return Delete.removeAllByName(name);
	}

	@Override
	public int removeLastByName(String name) {
		return Delete.removeLastByName(name);
	}

	@Override
	public boolean view(Date date) {
		return View.view(date);
	}

	@Override
	public boolean view(String name) {
		return View.view(name);
	}

	@Override
	public int transfer(String name, int oldJailNo, int oldCellNo, int newJailNo, int newCellNo) {
		return Update.transfer(name, oldJailNo, oldCellNo, newJailNo, newCellNo);
	}

	@Override
	public int transfer(int oldJailNo, int newJailNo) {
		return Update.transfer(oldJailNo, newJailNo);
	}

	@Override
	public void view() {
		View.view();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	
}
