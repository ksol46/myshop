package com.myshop.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="order_item") //테이블명
@Getter
@Setter
@ToString
public class OrderItem extends BaseEntity {
	
	@Id
	@Column(name = "order_item_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "order_id")
	private Order order;
	
	private int orderPrice; //주문 가격
	
	private int count; //주문수량
	
	//주문할 상품과 주문 수량을 통해 orderItem 객체를 만듦.
	public static OrderItem createOrderItem(Item item, int count) {
		OrderItem orderItem = new OrderItem();
		orderItem.setItem(item);
		orderItem.setCount(count);
		orderItem.setOrderPrice(item.getPrice());
		
		item.removeStock(count); //재고를 감소시킴
		
		return orderItem;
	}
	
	//주문 한 총 가격
	public int getTotalPrice() {
		return orderPrice*count;
	}
	
}
