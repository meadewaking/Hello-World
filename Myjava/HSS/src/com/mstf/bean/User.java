package com.mstf.bean;

/*
 * User实体bean
 */
public class User {
	private int user_id;
	private String username;
	private String password;
	private int age;
	private int priority;
	
	public User(){
		
	}
	
	public User(String username, int age, int priority){
		this.username = username;
		this.age = age;
		this.priority = priority;
	}
	
	public User(String username, String password, int age, int priority) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
		this.priority = priority;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username
				+ ", password=" + password + ", age=" + age + ", priority="
				+ priority + "]";
	}
}
