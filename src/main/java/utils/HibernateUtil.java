package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.vti.entity.Account;
import com.vti.entity.AccountLogin;
import com.vti.entity.Group;
import com.vti.entity.GroupAccount;
import com.vti.entity.Position;
import com.vti.entity.TypeQuestion;
import com.vti.entity.CategoryQuestion;
import com.vti.entity.Department;

public class HibernateUtil {
	private final static SessionFactory FACTORY;

	static {
		Configuration cfg = new Configuration();
		cfg.configure("HibernateConfig.xml");

		cfg.addAnnotatedClass(Group.class);
		cfg.addAnnotatedClass(Position.class);
		cfg.addAnnotatedClass(Account.class);
		cfg.addAnnotatedClass(CategoryQuestion.class);
		cfg.addAnnotatedClass(TypeQuestion.class);
		cfg.addAnnotatedClass(GroupAccount.class);
		cfg.addAnnotatedClass(AccountLogin.class);
		cfg.addAnnotatedClass(Department.class);
		

		ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties()).build();

		FACTORY = cfg.buildSessionFactory(registry);
	}

	public static SessionFactory getFactory() {
		return FACTORY;
	}
}
