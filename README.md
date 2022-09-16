# SpringBoardProject
스프링 게시판 Create, Read, Update, Delete, Find


<h2>sql</h2>

CREATE TABLE spring_board(
      no NUMBER,
      name VARCHAR2(34) CONSTRAINT sb_name_nn NOT NULL,
      subject VARCHAR2(1000) CONSTRAINT sb_sub_nn NOT NULL,
      content CLOB CONSTRAINT sb_cont_nn NOT NULL,
      pwd VARCHAR2(10) CONSTRAINT sb_pwd_nn NOT NULL,
      regdate DATE DEFAULT SYSDATE,
      hit NUMBER DEFAULT 0,
      CONSTRAINT sb_no_pk PRIMARY KEY(no)
);
