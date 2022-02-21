package vo;

public class BoardVO {

	private int num;
	private String email;
	private String pwd;
	private String subject;
	private String content;
	private String regdate;
	private String ip;
	private int count;
	private String filename;
	private int filesize;
	
	
	public BoardVO(int num, String email, String pwd, String subject, String content, String regdate, String ip, int count,
			String filename, int filesize) {
		super();
		this.num = num;
		this.email = email;
		this.pwd = pwd;
		this.subject = subject;
		this.content = content;
		this.regdate = regdate;
		this.ip = ip;
		this.count = count;
		this.filename = filename;
		this.filesize = filesize;
	}
	
	
	//UpdateController에서 쓸 값 생성
	public BoardVO(int num, String subject, String content) {
		super();
		this.num = num;
		this.subject = subject;
		this.content = content;
	}



	public int getNum() {
		return num;
	}


	public String getEmail() {
		return email;
	}

	public String getPwd() {
		return pwd;
	}

	public String getSubject() {
		return subject;
	}


	public String getContent() {
		return content;
	}


	public String getRegdate() {
		return regdate;
	}


	public String getIp() {
		return ip;
	}


	public int getCount() {
		return count;
	}


	public String getFilename() {
		return filename;
	}


	public int getFilesize() {
		return filesize;
	}


	@Override
	public String toString() {
		return "BoardVO [num=" + num + ", email=" + email + ", pwd=" + pwd + ", subject=" + subject + ", content=" + content
				+ ", regdate=" + regdate + ", ip=" + ip + ", count=" + count + ", filename=" + filename + ", filesize="
				+ filesize + "]";
	}
	
	
	
	
	
}
