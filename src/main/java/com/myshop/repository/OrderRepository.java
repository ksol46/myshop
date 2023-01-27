package com.myshop.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myshop.entity.Order;
									//레파지토리는 DAO의 역할을 한다.
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	//추상메소드, 현재 로그인 한 사용자의 주문 데이터를 페이징 조건에 맞춰서 가져오는 기능.
													//파라미터를 받아옴 :___
	@Query("select o from Order o where o.member.email = :email order by o.orderDate desc")
	List<Order> findOrders(@Param("email") String email, Pageable pageable);
				//count : Order의 약어 o의 갯수
	@Query("select count(o) from Order o where o.member.email = :email")
	Long countOrder(@Param("email") String email); //현재 로그인한 회원의 주문 갯수가 몇개인지 조회
	
}
