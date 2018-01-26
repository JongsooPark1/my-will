package com.will.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity		// data와 연결한다는 것을 알려주기 위해
public class User {
	@Id						// 각각의 data를 식별하기 위해 primary key 사용
	@GeneratedValue			// data 추가되면 자동으로 1증가
	private Long id;
	
	@Column(nullable = false, length = 20, unique = true)		// userId data를 null이 안되게 하기 위해, 똑같은 값 가져오지 못하게 unique 설정
	private String userId;
	
	private String password;
	private String name;
	private String email;
	
	public Object getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean matchPassword(String inputPassword) {
		if (inputPassword == null) {
			return false;
		}
		return inputPassword.equals(password);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void update(User newUser) {
		this.password = newUser.password;
		this.name = newUser.name;
		this.email = newUser.email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}
}
