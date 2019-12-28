package testgroup.filmography.service;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import testgroup.filmography.config.HibernateSessionFactoryUtil;
import testgroup.filmography.dao.FilmDAO;
import testgroup.filmography.dao.FilmDAOImpl;
import testgroup.filmography.model.Film;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class FilmServiceImpl implements FilmService {
    private FilmDAO filmDAO = new FilmDAOImpl();

    @Override
    @Transactional
    public List<Film> allFilms(int page) {
        return filmDAO.allFilms(page);
    }

    @Override
    @Transactional
    public void add(Film film) {
        filmDAO.add(film);
    }

    @Override
    @Transactional
    public void delete(Film film) {
        filmDAO.delete(film);
    }

    @Override
    @Transactional
    public void edit(Film film) {
        filmDAO.edit(film);
    }

    @Override
    @Transactional
    public Film getById(int id) {
        return filmDAO.getById(id);
    }

    public int filmsCount() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        return session.createQuery("select count(*) from Film", Number.class).getSingleResult().intValue();
    }
}
