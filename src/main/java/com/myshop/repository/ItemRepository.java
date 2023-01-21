package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;
import com.querydsl.core.BooleanBuilder;

//JpaRepository : 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의가 되어있다.
//JpaRepository<사용할 엔티티 클래스, 기본키 타입>
public interface ItemRepository extends JpaRepository<Item, Long>, 
       QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
	//셀렉트 해 와서 담을 리스트
	// item name의 이름으로 찾겠다.
	//select * from item where item_nm = ?
	List<Item> findByItemNm(String itemNm);
	
	//select * from item where item_nm = ? or item_detail = ?
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);
	
	//select * from item where price < ?
	List<Item> findByPriceLessThan(Integer price);
	
	//select * from item where price < ? order by price desc
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
	
	/*
	//퀴즈1
	List<Item> findByItemNmAndItemSellStatus(String itemNm, ItemSellStatus itemSellStatus);
	List<Item> findByPriceBetween(Integer price1, Integer price2);
	List<Item> findByRegTimeAfter(LocalDateTime regTime);
	List<Item> findByItemSellStatusNull();
	List<Item> findByItemDetailLike(String itemDetail);
	*/
	
	//i는 별칭
	//@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	//List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	// String itemDetail 값음 "itemDetail"로 쓸 것이다. 쿼리문에서 꼭 :붙여서 넣어줄 것.
	
														//?1 : 1번째 파라미터
	@Query("select i from Item i where i.itemDetail like %?1% order by i.price desc")
	List<Item> findByItemDetail(String itemDetail);
	
	@Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
	
	
	//퀴즈
	/*
	@Query("select i from Item i where i.price >= :price")
	List<Item> getPrice(@Param("price") Integer price);
	
	@Query("select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :sell")
	List<Item> getItemNmAndItemSellStatus(@Param("itemNm") String itemNm, @Param("sell") ItemSellStatus sell);
	
	@Query(value = "select * from item i where i.item_nm = :itemNm and i.item_sell_status = :#{#sell.name()}", nativeQuery = true)
	List<Item> getItemNmAndItemSellStatus(@Param("itemNm") String itemNm, @Param("sell") ItemSellStatus sell);	
	*/
}