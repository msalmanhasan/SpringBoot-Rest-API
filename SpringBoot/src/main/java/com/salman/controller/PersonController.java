package com.salman.controller;

import com.salman.entity.Person;
import com.salman.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.Collection;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Person> getAllPersons(){
        return personService.getAllPersons();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Person getPersonById(@PathVariable("id") int id){
        return personService.getPersonById(id);
    }

    @RequestMapping(value = "/color/{color}", method = RequestMethod.GET)
    public Collection<Person> getPersonsByColor(@PathVariable("color") String color){

        return personService.getPersonsByColor(color);
    }

    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insertPersonToDb(@RequestBody Person person){

        personService.insertPersonToDb(person);
    }
}
