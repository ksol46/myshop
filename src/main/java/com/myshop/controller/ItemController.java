package com.myshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myshop.dto.ItemFormDto;
import com.myshop.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor //의존성 주입 final로 할 때 사용함..?
public class ItemController {
	
	private final ItemService itemService;
	
	//상품등록 페이지를 보여줌
	@GetMapping(value = "/admin/item/new")
	public String itemForm(Model model) {
		model.addAttribute("itemFormDto", new ItemFormDto());
		return "item/itemForm";
	}
	
	//상품등록
	@PostMapping(value = "/admin/item/new")
						//유효성 검사
	public String itemNew(@Valid ItemFormDto itemFormDto, BindingResult bindingResult,
			Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList) {
		
		//에러가 있으면 itemForm에서 잡아준다.
		if(bindingResult.hasErrors()) {
			return "item/itemForm";
		}
		
		//첫번째 이미지가 있는지 검사(첫번째 이미지는 필수 입력값이기 떄문에)
		if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
			model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
			return "item/itemForm";
		}
		
		try {
			itemService.saveItem(itemFormDto, itemImgFileList);
		} catch (Exception e) {
			model.addAttribute("errorMessage", "상품 등록 중 에러가 발생했습니다.");
			return "item/itemForm";
		}
		return "redirect:/";
	}
}
