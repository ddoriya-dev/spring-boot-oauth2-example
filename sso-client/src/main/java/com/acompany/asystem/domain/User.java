package com.acompany.asystem.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	//
	@Id
	private String userName;
	
	private int age;
	
	private String memo;

	private String tokenId;
	
	protected User() {}
	
	public User(String userName, int age, String memo) {
		//
		this.userName = userName;
		this.age = age;
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		//
		StringBuilder builder = new StringBuilder();
		builder.append("User [userName=");
		builder.append(userName);
		builder.append(", age=");
		builder.append(age);
		builder.append(", memo=");
		builder.append(memo);
		builder.append(", tokenId=");
		builder.append(tokenId);
		builder.append("]");
		return builder.toString();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	
}
