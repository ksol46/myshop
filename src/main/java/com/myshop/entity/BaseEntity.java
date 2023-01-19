package com.myshop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.*;

@EntityListeners(value = {AuditingEntityListener.class}) //Auditing을 적용하기 위해
@MappedSuperclass //부모 클래스를 상속받는 자식 클래스에게 매핑 정보만 제공한다.
@Getter
public class BaseEntity extends BaseTimeEntity {
	/* 스프링이 알아서 등록자와 수정자라고 인식하고 처리를 해준다.
	 * 
	*/
	
	@CreatedBy
	@Column(updatable = false)
	private String createBy; //등록자
	
	@LastModifiedBy
	private String modifiedBy; //수정자
	
	
}
