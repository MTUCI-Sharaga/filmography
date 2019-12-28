package testgroup.filmography.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import testgroup.filmography.model.Film;
import testgroup.filmography.service.FilmService;

import java.util.List;

@Controller
public class FilmController {

    private int page;

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }


    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView allFilms(@RequestParam(defaultValue = "1") int page) {
        this.page = page;
        List<Film> films = filmService.allFilms(page);
        int filmsCount = filmService.filmsCount();
        int pagesCount = (filmsCount + 9)/10;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("films");
        modelAndView.addObject("page", page);
        modelAndView.addObject("filmsList", films);
        modelAndView.addObject("filmsCount", filmsCount);
        modelAndView.addObject("pagesCount", pagesCount);
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        Film film = filmService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("film", film);
        System.out.println(modelAndView.toString());
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        filmService.edit(film);
        System.out.println(modelAndView.toString());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView addFilm(@ModelAttribute("film") Film film) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        filmService.add(film);
        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteFilm(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/?page=" + this.page);
        Film film = filmService.getById(id);
        filmService.delete(film);
        return modelAndView;
    }

//    @RequestMapping(method = RequestMethod.POST)
//    public ModelAndView sortFilmsPost() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/?page=" + this.page);
//        System.out.printf("Can you hear me?");
//        return modelAndView;
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public ModelAndView sortFilmsGet() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/?page=" + this.page);
//        System.out.println("Heeeey!");
//        return modelAndView;
//    }
}
