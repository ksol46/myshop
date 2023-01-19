package com.myshop.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.*;

@EntityListeners(value = {AuditingEntityListener.class}) //Auditing을 적용하기 위해
@MappedSuperclass //부모 클래스를 상속받는 자식 클래스에게 매핑 정보만 제공한다.
@Getter
@Setter
public class BaseTimeEntity {

	@CreatedDate //엔티티가 생성되어서 저장 될 때 시간을 자동으로 저장.
	@Column(updatable = false) // SQL UPDATE문에 해당 컬럼을 포함할지 여부. 읽기 전용이라고 볼 수 있다.
	private LocalDateTime regTime; //등록날짜
	
	@LastModifiedDate
	private LocalDateTime upDateTime; //수정날짜
}
