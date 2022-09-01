package com.sist.mapper;
import java.util.*;
// C(Create)U(Update)R(Read)D(Delete)
//  INSERT   UPDATE SELECT DELETE
//  응용 => Transaction , JOIN...
//  파일 업로드 (자료) , 지니/멜론 => 조인 => 사용자 순위 결정 

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

import com.sist.dao.BoardVO;

/*
 *    1. XML 
 *    2. Annotation 
 *    3. XML+Annotation => 가장 많이 사용하는 형식 
 *           간단한 SQL : Annotation
 *           복잡한 SQL : XML 
 */
public interface BoardMapper {

	// 목록 출력
	@Select("SELECT no, subject, name, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit, num "
			+ "FROM (SELECT no, subject, name, regdate, hit, rownum as num "
			+ "FROM (SELECT no, subject, name, regdate, hit "
			+ "FROM spring_board ORDER BY no DESC )) "
			+ "WHERE num BETWEEN #{start} AND #{end}" )
	public List<BoardVO> boardListData(Map map); // 자동 구현
	
	// 총 페이지
	@Select("SELECT CEIL(COUNT(*)/10.0) FROM spring_board")
	public int boardTotalPage();
	
	/////////////////////////////
	
	// 상세 보기
	@Select("SELECT no, name, subject, content, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, hit "
			+ "FROM spring_board "
			+ "WHERE no=#{no}")
	public BoardVO boardDetailData(int no); // 게시글 고유번호 불러 옴
	
	// 게시글 조회 수 증가
	@Update("UPDATE spring_board SET "
			+ "hit = hit+1 "
			+ "WHERE no=#{no}")
	public  void hitIncrement(int no); // 게시글 고유번호 불러 옴
	
	
	/////////////////////////////
	
	// 게시글 추가
	@SelectKey(keyProperty = "no", resultType = int.class, before = true,
				statement = "SELECT NVL(MAX(no)+1, 1) as no FROM spring_board")
								// 게시글 고유 번호 자동 증가
	
	@Insert("INSERT INTO spring_board "
			+ "VALUES(#{no}, #{name}, #{subject}, #{content}, #{pwd}, SYSDATE, 0)")
																	// 현재 날짜 , 조회 수
	public void boardInsert(BoardVO vo);
	
	
	///////////////////////////////
	
	// 게시글 수정
	@Update("UPDATE spring_board SET "
			+ "name=#{name}, subject=#{subject}, content=#{content} " // 이름, 제목, 내용 수정
			+ "WHERE no=#{no}")
	public void boardUpdate(BoardVO vo);
	
	// 수정, 삭제 시 비밀번호 검색
	@Select("SELECT pwd FROM spring_board WHERE no=#{no}")
	public String boardGetPassword(int no);
	
	
	
	///////////////////////////
	
	// 게시글 삭제
	@Delete("DELETE FROM spring_board WHERE no=#{no}")
	public void boardDeleteData(int no);
	
	/////////////////////////////
	
	/*
	 * 1. XML
	 * 2. Annotation
	 * 3. XML + Annotation => 가장 많이 사용하는 형식
	 * 		간단한 SQL : Annotation
	 * 		복잡한 SQL : XML
	 */
	
	// 이름, 제목, 내용 검색
	public List<BoardVO> boardFindData(Map map);
}











