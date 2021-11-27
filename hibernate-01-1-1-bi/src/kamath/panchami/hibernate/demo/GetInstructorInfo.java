package kamath.panchami.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Instructor;
import kamath.panchami.hibernate.demo.entity.InstructorDetail;

public class GetInstructorInfo {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();					
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get instructor detail object
			InstructorDetail tempInstructorDetail = session.get(InstructorDetail.class, 2);
			
			//print instructor detail
			System.out.println("tempInstructorDetail : " + tempInstructorDetail );
			
			//print associated instructor
			System.out.println("Associated Instructor : " + tempInstructorDetail.getInstructor() );
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			//handle connection leak issue
			session.close();
			factory.close();
		}
	}

}
