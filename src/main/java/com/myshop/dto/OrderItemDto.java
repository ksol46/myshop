package com.myshop.dto;

import com.myshop.entity.OrderItem;

import lombok.*;

@Getter
@Setter
public class OrderItemDto { //주문데이터를 담는 DTO
	public OrderItemDto(OrderItem orderItem, String imgUrl){
        this.itemNm = orderItem.getItem().getItemNm();
        this.count = orderItem.getCount();
        this.orderPrice = orderItem.getOrderPrice();
        this.imgUrl = imgUrl;
    }

    private String itemNm; //상품명
    private int count; //주문 수량

    private int orderPrice; //주문 금액
    private String imgUrl; //상품 이미지 경로
}
