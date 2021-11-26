package kamath.panchami.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Student;

public class QueryStudent {

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
			
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			
			displayStudents(theStudents);
			
			//query students : firstName='Pavan'
			System.out.println("\n\nStudents having first name as 'Pavan'....");
			theStudents = session.createQuery("from Student s where s.firstName='Pavan'").getResultList();
			displayStudents(theStudents);
			
			//query student with firstName='Pavan' or email='panchukamth@gmail.com'
			System.out.println("\n\nStudents having first name as 'Pavan' or email='panchukamth@gmail.com'"); 
			theStudents = session.createQuery("from Student s where s.firstName='Pavan' OR s.email='panchukamath@gmail.com'").getResultList();
			displayStudents(theStudents);
			
			//query student where email LIKE '%@gmail.com'
			System.out.println("\n\nStudents having where email LIKE '%@gmail.com'"); 
			theStudents = session.createQuery("from Student s where s.email LIKE '%@gmail.com'").getResultList();
			displayStudents(theStudents);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudents(List<Student> theStudents) {
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}

}
