package kr.co.goodee.dao;

import java.util.ArrayList;

import kr.co.goodee.dto.BoardDTO;
import kr.co.goodee.dto.FileDTO;

public interface BoardDAO {

	ArrayList<BoardDTO> list();

	int write(BoardDTO bean);

	void wrtieFile(int idx, String orifilename, String newfilename);

	BoardDTO detail(String idx);

	ArrayList<FileDTO> fileList(String idx);

}
