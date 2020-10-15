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

import Web_bonsai.bean.Comment;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Transactional
@RestController(value = "CommentAPI_Admin")
public class CommentAPI {
	@Autowired
	SessionFactory factory;
	@RequestMapping(value = "/api/manager_comment",method = RequestMethod.POST)
	public Comment createComment(@RequestBody Comment comment){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(comment);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
		
		return comment;
	}
	@RequestMapping(value = "/api/manager_comment",method = RequestMethod.PUT)
	public void updateComment(@RequestParam("id") int id , @RequestBody Comment comments){
		Session s = factory.getCurrentSession();
		Comment comment = (Comment) s.get(Comment.class,id);
		comment.setContent(comments.getContent());
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(comment);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
	}
	@RequestMapping(value = "/api/manager_comment",method = RequestMethod.DELETE)
	public void deleteComment(@RequestParam("id") int id){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			Session s = factory.getCurrentSession();
			Comment comment = (Comment) s.get(Comment.class,id);
			session.delete(comment);
			t.commit();
		} catch (Exception e) {
			t.rollback();
		}finally {
			session.close();
		}
	}
	@RequestMapping(value = "/api/selectAllComment",method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> selectAll(){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM Comment"; 
		List<Comment> listComment= session.createQuery(hql).list();
		t.commit();
		if(listComment.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Comment>>(listComment, HttpStatus.OK);
	}
	@RequestMapping(value = "/api/selectCommentByName", method = RequestMethod.GET)
	public ResponseEntity<List<Comment>> findByCommentName(@RequestParam("userName") String userName) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		String hql = "FROM  Comment WHERE userName LIKE :userName";
		Query query = session.createQuery(hql);
		query.setParameter("userName", userName);
		List<Comment> listCommentByUserName = query.list();
		if(listCommentByUserName == null) {
			ResponseEntity.notFound().build();
		}
		return new ResponseEntity<List<Comment>>(listCommentByUserName, HttpStatus.OK);
	}
}
