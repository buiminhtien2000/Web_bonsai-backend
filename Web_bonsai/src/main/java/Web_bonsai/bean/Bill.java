package Web_bonsai.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "bill" )
public class Bill {
	@Id
	@GeneratedValue
	private int id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dateOfSale;
	private int idUser;
	private int idProduct;
	private String status;
	private int quantity;
	private float totalMoney;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateOfSale() {
		return dateOfSale;
	}

	public void setDateOfSale(Date dateOfSale) {
		this.dateOfSale = dateOfSale;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(float totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Bill() {
		super();
	}

	public Bill(int id, Date dateOfSale, int idUser, int idProduct, String status, int quantity, float totalMoney) {
		super();
		this.id = id;
		this.dateOfSale = dateOfSale;
		this.idUser = idUser;
		this.idProduct = idProduct;
		this.status = status;
		this.quantity = quantity;
		this.totalMoney = totalMoney;
	}
	
}
