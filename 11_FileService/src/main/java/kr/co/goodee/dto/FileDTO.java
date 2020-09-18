package kr.co.goodee.dto;

import org.apache.ibatis.type.Alias;

@Alias("files")
public class FileDTO {

	private int fileidx;
	private int idx;
	private String orifilename;
	private String newfilename;
	
	public int getFileidx() {
		return fileidx;
	}
	public void setFileidx(int fileidx) {
		this.fileidx = fileidx;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getOrifilename() {
		return orifilename;
	}
	public void setOrifilename(String orifilename) {
		this.orifilename = orifilename;
	}
	public String getNewfilename() {
		return newfilename;
	}
	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}
	
	
}
