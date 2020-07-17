package com.salman.dao;

import com.salman.entity.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
@Qualifier("data")
public class PersonDaoImpl implements PersonDao{

    private static Map<Integer, Person> persons;
    private static Map<String, String> colors;

    static {

        colors = new HashMap<String, String>();

        colors.put("1","blue");
        colors.put("2","green");
        colors.put("3","purple");
        colors.put("4","red");
        colors.put("5","yellow");
        colors.put("6","turquois");
        colors.put("7","white");
        colors.put("blue","1");
        colors.put("green","2");
        colors.put("purple","3");
        colors.put("red","4");
        colors.put("yellow","5");
        colors.put("turquois","6");
        colors.put("white","7");

        persons = new HashMap<Integer, Person>();


        String row;
        BufferedReader csvReader = null;

        try {
            csvReader = new BufferedReader(new FileReader("src/main/resources/Persons.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        try {

            for (int i=1;(row = csvReader.readLine()) != null;i++) {
                String[] data = row.split(",");
                // do something with the data

                int id = i;
                String lastname = data[0];
                String name = data[1].substring(1,data[1].length());
                String zipcode = data[2].substring(1,6);
                String city = data[2].substring(7,data[2].length());
                String color = colors.get(data[3].substring(1,2));

                Person person = new Person(id,name,lastname,zipcode,city,color);

                persons.put(i,person);


            }
            csvReader.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Collection<Person> getAllPersons() {
        return this.persons.values();
    }

    @Override
    public Person getPersonById(int id) {
        return this.persons.get(id);
    }

    @Override
    public Collection<Person> getPersonsByColor(String color) {
        Collection<Person> toReturn = new ArrayList<Person>();
        for(Map.Entry<Integer,Person> entry: persons.entrySet()){
            if (entry.getValue().getColor().equals(color)){
                toReturn.add(entry.getValue());
            }
        }

        return toReturn;
    }

    @Override
    public void insertPersonToDb(Person person) {

        persons.put(persons.size()+1,person);

        Writer output;
        try {
            output = new BufferedWriter(new FileWriter("src/main/resources/Persons.csv", true));
            output.append(person.getLastname()+", " + person.getName()+", " + person.getZipcode() + " "+person.getCity()+", "+colors.get(person.getColor()) + "\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
