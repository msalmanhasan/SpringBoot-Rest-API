package com.salman.dao;

import com.salman.entity.Person;


import java.util.Collection;


public interface PersonDao {

    Collection<Person> getAllPersons();

    Person getPersonById(int id);

    Collection<Person> getPersonsByColor(String color);

    void insertPersonToDb(Person person);
}
