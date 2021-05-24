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
import java.util.Optional;

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

    @GetMapping("/person/{id}")
    public Person getPeopleById(@PathVariable Long id) {
        return personRepository.findById(id)
            .orElse(null);
    }

    /**
     * curl localhost:9082/api/person/email?email=ptchankue@gmail.com -v
     * @param email
     * @return
     */
    @GetMapping("/person/email")
    public ResponseEntity getPersonByEmailAddress(@RequestParam String email) {
        Person p = personRepository.findByEmailAddress(email);
        System.out.println(p);
        return (null!= p) ?
            ResponseEntity.status(HttpStatus.OK).body(p):
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(p);
    }

    @PostMapping("/person")
    public Person createAccount(@Valid @RequestBody Person person) {
        System.out.println(person);
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
