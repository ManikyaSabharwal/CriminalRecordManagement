package com.criminalRecord.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.criminalRecord.model.Record;

public class CRUDImplTest {

	static Scanner in = new Scanner(System.in);

	public static void main(String[] args) {

		int loginValue = -1;
		Login signin = new Login();
		while (true) {
			System.out.println("1-Login\n" + "2-Register a new user\n" + "3-Close the application");
			loginValue = in.nextInt();
			if (loginValue == 3)
				break;
			switch (loginValue) {
			case 1:
				in.nextLine();
				System.out.println("Enter username");
				String userName = in.nextLine();
				System.out.println("Enter Password");
				String password = in.nextLine();
				if (signin.login(userName, password)) {
					int choice = -1;
					while (true) {
						System.out.println("0-Logout\n" + "1-add\n" + "2-Remove all records of a person\n"
								+ "3-Remove lastest record of the person\n" + "4-View all Records\n"
								+ "5-View all records of a person\n" + "6-View all records of a particular Date\n"
								+ "7-Transfer single person\n" + "8-Transfer all prisoners to another jail");

						choice = in.nextInt();
						if (choice == 0)
							break;

						switch (choice) {
						case 1:
							testAddRecord();
							break;
						case 2:
							testRemoveAllByName();
							break;
						case 3:
							testRemoveLastByName();
							break;
						case 4:
							testView();
							break;
						case 5:
							testViewName();
							break;
						case 6:
							testViewDate();
							break;
						case 7:
							testTransferSingle();
							break;
						case 8:
							testTransfer();
							break;
						default:
							System.out.println("Enter a valid option");
						}
					}
				} else
					System.out.println("Invalid Username/Password");
				break;
			case 2:
				in.nextLine();
				System.out.println("Enter Admin details");
				System.out.println("Username: ");
				String adminUserName = in.nextLine();
				System.out.println("Password: ");
				String adminPassword = in.nextLine();
				if (adminUserName.contentEquals("admin")) {
					boolean userCreated = false;
					while (signin.login(adminUserName, adminPassword) && !userCreated) {
						Registeration registeration = new Registeration();
						System.out.println("Enter UserName");
						String userName1 = in.nextLine();
						String password1 = "";
						if (!registeration.findByUserName(userName1)) {
							System.out.println("Enter Password");
							password1 = in.nextLine();
							if (registeration.verifyPassword(password1)) {
								Registeration.register(userName1, password1);
								System.out.println("Sucessfully Registered");
								userCreated = true;
							} else {
								System.out.println("Password does not meet minimun requirements.\n"
										+ "Password must contain atleast one upercase, one lowercase letter \n"
										+ " and special character(@#$%)\n" + "Min lengh: 6 and Max Length: 20");
							}
						} else {
							System.out.println("Username already present");
						}
					}
				} else {
					System.out.println("Enter valid User Details for admin");
				}
				break;
			default:
				System.out.println("Enter Valid option ");
				break;
			}
		}

		in.close();

	}

	public static void testAddRecord() throws InputMismatchException {
		String fullName;
		String crimeType;
		String crimeDetails;
		String frontImageURL;
		String leftImageURL;
		String rightImageURL;
		String bloodGroup;
		String fingerPrintURL;
		String retinaScanURL;
		String DNAURL;
		int jailNo;
		int jailCell;

		in.nextLine();
		System.out.println("Enter Full Name:");
		fullName = in.nextLine();
		System.out.println("Enter Crime type:");
		crimeType = in.nextLine();
		System.out.println("Enter crime Details:");
		crimeDetails = in.nextLine();
		System.out.println("Enter front Image URL::");
		frontImageURL = in.nextLine();
		System.out.println("Enter left Image URL:");
		leftImageURL = in.nextLine();
		System.out.println("Enter right Image URL:");
		rightImageURL = in.nextLine();
		System.out.println("Enter blood Group:");
		bloodGroup = in.nextLine();
		System.out.println("Enter finger Print URL:");
		fingerPrintURL = in.nextLine();
		System.out.println("Enter retina Scan URL:");
		retinaScanURL = in.nextLine();
		System.out.println("Enter DNA URL:");
		DNAURL = in.nextLine();
		System.out.println("Enter Jail No:");
		while (!in.hasNextInt()) {
			System.out.println("Enter a number for Jail No");
		}
		jailNo = in.nextInt();
		System.out.println("Enter Jail Cell:");
		while (!in.hasNextInt()) {
			System.out.println("Enter a number for Cell No");
		}
		jailCell = in.nextInt();
		Record record = new Record(fullName, crimeType, crimeDetails, frontImageURL, leftImageURL, rightImageURL,
				bloodGroup, fingerPrintURL, retinaScanURL, DNAURL, jailNo, jailCell);
		CRUDImpl crudImpl = new CRUDImpl();
		boolean added = crudImpl.addRecord(record);
		if (added)
			System.out.println("Record added successfully.");
		else
			System.out.println("Record couldn't be added successfully.");
		in.nextLine();
	}

	public static void testRemoveAllByName() {
		CRUDImpl crudImpl = new CRUDImpl();
		System.out.println("Enter name to delete");
		in.nextLine();
		String name = in.nextLine();
		int records = 0;
		System.out.println((records = crudImpl.removeAllByName(name)) > 0
				? records + " record(s) by the name " + name + " removed Successfully"
				: records + " record found by the name: " + name);
	}

	public static void testRemoveLastByName() {
		CRUDImpl crudImpl = new CRUDImpl();
		System.out.println("Enter name to delete");
		in.nextLine();
		String name = in.nextLine();
		int records = 0;
		System.out.println((records = crudImpl.removeLastByName(name)) > 0
				? records + " record(s) by the name " + name + " removed Successfully"
				: records + " record found by the name: " + name);
	}

	public static void testView() {
		CRUDImpl crudImpl = new CRUDImpl();
		crudImpl.view();
	}

	public static void testViewName() {
		CRUDImpl crudImpl = new CRUDImpl();
		System.out.println("Enter the name to search in records");
		in.nextLine();
		String name = in.nextLine();
		if (!crudImpl.view(name)) {
			System.out.println("No record Found.");
		}
	}

	public static void testViewDate() {
		CRUDImpl crudImpl = new CRUDImpl();
		System.out.println("Enter the date to search in records(yyyy-mm-dd)");
		in.nextLine();
		String stringDate = in.nextLine();
		Date utilDate;
		try {
			utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(stringDate);
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			crudImpl.view(sqlDate);
		} catch (ParseException e) {
			System.out.println("Date format yyyy-mm-dd.Try again.");
		}
	}

	public static void testTransferSingle() {
		CRUDImpl crudImpl = new CRUDImpl();
		String name;
		int oldJailNo;
		int oldCellNo;
		int newJailNo;
		int newCellNo;

		in.nextLine();
		System.out.println("Enter the details of the person you want to transfer");
		System.out.println("Enter Name : ");
		name = in.nextLine();
		System.out.println("Enter Current Jail No. : ");
		oldJailNo = in.nextInt();
		System.out.println("Enter Current Jail Cell No. : ");
		oldCellNo = in.nextInt();
		System.out.println("Enter New Jail No. : ");
		newJailNo = in.nextInt();
		System.out.println("Enter New Jail Cell No. : ");
		newCellNo = in.nextInt();

		crudImpl.transfer(name, oldJailNo, oldCellNo, newJailNo, newCellNo);
	}

	public static void testTransfer() {
		int oldJailNo;
		int newJailNo;
		CRUDImpl crudImpl = new CRUDImpl();

		System.out.println("Enter current Jail No.");
		oldJailNo = in.nextInt();
		System.out.println("Enter new Jail No.");
		newJailNo = in.nextInt();
		crudImpl.transfer(oldJailNo, newJailNo);
	}

}
