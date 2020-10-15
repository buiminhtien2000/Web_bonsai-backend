package Web_bonsai.api;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Web_bonsai.bean.Bill;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController(value = "BillAPI_Admin")
public class BillAPI {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "/api/manager_bill",method = RequestMethod.POST)
	public Bill createBill(@RequestBody Bill bill){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(bill);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
		return bill;
	}
	@RequestMapping(value = "/api/manager_bill",method = RequestMethod.PUT)
	public void updateBill(@RequestParam("id") int id , @RequestBody Bill bills){
		Session s = factory.getCurrentSession();
		Bill bill = (Bill) s.get(Bill.class,id);
		bill.setQuantity(bills.getQuantity());
		bill.setStatus(bills.getStatus());
		bill.setTotalMoney(bills.getTotalMoney());
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(bill);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
	}
	@RequestMapping(value = "/api/manager_bill",method = RequestMethod.DELETE)
	public void deleteBill(@RequestParam("id") int id){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Session s = factory.getCurrentSession();
			Bill bill = (Bill) s.get(Bill.class,id);
			session.delete(bill);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
	}
	@RequestMapping(value = "/api/selectAllBill",method = RequestMethod.GET)
	public ResponseEntity<List<Bill>> selectAll(){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM Bill"; 
		List<Bill> listBill= session.createQuery(hql).list();
		t.commit();
		if(listBill.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Bill>>(listBill, HttpStatus.OK);
	}
	@RequestMapping(value = "/api/selectBillById", method = RequestMethod.GET)
	public ResponseEntity<List<Bill>> findByIdUser(@RequestParam("idUser") String idUser) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM Bill WHERE idUser LIKE :idUser";
		Query query = session.createQuery(hql);
		query.setParameter("idUser", idUser);
		List<Bill> listBillByIdUser = query.list();
		if(listBillByIdUser == null) {
			ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Bill>>(listBillByIdUser, HttpStatus.OK);
	}
}
