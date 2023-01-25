package com.myshop.exception;

public class OutOfStockException extends RuntimeException {
	
	public OutOfStockException (String message) {
		super(message); //부모의 생성자. / super.____ : 부모의 필드나 메소드를 사용하겠다. / super(____): 부모의 생성자를 표시할 떄.
	}
}
