package com.example.restApiTutorial.api;

import com.example.restApiTutorial.model.Person;
import com.example.restApiTutorial.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    //zwrócic ewentualnie kod błędu, albo jakiś exception
    @PostMapping // oznacza, że wysylane bedzie żadanie POST - dodania czegos, żadanie GET - wziecie czegos(get) żadanie PUT - aktualizacja, DELETE-usuniecie
    public void addPerson(@RequestBody Person person){ //RequestBody - wskazuje na to, że obiekt klasy person ma zostać stworzony na podstawie requestBody ( w tym przypadku wystarczy nam samo imie),
                                                        //następnie w konstruktorze Person mamy jsonProperty, które wskazuje na konkretne atrybuty, które mają zostać przypisane
                                                        //do konkretnych wartosci konstruktora
        personService.addPerson(person);
    }

    @GetMapping
    public List<Person> getAllPeople(){
        return personService.getAllPeople();
    }

    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){ // Path variable wskazuje na to, że endpointem zapytania get w tym przypadku
                                                                        // jest api/v1/person/id
        return personService.getPersonById(id).
                orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deletePerson(@PathVariable("id") UUID id){
        personService.deletePerson(id);
    }

    @PutMapping(path = "{id}")
    public void updatePerson(@PathVariable UUID id, @RequestBody Person personToUpdate){
        personService.updatePerson(id, personToUpdate);
    }
}
