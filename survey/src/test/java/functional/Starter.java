package functional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Starter {
      
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-config.xml");
        
        TestSpringHibernateJpa test = (TestSpringHibernateJpa) context
                .getBean("testSpringHibernateJpa");
        
        test.test(context);
        
        ((ConfigurableApplicationContext)context).close();
    }
}