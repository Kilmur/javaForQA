package jqa.maxim.starikov.mantis.appmanager;

import jqa.maxim.starikov.mantis.models.UserData;
import jqa.maxim.starikov.mantis.models.Users;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper(){
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
      .configure()
      .build();
    sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
  }

  public Users getUsers(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData" ).list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

  public UserData getUserById(int id){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery( "from UserData where id = '" + id + "'" ).list();
    session.getTransaction().commit();
    session.close();
    return result.iterator().next();
  }
}
