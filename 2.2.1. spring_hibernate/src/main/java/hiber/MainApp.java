package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;


public class MainApp {
    public static void main(String[] args) throws SQLException {
        //insertInDB();
        showDataFromDB();
    }

    public static void insertInDB() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        Car car1 = new Car("model1", 1);
        Car car2 = new Car("model2", 2);
        Car car3 = new Car("model3", 3);
        Car car4 = new Car("model4", 4);

        carService.add(car1);
        carService.add(car2);
        carService.add(car3);
        carService.add(car4);

        car1.setUser(user1);
        car2.setUser(user2);
        car3.setUser(user3);
        car4.setUser(user4);

        carService.update(car1);
        carService.update(car2);
        carService.update(car3);
        carService.update(car4);

        context.close();
    }

    public static void showDataFromDB() {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        List<Car> cars = carService.listCars();
        for (Car car : cars) {
            System.out.println("Id = " + car.getId());
            System.out.println("Model = " + car.getModel());
            System.out.println("Series = " + car.getSeries());
            System.out.println("User name = " + car.getUser().getFirstName());
            System.out.println();
        }

        System.out.println(carService.getUserByModelAndSeries("model1", 1));
        System.out.println(carService.getUserByModelAndSeries("model4", 4));

        context.close();
    }
}
