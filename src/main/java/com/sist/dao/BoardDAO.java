package com.sist.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
// 스프링에서 클래스 관리 제외 (~VO(사용자 정의 데이터형),Mapper(interface))

import com.sist.mapper.BoardMapper;
@Repository
public class BoardDAO {

	@Autowired
	private BoardMapper mapper; // 자동 주입
	
	//////////////////////////
	
	// public List<BoardVO> boardListData(Map map);
	// 게시글 목록
	public List<BoardVO> boardListData(Map map)
	{
		return mapper.boardListData(map);
	}
	
	// @Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	// 게시글 총 페이지
	public int boardTotalPage()
	{
		return mapper.boardTotalPage();
	}
	
	//////////////////////////
	
	// 상세 보기
	public BoardVO boardDetailData(int no)
	{
		mapper.hitIncrement(no);
		
		return mapper.boardDetailData(no);
	}
	
	
	//////////////////////////
	
	// public void boardInsert(BoardVO vo);
	// 게시글 작성
	public void boardInsert(BoardVO vo)
	{
		mapper.boardInsert(vo);
	}
	
	
	//////////////////////////
	
	// public void boardUpdate(BoardVO vo);
	// 게시글 수정 화면
	public BoardVO boardUpdateData(int no)
	{
		return mapper.boardDetailData(no); // 게시글 상세보기로 돌아 감
	}
	
	// 수정 처리
	// 수정 시 비밀번호 일치 여부
	public boolean boardUpdate(BoardVO vo)
	{
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		
		if(db_pwd.equals(vo.getPwd()))
		{
			bCheck = true;
			mapper.boardUpdate(vo);
		}
		else
		{
			bCheck = false;
		}
		return bCheck; // 입력된 비밀번호 일치 여부 반환
	}
	
	
	//////////////////////////
	
	// 삭제 화면
	public BoardVO boardDeleteData(int no)
	{
		return mapper.boardDetailData(no);
	}
	
	// 삭제 처리
	// 비밀번호 일치 여부
	public boolean boardDelete(BoardVO vo)
	{
		boolean bCheck = false;
		String db_pwd = mapper.boardGetPassword(vo.getNo());
		
		if(db_pwd.equals(vo.getPwd()))
		{
			bCheck = true;
			mapper.boardDeleteData(vo.getNo());
		}
		else
		{
			bCheck = false;
		}
		return bCheck;
	}
	
	
	//////////////////////////
	
	// 이름, 제목, 내용  검색
	public List<BoardVO> boardFindData(Map map)
	{
		return mapper.boardFindData(map);
	}
	
	
}









