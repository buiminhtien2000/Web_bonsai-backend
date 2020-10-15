package Web_bonsai.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue
	private int id;
	private String userName;
	private String phoneOrEmail;
	private String content;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneOrEmail() {
		return phoneOrEmail;
	}

	public void setPhoneOrEmail(String phoneOrEmail) {
		this.phoneOrEmail = phoneOrEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Comment() {
		super();
	}

	public Comment(int id, String userName, String phoneOrEmail, String content) {
		super();
		this.id = id;
		this.userName = userName;
		this.phoneOrEmail = phoneOrEmail;
		this.content = content;
	}
	
	
}
