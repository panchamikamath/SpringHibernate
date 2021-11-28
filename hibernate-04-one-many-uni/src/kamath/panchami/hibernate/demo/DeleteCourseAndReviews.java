package kamath.panchami.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Course;
import kamath.panchami.hibernate.demo.entity.Instructor;
import kamath.panchami.hibernate.demo.entity.InstructorDetail;
import kamath.panchami.hibernate.demo.entity.Review;

public class DeleteCourseAndReviews {

	public static void main(String[] args) {
		
		//create session factory
				SessionFactory factory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Instructor.class)
						.addAnnotatedClass(InstructorDetail.class)
						.addAnnotatedClass(Course.class)
						.addAnnotatedClass(Review.class)
						.buildSessionFactory();					
				
				//create session
				Session session = factory.getCurrentSession();
				
				try {
					
					//start a transaction
					session.beginTransaction();
					
					//get a course
					Course tempCourse = session.get(Course.class, 13);
					
					//delete course
					session.delete(tempCourse);
					
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
