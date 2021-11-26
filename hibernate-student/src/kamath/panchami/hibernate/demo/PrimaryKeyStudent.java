package kamath.panchami.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.demo.entity.Student;

public class PrimaryKeyStudent {

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
					
					System.out.println("Creating new student objects...");
					//create student object
					Student tempStudent1 = new Student("Panchami","kamath","panchukamath@gmail.com");
					Student tempStudent2 = new Student("Gowthami","kamath","gowthu.kamath@gmail.com");
					Student tempStudent3 = new Student("Pavan","kamath","pavankamath@gmail.com");
					
					//start a transaction
					session.beginTransaction();
					
					//save student object
					System.out.println("Saving the student...");
					session.save(tempStudent1);
					session.save(tempStudent2);
					session.save(tempStudent3);
					
					//commit transaction
					session.getTransaction().commit();
					
					System.out.println("Done!");
				}
				finally {
					factory.close();
				}

	}

}
