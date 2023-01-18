package com.myshop.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="cart_item") //테이블명 (데이터 베이스에 들어가는 테이블명)
@Getter
@Setter
@ToString
public class CartItem {
	
	@Id
	@Column(name = "cart_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//본인을 기준으로 생각하면 된다. 
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "cart_id")
	private Cart cart; //부모테이블
	
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "item_id")
	private Item item; //부모테이블
	
	private int count;
	
	
}
