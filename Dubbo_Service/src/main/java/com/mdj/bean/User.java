package com.mdj.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private String userId;
	private String loginName;
	private String userName;
	private String password;
	private String sex;
	private Date employDate;
	private String email;
	private String officePhone;
	private String homePhone;
	private String mobilePhone;
	private String delStatus;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getEmployDate() {
		return employDate;
	}
	public void setEmployDate(Date employDate) {
		this.employDate = employDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getDelStatus() {
		return delStatus;
	}
	public void setDelStatus(String delStatus) {
		this.delStatus = delStatus;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", loginName=" + loginName
				+ ", userName=" + userName + ", password=" + password
				+ ", sex=" + sex + ", employDate=" + employDate + ", email="
				+ email + ", officePhone=" + officePhone + ", homePhone="
				+ homePhone + ", mobilePhone=" + mobilePhone + ", delStatus="
				+ delStatus + "]";
	}
	
}
