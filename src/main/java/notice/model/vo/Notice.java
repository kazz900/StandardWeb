package notice.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Notice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3482019981650431019L;
	private int noticeNo;
	private String noticeTitle;
	private java.sql.Date noticeDate;
	private String noticeWriter;
	private String noticeContent;
	private String originalFilePath;
	private String renameFilePath;

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public java.sql.Date getNoticeDate() {
		return noticeDate;
	}

	public void setNoticeDate(java.sql.Date noticeDate) {
		this.noticeDate = noticeDate;
	}

	public String getNoticeWriter() {
		return noticeWriter;
	}

	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getOriginalFilePath() {
		return originalFilePath;
	}

	public void setOriginalFilePath(String originalFilePath) {
		this.originalFilePath = originalFilePath;
	}

	public String getRenameFilePath() {
		return renameFilePath;
	}

	public void setRenameFilePath(String renameFilePath) {
		this.renameFilePath = renameFilePath;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeDate=" + noticeDate
				+ ", noticeWriter=" + noticeWriter + ", noticeContent=" + noticeContent + ", originalFilePath="
				+ originalFilePath + ", renameFilePath=" + renameFilePath + "]";
	}

	public Notice(int noticeNo, String noticeTitle, Date noticeDate, String noticeWriter, String noticeContent,
			String originalFilePath, String renameFilePath) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeDate = noticeDate;
		this.noticeWriter = noticeWriter;
		this.noticeContent = noticeContent;
		this.originalFilePath = originalFilePath;
		this.renameFilePath = renameFilePath;
	}

	public Notice() {
		super();
	}

}
