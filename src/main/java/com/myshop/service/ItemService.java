package com.myshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myshop.dto.ItemFormDto;
import com.myshop.entity.Item;
import com.myshop.entity.ItemImg;
import com.myshop.repository.ItemImgRepository;
import com.myshop.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service //서비스로 사용하겠다.
@RequiredArgsConstructor //생성자를 주입하겠다.
@Transactional //데이터베이스를 다룰 때 추가, 삭제, 수정 등의 작업의 오류발생시 이전으로 돌릴 수 있다.
public class ItemService {
	private final ItemRepository itemRepository;
	private final ItemImgService itemImgService;
	private final ItemImgRepository itemImgRepository;
	//상품등록시 상품과 이미지를 나누어 작업하여서 필요한것들을 모두 사용하려고 선언했음.
	
	//상품등록
	public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception{
		
		//상품등록
		Item item = itemFormDto.createItem();
		itemRepository.save(item); //save : 영속성 컨텍스트를 저장
		
		//이미지등록
		for(int i=0; i<itemImgFileList.size(); i++) {
			ItemImg itemImg = new ItemImg();
			itemImg.setItem(item);
			
			//첫번째 이미지 일 때 대표 상품 이미지 여부 지정
			if(i == 0) {
				itemImg.setRepimgYn("Y");
			} else {
				itemImg.setRepimgYn("N");
			}
			
			itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
		}
			return item.getId();
	}
}
