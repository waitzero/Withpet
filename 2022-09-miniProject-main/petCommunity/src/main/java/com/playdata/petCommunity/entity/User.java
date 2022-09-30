package com.playdata.petCommunity.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.playdata.petCommunity.command.UserUpdateVO;
import com.playdata.petCommunity.command.UserVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long uno;
	
	@Column(length = 30, nullable = false)
	private String userName;
	
	@Column(length = 30, nullable = false)
	private String userPhoneNumber;
	
	@Column(length = 30, nullable = false, unique = true)
	private String userId;
	
	@Column(length = 300, nullable = false)
	private String userPw;
	
	@Column(length = 300)
	private String userLocation;
	
	@Column(length = 300)
	private String userLocationDetail;
	
	@Column(columnDefinition = "varchar(30) default '정상 등록'")
	private String userState;
	
	public User updateUserByVO(UserUpdateVO vo) {
		this.userName = vo.getUserName();
		this.userPhoneNumber = vo.getUserPhoneNumber();
		this.userId = vo.getUserId();
		this.userPw = vo.getUserPw();
		this.userLocation = vo.getUserLocation();
		this.userLocationDetail = vo.getUserLocationDetail();
		
		return this;
	}
	
}
