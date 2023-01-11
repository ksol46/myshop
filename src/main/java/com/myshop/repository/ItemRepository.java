package com.myshop.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;

/*
 * JpaRepository : 기본적인 CRUD 및 페이징 처리를 위한 메소드가 정의 되어있다.
 * 상속을 받아오고, JpaRepository<사용 할 엔티티 클래스, 기본기 타입>
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
	// 셀렉트 해 와서 담을 리스트
	// item name의 이름으로 찾겠다. -> select * from item where item_nm = ?
	List<Item> findByItemNm(String itemNm);

	// select * from item where item_nm = ? or item_detail = ?
	List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

	// select * from item where price < ?
	List<Item> findByPriceLessThan(Integer price);

	// select * from item where price < ? order by price desc
	List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

	/*
	 * //quiz1 : itemNm이 “테스트 상품1” 이고 ItemSellStatus가 Sell인 레코드를 구하는 Junit 테스트 코드를
	 * 완성하시오. List<Item> findByItemNmAndItemSellStatus(String itemNm ,ItemSellStatus
	 * itemSellStatus);
	 * 
	 * //quiz2 : price가 10004~ 10008 사이인 레코드를 구하는 Junit 테스트 코드를 완성하시오. //List<Item>
	 * findByPriceGreaterThanAndPriceLessThan(Integer price, Integer price2);
	 * List<Item> findByPriceBetween(Integer price, Integer price1);
	 * 
	 * //quiz3 : regTime이 2023-1-1 12:12:44 이후의 레코드를 구하는 Juinit 테스트 코드를 완성하시오.
	 * List<Item> findByRegTimeAfter(LocalDateTime regTime);
	 * 
	 * //quiz4 : itemSellStatus가 null이 아닌 레코드를 구하는 Juinit 테스트 코드를 완성하시오. List<Item>
	 * findByItemSellStatusNotNull();
	 * 
	 * //quiz5 : itemDetail이 설명1로 끝나는 레코드를 구하는 Junit 테스트 코드를 완성하시오. //List<Item>
	 * findByItemDetailEndingWith(String itemDetail); List<Item>
	 * findByItemDetailLike(String itemDetail);
	 */
	
	/*
	// i는 별칭
	@Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
	List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
	// String itemDetail 값음 "itemDetail"로 쓸 것이다. 쿼리문에서 꼭 :붙여서 넣어줄 것.
	*/
	
													//?1 : 1번째 파라미터
	@Query("select i from Item i where i.itemDetail like %?1% order by i.price desc")
	List<Item> findByItemDetail(String itemDetail);
	
	@Query(value = "select * from item i where i.item_detail like %:itemDetail% order by i.price desc", nativeQuery = true)
	List<Item> findByItemDetailNative(@Param("itemDetail") String itemDetail);
	
	
	/*
	//quiz2-1 : price가 10005 이상인 레코드를 구하는 @Query 어노테이션을 작성하시오.  
	@Query("select i from Item i where i.price >= :price")
	List<Item> findByPriceLessThanEqual(@Param("price") Integer price);

	
	//quiz2-2 : itemNm이 “테스트 상품1” 이고 ItemSellStatus가 Sell인 레코드를 구하는 @Query 어노테이션을 작성하시오.
	@Query("select i from Item i where i.itemNm = :itemNm and i.itemSellStatus = :sell")
	List<Item> findByItemNmAndItemSellStatus(@Param("itemNm")String itemNm, @Param("sell") ItemSellStatus itemSellStatus);
	*/
	
	
}
