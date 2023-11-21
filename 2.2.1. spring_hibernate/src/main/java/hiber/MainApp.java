package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MainApp {
    private final UserService userService;

    @Autowired
    public MainApp(UserService userService) {
        this.userService = userService;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MainApp mainApp = context.getBean(MainApp.class);

        //mainApp.insertInDB();
        mainApp.showDataFromDB();
    }

    public void insertInDB() {

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car car1 = new Car("model1", 1);
        Car car2 = new Car("model2", 2);
        Car car3 = new Car("model3", 3);
        Car car4 = new Car("model4", 4);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        car4.setUser(user4);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

    }

    public void showDataFromDB() {
        userService.listUsers().forEach(System.out::println);

        System.out.println(userService.getUserByModelAndSeries("model1", 1));
        System.out.println(userService.getUserByModelAndSeries("model4", 4));
    }
}
