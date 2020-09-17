package kr.co.goodee.dto;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

//typeAliase를 패키지 단위로 잡았을 경우 @Alias를 통해 축약 이름을 정해 준다.
@Alias("board")
public class BoardDTO {
	
	private int idx;
	private String user_name;
	private String subject;
	private String content;
	private Date reg_date;
	private int bhit;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getBhit() {
		return bhit;
	}
	public void setBhit(int bhit) {
		this.bhit = bhit;
	}
	
	
}
