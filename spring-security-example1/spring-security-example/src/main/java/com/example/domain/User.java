package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


//테이블 연관 관계 설정
@Entity
//table은 생략이 가능하지만 생략하면 클래스 이름을(User)를 그대로 사용한다. 하지만 User라는 시스템 테이블이 있으면 생성하지 못함
@Table(name="users")
@Data
public class User {
	//primary key
	@Id
	private String username;
	
	//Column도 생략이 가능하지만 nullable을 넣기 위해서.
	//name 넣지 않으면 변수 이름이 그대로 컬럼이 됨
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private Boolean enabled;
	
	@Column(nullable = false)
	//Enum 타입 설정
	@Enumerated(EnumType.STRING)
	private Role role;
}
