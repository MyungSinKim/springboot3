package soundsystem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringExam06 {
    public static void main(String[] args) throws Exception{
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(StudentConfig.class);
        Control control = applicationContext.getBean(Control.class);
        control.control();
    }
}
