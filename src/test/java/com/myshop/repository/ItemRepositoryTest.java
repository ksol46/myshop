
package com.myshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import com.myshop.constant.ItemSellStatus;
import com.myshop.entity.Item;
import com.myshop.entity.QItem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.thymeleaf.util.StringUtils;


@SpringBootTest //스프링부트 테스트를 할 것 이라는 어노테이션.
@TestPropertySource(locations = "classpath:application-test.properties")
//실행이 안될 때는 my
class ItemRepositoryTest {

	@Autowired
	ItemRepository itemRepository;
	
	
	@PersistenceContext //영속성 컨텍스트를 사용하기 위해 선언
	EntityManager em; //엔티티 매니저
	
	
	//@Test //테스트 할 메소드 위에 어노테이션을 붙여준다.
	//@DisplayName("상품 저장 테스트") //테스트 할 메소드 이름을 지정해준다. 편리함.
	/*
	public void createItemTest() {
		Item item = new Item();
		item.setItemNm("테스트 상품");
		item.setPrice(10000);
		item.setItemDetail("테스트 상품 상세 설명");
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		
		
		Item savedItem = itemRepository.save(item); //데이터 insert
		
		System.out.println(savedItem.toString());
	}
	*/
	
	public void createItemTest() {
		for (int i=1; i<=10; i++) {
		Item item = new Item();
		item.setItemNm("테스트 상품" + i);
		item.setPrice(10000 + i);
		item.setItemDetail("테스트 상품 상세 설명" + i);
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		Item savedItem = itemRepository.save(item);
	
		}
	}
	
	public void createItemTest2() {
		for (int i=1; i<=5; i++) {
		Item item = new Item();
		item.setItemNm("테스트 상품" + i);
		item.setPrice(10000 + i);
		item.setItemDetail("테스트 상품 상세 설명" + i);
		item.setItemSellStatus(ItemSellStatus.SELL);
		item.setStockNumber(100);
		item.setRegTime(LocalDateTime.now());
		item.setUpdateTime(LocalDateTime.now());
		Item savedItem = itemRepository.save(item);
	
		}
		
		for (int i=6; i<=10; i++) {
			Item item = new Item();
			item.setItemNm("테스트 상품" + i);
			item.setPrice(10000 + i);
			item.setItemDetail("테스트 상품 상세 설명" + i);
			item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
			item.setStockNumber(0);
			item.setRegTime(LocalDateTime.now());
			item.setUpdateTime(LocalDateTime.now());
			Item savedItem = itemRepository.save(item);
		
			}
	}
	
	@Test
	@DisplayName("상품명 조회 테스트")
	public void findByItemNmTest() {
		this.createItemTest(); //item 테이블에 10개가 insert
		List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("상품명, 상품상세설명 or 테스트")
	public void findByItemNmOrItemDetail() {
		this.createItemTest(); //똑같이 item 테이블에 10개가 insert 된다.
		List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1", "테스트 상품 상세 설명5");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("가격 LessThan 테스트")
	public void findByPriceLessThanTest() {
		this.createItemTest(); //똑같이 item 테이블에 10개가 insert 된다.
		List<Item> itemList = itemRepository.findByPriceLessThan(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	@Test
	@DisplayName("가격 내림차순 조회 테스트")
	public void findByPriceLessThanOrderByPriceDescTest() {
		this.createItemTest(); //똑같이 item 테이블에 10개가 insert 된다.
		List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	/*
	@Test
	@DisplayName("퀴즈1")
	public void findByItemNmAndItemSellStatus() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL); //열거형 그대로 가져와서 써야한다.
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	/*
	@Test
	@DisplayName("퀴즈2")
	public void findByPriceGreaterThanAndPriceLessThan() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceGreaterThanAndPriceLessThan(10004, 10008);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	/*
	@Test
	@DisplayName("퀴즈2(쌤 풀이)")
	public void findByPriceBetween() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceBetween(10004, 10008);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	*/
	
	/*
	@Test
	@DisplayName("퀴즈3")
	public void findByRegTimeAfter() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByRegTimeAfter(LocalDateTime.of(2023, 1, 1, 12, 12, 44));
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	/*
	@Test
	@DisplayName("퀴즈3(쌤 풀이)")
	public void findByRegTimeAfter() {
		this.createItemTest();
		LocalDateTime regTime = LocalDateTime.of(2023, 1, 1, 12, 12, 44);
		List<Item> itemList = itemRepository.findByRegTimeAfter(regTime);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	/*
	@Test
	@DisplayName("퀴즈4")
	public void findByitemSellStatusNotNull() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByitemSellStatusNotNull();
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	/*
	@Test
	@DisplayName("퀴즈5")
	public void findByItemDetailEndingWith() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailEndingWith("설명1");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	/*
	 @Test
	@DisplayName("퀴즈5(쌤 풀이)")
	public void findByItemDetailLike() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailLike("%설명1"); //~로 끝나는
		List<Item> itemList = itemRepository.findByItemDetailLike("설명1%"); //~로 시작하는
		List<Item> itemList = itemRepository.findByItemDetailLike("%설명1%"); //~안에 들어있음
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	 */
	
	@Test
	@DisplayName("@Query를 이용한 상품 조회 테스트")
	public void findByItemDetailTest() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetail("테스트 상품 상태 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	
	
	@Test
	@DisplayName("@native Query를 이용한 상품 조회 테스트")
	public void findByItemDetailNative() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemDetailNative("테스트 상품 상태 설명");
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	/*
	//퀴즈 2-1
	@Test
	@DisplayName("퀴즈 2-1")
	public void findByPriceLessThanEqual() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByPriceLessThanEqual(10005);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	
	//퀴즈 2-2
	@Test
	@DisplayName("퀴즈 2-2")
	public void findByItemNmAndItemSellStatus() {
		this.createItemTest();
		List<Item> itemList = itemRepository.findByItemNmAndItemSellStatus("테스트 상품1", ItemSellStatus.SELL);
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
	}
	*/
	
	@Test
	@DisplayName("querydsl 조회 테스트")
	public void queryDslTest() {
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em); //쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;
		
		//select * from item where itemSellStatus = 'SELL' and itemDetail like %테스트 상품 상세 설명% order by price desc;
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
								 .where(qItem.itemDetail.like("%테스트 상품 상세 설명%"))
								 .orderBy(qItem.price.desc());
		
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	@Test
	@DisplayName("querydsl 조회 테스트2")
	public void queryDslTest2() {
		this.createItemTest2();
		JPAQueryFactory qf = new JPAQueryFactory(em); //쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;
		Pageable page = PageRequest.of(0, 2); //of(조회 할 페이지의 번호, 한 페이지당 조회 할 데이터의 갯수)
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
								 .where(qItem.itemDetail.like("%테스트 상품 상세 설명%"))
								 .where(qItem.price.gt(10003))
								 .offset(page.getOffset())
								 .limit(page.getPageSize());
		
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	/*
	@Test
	@DisplayName("퀴즈3-1")
	public void queryDslQuiz3_1Test() {
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em); //쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
								 .where(qItem.itemNm.like("테스트 상품1"))
								 
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	@Test
	@DisplayName("퀴즈3-2")
	public void queryDslQuiz3_2Test() {
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em); //쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.price.between(10004, 10008);
								 
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	@Test
	@DisplayName("퀴즈3-3")
	public void queryDslQuiz3_3Test() {
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em); //쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;
		LocalDateTime regTime = LocalDateTime.of(2023, 1, 1, 12, 12, 44);
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.regTime.after(regTime));
								 
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	@Test
	@DisplayName("퀴즈3-4")
	public void queryDslQuiz3_4Test() {
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em); //쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;
		
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.itemSellStatus.isNotNull());
								 
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	
	@Test
	@DisplayName("퀴즈3-5")
	public void queryDslQuiz3_5Test() {
		this.createItemTest();
		JPAQueryFactory qf = new JPAQueryFactory(em); //쿼리를 동적으로 생성하기 위한 객체
		QItem qItem = QItem.item;
		
		JPAQuery<Item> query = qf.selectFrom(qItem)
								 .where(qItem.itemDetail.like("%설명1"));
								 
		List<Item> itemList = query.fetch();
		
		for (Item item : itemList) {
			System.out.println(item.toString());
		}
		
	}
	*/
}
