package kamath.panchami.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Instructor;
import kamath.panchami.hibernate.demo.entity.InstructorDetail;

public class CreateInfo {

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
			//use session object to save Java object
			
			System.out.println("Creating new object...");
			
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com/actor","acting");
			Instructor tempInstructor = new Instructor("Joey","Tribbiani","Joey.Tribbiani@friends.com");
			
			//associate objects
			tempInstructor.setInstructorDetails(tempInstructorDetail);
			
			//start a transaction
			session.beginTransaction();
			
			//save student object
			System.out.println("Saving the object...");
			session.save(tempInstructor);
			
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
