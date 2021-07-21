import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);
        HelloWorld bean =
                (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld bean2 =
                (HelloWorld) applicationContext.getBean("helloworld");
        System.out.println(bean.getMessage());
        System.out.println("first: " + (bean == bean2));
        System.out.println("==========================================");

        Cat newBean = (Cat) applicationContext.getBean("cat");
        Cat newBean2 = (Cat) applicationContext.getBean("cat");
        System.out.println(newBean.getMessage());
        System.out.println("second: " + (newBean == newBean2));
    }
}