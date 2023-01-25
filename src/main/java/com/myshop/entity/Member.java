package com.myshop.entity;

import javax.persistence.*;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.myshop.constant.Role;
import com.myshop.dto.MemberFormDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity //db가 해당 객체를 인식 가능!
@Table(name="member") //테이블명
@Getter
@Setter
@ToString
public class Member extends BaseEntity {
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO) //자동생성 어노테이션
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		member.setName(memberFormDto.getName());
		member.setEmail(memberFormDto.getEmail());
		member.setAddress(memberFormDto.getAddress());
		
		String password = passwordEncoder.encode(memberFormDto.getPassword()); //비밀번호 암호화
		member.setPassword(password);
		
		member.setRole(Role.USER);
		
		return member;
	}
}