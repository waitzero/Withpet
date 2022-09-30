package com.playdata.petCommunity.util.page;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTO<T> {

	// 페이지 네이션을 그리는 클래스
	private int start; // 시작페이지
	private int end; // 끝페이지
	private boolean prev; // 이전 페이지 여부
	private boolean next; // 다음 페이지 여부
	
	// Page<제네릭> 타입을 매개변수로 받아서 필요한 값을 멤버 변수로 저장
	private int page; // 조회하는 페이지 번호
	private int amount; // 데이터 개수
	private long total; // 전체 게시글 수
	private int pageTotal; // 실제 끝 페이지 번호
	private List<T> pageData; // 데이터 *****
	
	private List<Integer> pageList = new ArrayList<>(); // start~end값을 저장하는 리스트
	
	public PageDTO(Page<T> pageable) {
		
		this.page = pageable.getNumber() + 1;
		this.amount = pageable.getSize();
		this.total = pageable.getTotalElements();
		this.pageTotal = pageable.getTotalPages();
		this.pageData = pageable.getContent();
		
		// End페이지 계산
		// 1~10 조회시 -> 10
		// 공식 = (int)(Math.ceil(조회하는 페이지번호 / 10.0)) * 10;
		this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
		
		// start 페이지 계산
		// 공식 = end - 페이지네이션 개수 + 1
		this.start = this.end - 10 + 1;
		
		// 마지막에 그려질 실제 끝 번호를 다시 계산 (실제 끝 페이지 번호)
		// 404개 게시글 -> 41번 페이지 도달 했을때 end = 50, 페이지 토탈 = 41 -> 페이지 토탈을 따라감
		this.end = this.end > this.pageTotal ? this.pageTotal : this.end;
		
		// 다음버튼 활성화 여부
		this.next = this.pageTotal > this.end;
		
		// 이전버튼 활성화 여부
		this.prev = this.start > 1;
		
		// 타임리프에서는 페이지네이션값을 리스트 형태로 저장
		for(int i = this.start; i <= this.end; i++) {
			this.pageList.add(i);
		}
		
	}
	
}
