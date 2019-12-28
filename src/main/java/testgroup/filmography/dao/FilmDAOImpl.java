package testgroup.filmography.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import testgroup.filmography.config.HibernateSessionFactoryUtil;
import testgroup.filmography.model.Film;

import java.util.List;

@Repository
public class FilmDAOImpl implements FilmDAO {

    @Override
    @SuppressWarnings("unchecked")
    public List<Film> allFilms(int page) {
        List<Film> films = (List<Film>)  HibernateSessionFactoryUtil.getSessionFactory().openSession()
                .createQuery("From Film f order by f.title asc").setFirstResult(10 * (page - 1)).setMaxResults(10).list();
        return films;
    }

    @Override
    public void add(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();

        try {
            System.out.println("Добавление записи в таблицу БД");
            System.out.println(film.toString());
            Transaction tx = session.beginTransaction();
            session.save(film);
            tx.commit();
            System.out.println("\tЗаписи добавлены");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(film);
        tx1.commit();
        session.close();
    }

    @Override
    public void edit(Film film) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(film);
        tx1.commit();
        session.close();
    }

    @Override
    public Film getById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Film.class, id);
    }
}