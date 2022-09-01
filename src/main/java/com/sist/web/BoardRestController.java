package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
// JavaScript , 일반 데이터 , JSON
// update.do , update_ok.do(X) ==> Boot 
import com.sist.dao.*;

@RestController
public class BoardRestController {

	@Autowired
	private BoardDAO dao;
	
	/////////////////////////////////
	
	// 수정 시 비밀번호 일치 여부
	@PostMapping(value="board/update_ok.do", produces = "text/html; charset = UTF-8") // 알림 한글 깨짐 방지
	public String board_update_ok(BoardVO vo)
	{
		// 자바 스크립트 전송
		String result = "";
		boolean bCheck = dao.boardUpdate(vo);
		
		if(bCheck == true)
		{
			result = "<script>"
					+ "location.href=\"detail.do?no="+ vo.getNo() + "\";" // 비번 일치 시 게시글 상세보기로 이동
					+ "</script>";
		}
		else
		{
			result = "<script>"
					+ "alert(\"비밀번호가 틀립니다!!\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	
	// 삭제 처리
	// 삭제 시 비밀번호 일치 여부
	@PostMapping(value="board/delete_ok.do", produces = "text/html; charset = UTF-8") // 알림 한글 깨짐 방지
	public String board_delete_ok(BoardVO vo)
	{
		// 자바 스크립트 전송
		String result = "";
		boolean bCheck = dao.boardDelete(vo);
		
		if(bCheck == true)
		{
			result = "<script>"
					+ "location.href=\"list.do\";" // 비번 일치 시 게시글 상세보기로 이동
					+ "</script>";
		}
		else
		{
			result = "<script>"
					+ "alert(\"비밀번호가 틀립니다!!\");"
					+ "history.back();"
					+ "</script>";
		}
		return result;
	}
	
}










