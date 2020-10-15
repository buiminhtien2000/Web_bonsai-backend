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

import Web_bonsai.bean.Users;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController(value = "UserAPI_Admin")
public class UserAPI {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "/api/manager_user",method = RequestMethod.POST)
	public Users createUser(@RequestBody Users user){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
		
		return user;
	}
	@RequestMapping(value = "/api/manager_user",method = RequestMethod.PUT)
	public void updateUser(@RequestParam("id") int id, 
            @RequestBody Users users){
		Session s = factory.getCurrentSession();
		Users user = (Users) s.get(Users.class,id);
		user.setName(users.getName());
		user.setPassword(users.getPassword());
		user.setPhoneOrEmail(users.getPhoneOrEmail());
		user.setPicture(users.getPicture());
		user.setBirthDay(users.getBirthDay());
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
	}
	@RequestMapping(value = "/api/manager_user",method = RequestMethod.DELETE)
	public void deleteUser(@RequestParam("id") int id){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Session s = factory.getCurrentSession();
			Users user = (Users) s.get(Users.class, id);
			session.delete(user);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
	}
	@RequestMapping(value = "/api/selectAllUser",method = RequestMethod.GET)
	public ResponseEntity<List<Users>> selectAll(){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM Users"; 
		List<Users> listUser= session.createQuery(hql).list();
		t.commit();
		if(listUser.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Users>>(listUser, HttpStatus.OK);
	}
	@RequestMapping(value = "/api/selectUserByName", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> findByName(@RequestParam("name") String name) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM Users WHERE name LIKE :name";
		Query query = session.createQuery(hql);
		query.setParameter("name", name);
		List<Users> listUserByName = query.list();
		if(listUserByName == null) {
			ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Users>>(listUserByName, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/api/selectUserByID", method = RequestMethod.GET)
	public ResponseEntity<List<Users>> findById(@RequestParam("id") int id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM Users WHERE id LIKE :id";
		Query query = session.createQuery(hql);
		query.setParameter("id", id);
		List<Users> listUserById = query.list();
		if(listUserById == null) {
			ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Users>>(listUserById, HttpStatus.OK);
	}
}
