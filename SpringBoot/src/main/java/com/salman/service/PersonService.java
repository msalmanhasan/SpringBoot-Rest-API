package com.salman.service;

import com.salman.dao.PersonDao;
import com.salman.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    @Qualifier("data")
    private PersonDao personDao;

    public Collection<Person> getAllPersons(){
        return this.personDao.getAllPersons();
    }

    public Person getPersonById(int id){

        return this.personDao.getPersonById(id);
    }

    public Collection<Person> getPersonsByColor(String color){

        return this.personDao.getPersonsByColor(color);
    }

    public void insertPersonToDb(Person person){

        this.personDao.insertPersonToDb(person);
    }
}
