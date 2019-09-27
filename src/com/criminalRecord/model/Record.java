package com.criminalRecord.model;

public class Record {
	private String fullName;
	private String crimeType;
	private String crimeDetails;
	private String frontImageURL;
	private String leftImageURL;
	private String rightImageURL;
	private String bloodGroup;
	private String fingerPrintURL;
	private String retinaScanURL;
	private String DNAURL;
	private int jailNo;
	private int jailCell;
	public Record(String fullName, String crimeType, String crimeDetails, String frontImageURL, String leftImageURL,
			String rightImageURL, String bloodGroup, String fingerPrintURL, String retinaScanURL, String dNAURL,
			int jailNo, int jailCell) {
		super();
		this.fullName = fullName;
		this.crimeType = crimeType;
		this.crimeDetails = crimeDetails;
		this.frontImageURL = frontImageURL;
		this.leftImageURL = leftImageURL;
		this.rightImageURL = rightImageURL;
		this.bloodGroup = bloodGroup;
		this.fingerPrintURL = fingerPrintURL;
		this.retinaScanURL = retinaScanURL;
		DNAURL = dNAURL;
		this.jailNo = jailNo;
		this.jailCell = jailCell;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCrimeType() {
		return crimeType;
	}
	public void setCrimeType(String crimeType) {
		this.crimeType = crimeType;
	}
	public String getCrimeDetails() {
		return crimeDetails;
	}
	public void setCrimeDetails(String crimeDetails) {
		this.crimeDetails = crimeDetails;
	}
	public String getFrontImageURL() {
		return frontImageURL;
	}
	public void setFrontImageURL(String frontImageURL) {
		this.frontImageURL = frontImageURL;
	}
	public String getLeftImageURL() {
		return leftImageURL;
	}
	public void setLeftImageURL(String leftImageURL) {
		this.leftImageURL = leftImageURL;
	}
	public String getRightImageURL() {
		return rightImageURL;
	}
	public void setRightImageURL(String rightImageURL) {
		this.rightImageURL = rightImageURL;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getFingerPrintURL() {
		return fingerPrintURL;
	}
	public void setFingerPrintURL(String fingerPrintURL) {
		this.fingerPrintURL = fingerPrintURL;
	}
	public String getRetinaScanURL() {
		return retinaScanURL;
	}
	public void setRetinaScanURL(String retinaScanURL) {
		this.retinaScanURL = retinaScanURL;
	}
	public String getDNAURL() {
		return DNAURL;
	}
	public void setDNAURL(String dNAURL) {
		DNAURL = dNAURL;
	}
	public int getJailNo() {
		return jailNo;
	}
	public void setJailNo(int jailNo) {
		this.jailNo = jailNo;
	}
	public int getJailCell() {
		return jailCell;
	}
	public void setJailCell(int jailCell) {
		this.jailCell = jailCell;
	}
	@Override
	public String toString() {
		return String.format("%-20s %-20s %-20s %-150s  %-150s  %-150s %-10s %-150s  %-150s  %-150s %-6d %-9d",
				fullName, crimeType, crimeDetails, frontImageURL, leftImageURL, rightImageURL, bloodGroup,
				fingerPrintURL, retinaScanURL, DNAURL, jailNo, jailCell);
	}
	
	
	
	
}
