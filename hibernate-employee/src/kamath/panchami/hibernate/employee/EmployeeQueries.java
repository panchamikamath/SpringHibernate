package kamath.panchami.hibernate.employee;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import kamath.panchami.hibernate.entity.Employee;

public class EmployeeQueries {

	public static void main(String[] args) {
		
		//create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Employee.class)
				.buildSessionFactory();
		
		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//begin session
			session.beginTransaction();
			
			//code to add objects
			Employee employee1 = new Employee("Panchami","Kamath","Unisys");
			Employee employee2 = new Employee("Sharan","Megha","Dell");
			Employee employee3 = new Employee("Sahana","M","Zebra");
			
			session.save(employee1);
			session.save(employee2);
			session.save(employee3);
			
			System.out.println("Saved employees....");
			
			System.out.println("Fetching employees...");
			
			List<Employee> theEmployees = session.createQuery("from Employee").getResultList();
			
			displayEmployees(session, theEmployees);
			
			theEmployees = session.createQuery("from Employee s where s.firstName='Panchami'").getResultList();
			
			displayEmployees(session, theEmployees);
			
			Employee theEmployee = session.get(Employee.class, 3);
			session.delete(theEmployee);
			
			//commit transaction
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
		
		

	}

	private static void displayEmployees(Session session, List<Employee> theEmployees) {
		for(Employee tempEmployee: theEmployees) {
			System.out.println("Employee with ID : " + tempEmployee.getId() + " is " + session.get(Employee.class, tempEmployee.getId()));
		}
	}

}
