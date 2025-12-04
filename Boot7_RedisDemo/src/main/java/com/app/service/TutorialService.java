package com.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import com.app.models.Tutorial;
import com.app.repo.TutorialRepo;

@Service
@EnableCaching
public class TutorialService {

    @Autowired
    TutorialRepo tr;


    @Cacheable(value = "tutorials")
    public List<Tutorial> findAll() {
        List<Tutorial> list = new ArrayList<>();
        Iterable<Tutorial> tt = tr.findAll();
        for (Tutorial t : tt) list.add(t);
        return list;
    }

 
    @Cacheable(value = "tutorials", key = "#title")
    public List<Tutorial> findByTitle(String title) {
        return tr.findByTitle(title);
    }

    @Cacheable(value = "tutorial", key = "#id")
    public Tutorial findById(int id) {
        return tr.findById(id).orElse(null);
    }

 
    @CachePut(value = "tutorial", key = "#result.id")
    @CacheEvict(value = "tutorials", allEntries = true)
    public Tutorial save(Tutorial t) {
        return tr.save(t);
    }

  
    @CachePut(value = "tutorial", key = "#id")
    @CacheEvict(value = "tutorials", allEntries = true)
    public String update(int id, Tutorial t) {
        Optional<Tutorial> tut = tr.findById(id);
        if (tut.isPresent()) {
            Tutorial tt = tut.get();
            tt.setDescription(t.getDescription());
            tt.setId(id);
            tt.setPublished(t.isPublished());
            tt.setTitle(t.getTitle());
            tr.save(tt);
            return "Updated";
        }
        return "No tutorial found with that id";
    }

   
    @CacheEvict(value = "tutorial", key = "#id")
 
    public String deleteById(int id) {
        tr.deleteById(id);
        return "Deleted";
    }

   
    @CacheEvict(value = { "tutorial", "tutorials", "published_tutorials" }, allEntries = true)
    public String deleteAll() {
        tr.deleteAll();
        return "All tutorials deleted";
    }

   
    @Cacheable(value = "published_tutorials", key = "#isPub")
    public List<Tutorial> findByPublished(boolean isPub) {
        return tr.findBypublished(isPub);
    }
}
