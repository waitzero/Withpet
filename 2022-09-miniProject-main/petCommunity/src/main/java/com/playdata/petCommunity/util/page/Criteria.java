package com.playdata.petCommunity.util.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Criteria {

	private int page; // 조회하는 페이지 번호
	private int amount; // 조회하는 데이터 개수
	
	private String writer;
	private String title;
	private String content;
	
	public Criteria() {
		this(1, 10);
	}

	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}

}
