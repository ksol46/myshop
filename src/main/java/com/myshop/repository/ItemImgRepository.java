package com.myshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myshop.entity.ItemImg;
						//레파지토리는 dao의 역할을 하기 떄문에 db 정보를 가져온다.
public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
	List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);
	
	//상품의 대표 이미지를 찾음
	ItemImg findByItemIdAndRepimgYn(Long itemId, String repimgYn);
}
