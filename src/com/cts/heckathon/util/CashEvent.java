package com.cts.heckathon.util;

import java.util.Date;

public class CashEvent {
 int tradeId;
 int accountId;
 Date valueDate;
 double amount;
 Date loadTime;
public int getTradeId() {
	return tradeId;
}
public void setTradeId(int tradeId) {
	this.tradeId = tradeId;
}
public int getAccountId() {
	return accountId;
}
public void setAccountId(int accountId) {
	this.accountId = accountId;
}
public Date getValueDate() {
	return valueDate;
}
public void setValueDate(Date valueDate) {
	this.valueDate = valueDate;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public Date getLoadTime() {
	return loadTime;
}
public void setLoadTime(Date loadTime) {
	this.loadTime = loadTime;
}
@Override
public String toString() {
	return "" + tradeId + "," + accountId + "," + valueDate + ","+ amount + "," + loadTime + "";
}
	
}
