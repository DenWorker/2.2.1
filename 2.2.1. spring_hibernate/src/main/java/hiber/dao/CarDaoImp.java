package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().persist(car);
    }

    @Override
    public void update(Car car) {
        sessionFactory.getCurrentSession().merge(car);
    }

    @Override
    public User getUserByModelAndSeries(String model, int series) {
        return sessionFactory.getCurrentSession()
                .createQuery("select c.user from Car c " +
                        "where c.model = :model and  c.series = :series", User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getSingleResult();
    }

    @Override
    public List<Car> listCars() {
        return sessionFactory.getCurrentSession().createQuery("from Car", Car.class).getResultList();
    }

}
