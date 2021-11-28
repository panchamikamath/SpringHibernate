package kamath.panchami.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Course;
import kamath.panchami.hibernate.demo.entity.Instructor;
import kamath.panchami.hibernate.demo.entity.InstructorDetail;

public class GetInstructorCourses {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();					
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//get instructor from db
			Instructor tempInstructor = session.get(Instructor.class, 1);
			
			// get course for instructor
			System.out.println("Instructor: " + tempInstructor);
			System.out.println("Instructor Courses: " + tempInstructor.getCourse());
			
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
