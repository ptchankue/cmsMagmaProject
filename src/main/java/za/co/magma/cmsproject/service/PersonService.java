package za.co.magma.cmsproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.magma.cmsproject.domain.Person;
import za.co.magma.cmsproject.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

//    public List<Person> findAll() {
//
//        var it = personRepository.findAll();
//
//        var people = new ArrayList<Person>();
//        it.forEach(e -> people.add(e));
//
//        return people;
//    }
//
//    public Long count() {
//
//        return personRepository.count();
//    }
//
//    public void deleteById(Long PersonId) {

//        personRepository.deleteById(PersonId);
//    }
}