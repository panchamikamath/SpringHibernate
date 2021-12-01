package kamath.panchami.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Course;
import kamath.panchami.hibernate.demo.entity.Instructor;
import kamath.panchami.hibernate.demo.entity.InstructorDetail;
import kamath.panchami.hibernate.demo.entity.Review;
import kamath.panchami.hibernate.demo.entity.Student;

public class CreateCourseAndStudent {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Student.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();					
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			session.beginTransaction();
			//use session object to save Java object
			
			System.out.println("Creating new object...");
			
			//save course
			Course tempCourse = new Course("Elasticsearch fundamentals");
			
			session.save(tempCourse);
			System.out.println("saved courses: " + tempCourse);
			
			//save student
			Student tempStudent1 = new Student("Panchami","Kamath","pk@gmail.com");
			Student tempStudent2 = new Student("Gowthami","Kamath","gk@gmail.com");
			
			tempCourse.addStudent(tempStudent1);
			tempCourse.addStudent(tempStudent2);
			
			session.save(tempStudent1);
			session.save(tempStudent2);
			
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
