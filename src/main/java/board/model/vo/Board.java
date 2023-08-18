package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3385883267324046402L;
	private int boardNum;
	private String writer;
	private String title;
	private String content;
	private String originalFileName;
	private String renameFileName;
	private int boardRef;
	private int boardReplyRef;
	private int boardLev;
	private int boardReplySeq;
	private int boardReadCount;
	private java.sql.Date boardDate;
	@Override
	public String toString() {
		return "Board [boardNum=" + boardNum + ", writer=" + writer + ", title=" + title + ", content=" + content
				+ ", originalFileName=" + originalFileName + ", renameFileName=" + renameFileName + ", boardRef="
				+ boardRef + ", boardReplyRef=" + boardReplyRef + ", boardLev=" + boardLev + ", boardReplySeq="
				+ boardReplySeq + ", boardReadCount=" + boardReadCount + ", boardDate=" + boardDate + "]";
	}
	public Board(int boardNum, String writer, String title, String content, String originalFileName,
			String renameFileName, int boardRef, int boardReplyRef, int boardLev, int boardReplySeq, int boardReadCount,
			Date boardDate) {
		super();
		this.boardNum = boardNum;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.originalFileName = originalFileName;
		this.renameFileName = renameFileName;
		this.boardRef = boardRef;
		this.boardReplyRef = boardReplyRef;
		this.boardLev = boardLev;
		this.boardReplySeq = boardReplySeq;
		this.boardReadCount = boardReadCount;
		this.boardDate = boardDate;
	}
	public Board() {
		super();
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOriginalFileName() {
		return originalFileName;
	}
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}
	public String getRenameFileName() {
		return renameFileName;
	}
	public void setRenameFileName(String renameFileName) {
		this.renameFileName = renameFileName;
	}
	public int getBoardRef() {
		return boardRef;
	}
	public void setBoardRef(int boardRef) {
		this.boardRef = boardRef;
	}
	public int getBoardReplyRef() {
		return boardReplyRef;
	}
	public void setBoardReplyRef(int boardReplyRef) {
		this.boardReplyRef = boardReplyRef;
	}
	public int getBoardLev() {
		return boardLev;
	}
	public void setBoardLev(int boardLev) {
		this.boardLev = boardLev;
	}
	public int getBoardReplySeq() {
		return boardReplySeq;
	}
	public void setBoardReplySeq(int boardReplySeq) {
		this.boardReplySeq = boardReplySeq;
	}
	public int getBoardReadCount() {
		return boardReadCount;
	}
	public void setBoardReadCount(int boardReadCount) {
		this.boardReadCount = boardReadCount;
	}
	public java.sql.Date getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(java.sql.Date boardDate) {
		this.boardDate = boardDate;
	}
	
}
