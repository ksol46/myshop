package com.myshop.entity;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="cart") //테이블명 (데이터 베이스에 들어가는 테이블명)
@Getter
@Setter
@ToString
public class Cart {

	@Id
	@Column(name = "cart_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//서로 참조 관계일 때 자식 엔티티에 작성해준다.
	@OneToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "member_id")
	private Member member;
}
