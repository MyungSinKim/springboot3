package soundsystem;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringExam05 {
    public static void main(String[] args) throws Exception{
        ApplicationContext applicationContext
                = new AnnotationConfigApplicationContext(DiceConfig.class);
        Game game = applicationContext.getBean("game", Game.class);
        game.play();
    }
}
