package Web_bonsai.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "discount")
public class Discount {
	@Id
	private String id;
	private int percentDiscount;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date deadline;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(int percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Discount() {
		super();
	}

	public Discount(String id, int percentDiscount, Date deadline) {
		super();
		this.id = id;
		this.percentDiscount = percentDiscount;
		this.deadline = deadline;
	}
	
}
