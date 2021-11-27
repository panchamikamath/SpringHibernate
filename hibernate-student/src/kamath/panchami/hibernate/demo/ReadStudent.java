package kamath.panchami.hibernate.demo;

import java.text.ParseException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Student;

public class ReadStudent {

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
			Date theDateOfBirth = DateUtils.parseDate("21/2/1987");
			Student tempStudent = new Student("Arun","kamath","arun.kamath@gmail.com",theDateOfBirth);
			
			//start a transaction
			session.beginTransaction();
			
			//save student object
			System.out.println("Saving the student...");
			session.save(tempStudent);
			
			//commit transaction
			session.getTransaction().commit();
			
			//read student data
			System.out.println("Student saved.... Generated Id : " + tempStudent.getId());
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			System.out.println("Getting student with Id : " + tempStudent.getId());
			
			Student theStudent = session.get(Student.class, tempStudent.getId());
			
			System.out.println("Get complete : " + theStudent);
			
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
