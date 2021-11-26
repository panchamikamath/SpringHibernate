package kamath.panchami.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Student;

public class DeleteStudent {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();					
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			int studentId = 1;
			Student myStudent = session.get(Student.class, studentId);
			
			//delete student
			
			System.out.println("Deleteing student with Id : " + myStudent.getId());
			session.delete(myStudent);
			
			System.out.println("Deleteing student with Id 2 ");
			session.createQuery("delete from Student where id=2").executeUpdate();
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
