package vo;

public class ReplyVO {

	private int rnum;
	private int bnum;
	private String writer;
	private String comment;
	private String regdate;
	
	public ReplyVO(int bnum, String writer, String comment, String regdate) {
		super();
		this.bnum = bnum;
		this.writer = writer;
		this.comment = comment;
		this.regdate = regdate;
	}
	
	
	public ReplyVO(int bnum, String writer, String comment) {
		super();
		this.bnum = bnum;
		this.writer = writer;
		this.comment = comment;
	}

	public int getRnum() {
		return rnum;
	}

	public int getBnum() {
		return bnum;
	}

	public String getWriter() {
		return writer;
	}

	public String getComment() {
		return comment;
	}

	public String getRegdate() {
		return regdate;
	}
	
	
	
	
}
