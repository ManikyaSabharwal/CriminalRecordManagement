package com.criminalRecord.dao;

import java.sql.Date;

import com.criminalRecord.model.Record;

public class CRUDImpl implements CRUD  {

	@Override
	public synchronized boolean addRecord(Record record) {
		AccessThread<Boolean> runnableImplementation = new AccessThread<Boolean>() {

			@Override
			public void run() {
				this.value = Add.addRecord(record);
			}

			@Override
			public void initialiseValue() {
				this.value = false;
			}
			
		};
		Thread add = new Thread(runnableImplementation);
		add.start();

		return runnableImplementation.value;
	}

	@Override
	public synchronized int removeAllByName(String name) {
		AccessThread<Integer> runnableImplementation = new AccessThread<Integer>() {

			@Override
			public void run() {
				this.value = new Delete().removeAllByName(name);
			}

			@Override
			public void initialiseValue() {
				this.value = 0;
			}
			
		};
		Thread remove = new Thread(runnableImplementation);
		remove.start();
		return runnableImplementation.value;
	}

	@Override
	public int removeLastByName(String name) {
		ThreadAccessThread<Integer, String> thread = new ThreadAccessThread<Integer, String>(0, name) {

			@Override
			public void run() {
				Delete.removeLastByName(name);
				
			}
		};
		thread.start();
		
		return thread.t;
	}

	@Override
	public synchronized boolean view(Date date) {
		AccessThread<Boolean> runnableImplementation = new AccessThread<Boolean>() {

			@Override
			public void run() {
				this.value = new View().view(date);;
			}

			@Override
			public void initialiseValue() {
				this.value = false;
			}
			
		};
		Thread add = new Thread(runnableImplementation);
		add.start();
		return runnableImplementation.value;
	}

	@Override
	public boolean view(String name) {
		AccessThread<Boolean> runnableImplementation = new AccessThread<Boolean>() {

			@Override
			public void run() {
				this.value = new View().view(name);;
			}

			@Override
			public void initialiseValue() {
				this.value = false;
			}
			
		};
		Thread add = new Thread(runnableImplementation);
		add.start();
		return runnableImplementation.value;
	}

	@Override
	public int transfer(String name, int oldJailNo, int oldCellNo, int newJailNo, int newCellNo) {
		AccessThread<Integer> runnableImplementation = new AccessThread<Integer>() {

			@Override
			public void run() {
				this.value = Update.transfer(name, oldJailNo, oldCellNo, newJailNo, newCellNo);
			}

			@Override
			public void initialiseValue() {
				this.value = 0;
			}
			
		};
		Thread add = new Thread(runnableImplementation);
		add.start();
		return runnableImplementation.value;
	}

	@Override
	public int transfer(int oldJailNo, int newJailNo) {
		AccessThread<Integer> runnableImplementation = new AccessThread<Integer>() {

			@Override
			public void run() {
				this.value = Update.transfer(oldJailNo, newJailNo);
			}

			@Override
			public void initialiseValue() {
				this.value = 0;
			}
			
		};
		Thread add = new Thread(runnableImplementation);
		add.start();
		return runnableImplementation.value;
	}

	@Override
	public void view() {
		AccessThread<Boolean> runnableImplementation = new AccessThread<Boolean>() {

			@Override
			public void run() {
				new View().view();;
			}

			@Override
			public void initialiseValue() {
				this.value = false;
			}
			
		};
		Thread add = new Thread(runnableImplementation);
		add.start();
	}

	@Override
	public void run() {
		
	}
	

	
}
