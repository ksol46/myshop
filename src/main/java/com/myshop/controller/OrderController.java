package com.myshop.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myshop.dto.OrderDto;
import com.myshop.dto.OrderHistDto;
import com.myshop.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller //컨트롤러로 사용하겠다.
@RequiredArgsConstructor //생성자 주입
public class OrderController {
	private final OrderService orderService;
	@PostMapping(value = "/order") //id와 count 값을 가져와서 넘겨줌.
	public @ResponseBody ResponseEntity order(@RequestBody @Valid OrderDto orderDto, //@Valid:유효성체크
			BindingResult bindingResult, Principal principal) { //Principal : 현재 로그인 한 사용자 정보를 객체에서 얻어 올 수 있다.
		
		//binding 에러가 발생한다면
		if(bindingResult.hasErrors()) {
			StringBuilder sb = new StringBuilder(); //StringBuilder : 스트링을 다룰 수 있음, 문자열을 추가함. 메모리적으로 효율적으로 사용할 수 있음.
			
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			//어떤 에러가 났을때 FieldErrors가 발생하는데 리스트레 넣어주고 처리를 해준다.
			
			for(FieldError fieldError : fieldErrors) {
				sb.append(fieldError.getDefaultMessage());
			}
			
			//에러메시지를 보내줌.										//에러메시지 코드 400 문법에러.
			return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
		}
		
		//로그인 한 사용자의 이메일을 가져오고
		String  email = principal.getName(); //사용자의 이름을 가져옴.
		Long orderId;
		
		try {
			orderId = orderService.order(orderDto, email); //서비스호출
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}						//orderId 타입이 Long
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
	
	@GetMapping(value = {"/orders", "/orders/{page}"})
	public String orderHist(@PathVariable("page") Optional<Integer> page, Principal principal, Model model) {
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 4);
		Page<OrderHistDto> orderHistDtoList = orderService.getOrderList(principal.getName(), pageable);
		
		model.addAttribute("orders", orderHistDtoList);
		model.addAttribute("page", pageable.getPageNumber()); //현재페이지, 주문할때 필요함
		model.addAttribute("maxPage", 5);
		
		return "order/orderHist";
	}
	
	@PostMapping("/order/{orderId}/cancel")
	public @ResponseBody ResponseEntity cancelOrder(@PathVariable("orderId") Long orderId, Principal principal) {
		if(!orderService.validateOrder(orderId, principal.getName())) {
			return new ResponseEntity<String>("주문 취소 권한이 없습니다.", HttpStatus.FORBIDDEN);
		}
		
		orderService.cancelOrder(orderId);
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
	
	@DeleteMapping("/order/{orderId}/delete")
	public @ResponseBody ResponseEntity deleteOrder(@PathVariable("orderId") Long orderId, Principal principal) {
		if(!orderService.validateOrder(orderId, principal.getName())) {
			return new ResponseEntity<String>("주문 삭제 권한이 없습니다.", HttpStatus.FORBIDDEN);
		}
		orderService.deleteOrder(orderId);
		return new ResponseEntity<Long>(orderId, HttpStatus.OK);
	}
	
	
	
}
