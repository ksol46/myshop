package com.myshop.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.myshop.constant.OrderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="orders") //테이블명
@Getter
@Setter
@ToString
public class Order {
	
	@Id
	@Column(name = "order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	private LocalDateTime orderDate; //주문일, 주문날짜
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus; //주문상태
	
			   //양방향설정		//cascade는 부모에 적어준다. ALL은 모든 종류가 적용됨.//orphanRemoval : 부모가 아닌 자식테이블일때 사용. 부모가 사라지고 자식만 남을 때 설정해주면 같이 삭제된다.
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY) //OrderItem에 있는 order에 의해 관리가 된다.
	private List<OrderItem> orderItems = new ArrayList<>();
}
