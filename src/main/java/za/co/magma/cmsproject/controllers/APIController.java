package za.co.magma.cmsproject.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.magma.cmsproject.domain.BlogPost;
import za.co.magma.cmsproject.domain.Person;
import za.co.magma.cmsproject.repository.BlogPostRepository;
import za.co.magma.cmsproject.repository.PersonRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlogPostRepository blogPostRepository;

    @RequestMapping("/hello")
    public ResponseEntity myHelloAPI() {
        Map<String, String> result =new HashMap<>();
        result.put("message", "Hello guys");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/person/all")
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @PostMapping("/person")
    public Person createAccount(@Valid @RequestBody Person person) {
        return personRepository.save(person);
    }

    /**
     *
     * @param blogPost
     * @return
     */
    @PostMapping("/blogpost")
    public BlogPost createBlogPost(@Valid @RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }


}
