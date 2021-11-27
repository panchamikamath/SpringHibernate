package kamath.panchami.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Student;

public class CreateStudent {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();					
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			//use session object to save Java object
			
			System.out.println("Creating new student object...");
			//create student object
			Date theDateOfBirth = DateUtils.parseDate("32/2/1987");
			Student tempStudent = new Student("Ross","Geller","Geller.ross@friends.com",theDateOfBirth);
			
			//start a transaction
			session.beginTransaction();
			
			//save student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
