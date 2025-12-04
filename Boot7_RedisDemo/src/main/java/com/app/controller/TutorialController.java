package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.app.models.Tutorial;
import com.app.service.TutorialService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialService tser;

 
    @GetMapping("/tutorials")
    @ResponseStatus(HttpStatus.OK)
    public List<Tutorial> getAllTutorials(@RequestParam(required = false) String title) {
        if (title == null) {
            return tser.findAll();
        }
        return tser.findByTitle(title);
    }


    @GetMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tutorial getTutById(@PathVariable int id) {
        return tser.findById(id);
    }

  
    @PostMapping("/tutorials")
    @ResponseStatus(HttpStatus.CREATED)
    public Tutorial createTutorial(@RequestBody Tutorial tutorial) {
        return tser.save(tutorial);
    }

    
    @PutMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateTutorial(@PathVariable("id") int id, @RequestBody Tutorial tutorial) {
        return tser.update(id, tutorial);
    }

 
    @DeleteMapping("/tutorials/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteTutorial(@PathVariable("id") int id) {
        return tser.deleteById(id);
    }

   
    @DeleteMapping("/tutorials")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String deleteAllTutorials() {
        return tser.deleteAll();
    }

    @GetMapping("/tutorials/published")
    @ResponseStatus(HttpStatus.OK)
    public List<Tutorial> findByPublished() {
        return tser.findByPublished(true);
    }
}
