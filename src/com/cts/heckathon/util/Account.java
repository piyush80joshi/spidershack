package com.cts.heckathon.util;

public class Account {
int accountId;
String accountName;
String legalEntityCode;
String ccy;
String region;
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public String getAccountName() {
	return accountName;
}
public void setAccountName(String accountName) {
	this.accountName = accountName;
}
public String getLegalEntityCode() {
	return legalEntityCode;
}
public void setLegalEntityCode(String legalEntityCode) {
	this.legalEntityCode = legalEntityCode;
}
public String getCcy() {
	return ccy;
}
public void setCcy(String ccy) {
	this.ccy = ccy;
}
public String getRegion() {
	return region;
}
public void setRegion(String region) {
	this.region = region;
}
@Override
public String toString() {
	return "" + accountId + "," + accountName + "," + legalEntityCode + "," + ccy + "," + region + "";
}

}
