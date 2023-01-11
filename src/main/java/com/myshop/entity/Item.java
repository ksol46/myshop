package com.myshop.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import com.myshop.constant.ItemSellStatus;

import javax.persistence.*;
import lombok.*;

@Entity
@Table(name="item") //테이블명 (데이터 베이스에 들어가는 테이블명)
@Getter
@Setter
@ToString
public class Item {
	//not null이 아니면 int가 아니고 Integer로 써야한다, 필드 타입을 객체로 지정해야 한다.
	
	@Id
	@Column(name="item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //상품코드
	
	@Column(nullable = false, length = 50)
	private String itemNm; //상품명
	
	@Column(nullable = false, name = "price")
	private int price; //가격
	
	@Column(nullable = false)
	private int stockNumber; //재고수량
	
	@Lob
	@Column(nullable = false)
	private String itemDetail; //상품 상세설명
	
	@Enumerated(EnumType.STRING) //열거형 타입으로 설정시 내가 만들어놓은 상수만 사용할 수 있음
	private ItemSellStatus itemSellStatus; //상품 판매상태
	
	private LocalDateTime regTime; //등록 시간
	
	private LocalDateTime updateTime; //수정 시간
	
}
