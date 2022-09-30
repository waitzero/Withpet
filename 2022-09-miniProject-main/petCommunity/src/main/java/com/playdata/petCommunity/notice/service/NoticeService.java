package com.playdata.petCommunity.notice.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.playdata.petCommunity.command.NoticeVO;
import com.playdata.petCommunity.entity.Notice;
import com.playdata.petCommunity.util.page.Criteria;
import com.playdata.petCommunity.util.page.PageDTO;

public interface NoticeService {

	PageDTO<Notice> getList(Criteria cri);

	PageDTO<Notice> getListByWriter(Criteria cri);

	NoticeVO getDetailById(Long nno);

	NoticeVO registNotice(NoticeVO noticeVO);
	
	NoticeVO updateNotice(NoticeVO noticeVO,String userId);

	NoticeVO noticeDelete(NoticeVO noticeVO,String userId);

	boolean checkNotice(NoticeVO noticeVO, String userId);

}
