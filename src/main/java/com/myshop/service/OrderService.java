package com.myshop.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import com.myshop.dto.OrderDto;
import com.myshop.dto.OrderHistDto;
import com.myshop.dto.OrderItemDto;
import com.myshop.entity.Item;
import com.myshop.entity.ItemImg;
import com.myshop.entity.Member;
import com.myshop.entity.Order;
import com.myshop.entity.OrderItem;
import com.myshop.repository.ItemImgRepository;
import com.myshop.repository.ItemRepository;
import com.myshop.repository.MemberRepository;
import com.myshop.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service // service 클래스의 역할
@Transactional // 서비스 클래스에서 로직을 처리하다가 에러가 발생하면 로직을 수행하기 이전 상태로 되돌려 준다!
@RequiredArgsConstructor
public class OrderService {
	private final ItemRepository itemRepository;
	private final MemberRepository memberRepository;
	private final OrderRepository orderRepository;
	private final ItemImgRepository itemImgRepository; //의존성주입!!!!!
	
	public Long order(OrderDto orderDto, String email) {
		Item item = itemRepository.findById(orderDto.getItemId()) //주문하는 아이템 아이디을 먼저 찾아옴.
				.orElseThrow(EntityNotFoundException::new);
		
		Member member = memberRepository.findByEmail(email);
		
		List<OrderItem> orderItemList = new ArrayList<>();
		OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
		orderItemList.add(orderItem);
		
		Order order = Order.createOrder(member, orderItemList);
		orderRepository.save(order);
		
		return order.getId();
	}
	
	//데이터를 가공하는 페이징 객체를 만든다.
	@Transactional(readOnly = true)
	public Page<OrderHistDto> getOrderList(String email, Pageable pageable) {
		//주문 목록
		List<Order> orders = orderRepository.findOrders(email, pageable);
		//총 주문 목록 갯수
		Long tatalCount = orderRepository.countOrder(email);
		
		List<OrderHistDto> orderHistDtos = new ArrayList<>();
		
		for(Order order : orders) {
			OrderHistDto orderHistDto = new OrderHistDto(order);
			List<OrderItem> orderItems = order.getOrderItems();
			//장바구니 기능을 대비해서 for문으로 풀어줌.
			for(OrderItem orderItem : orderItems) {
				//상품의 대표 이미지
				ItemImg itemImg = itemImgRepository.findByItemIdAndRepimgYn(orderItem.getItem().getId(),"Y");
				OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
				orderHistDto.addOrderItemDto(orderItemDto);
			}
			
			orderHistDtos.add(orderHistDto);
		}
		return new PageImpl<OrderHistDto>(orderHistDtos, pageable, tatalCount);
	}

	//현재 로그인 한 사용자와 주문데이터를 생성한 사용자와 같은지 검사 / 검사를 통과하면
	@Transactional(readOnly = true)
	public boolean validateOrder(Long orederId, String email) {
		Member curMember = memberRepository.findByEmail(email); //이메일로 사용자 멤버 객체 가져옴.
		Order order = orderRepository.findById(orederId)
									 .orElseThrow(EntityNotFoundException::new);
		Member savedMember = order.getMember(); //주문한 사용자 찾기
		
		if(!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) {
			return false;
		}
		return true;
	}
	
	//주문취소 하는 서비스 / 주문 취소가 됨.
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
									 .orElseThrow(EntityNotFoundException::new);
		order.cancelOrder();
	}
	
	//주문 삭제 / order entity에 관계성을 다 매핑해놨기 떄문에 orders에서 지우면 orderitem도 같이 지워짐.
	public void deleteOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
									 .orElseThrow(EntityNotFoundException::new);
		orderRepository.delete(order);
	}

}
