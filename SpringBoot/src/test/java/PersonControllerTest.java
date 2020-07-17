import com.salman.controller.PersonController;
import com.salman.entity.Person;
import com.salman.service.PersonService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PersonControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PersonService personService;

    @InjectMocks
    private PersonController personController;

    @Before
    public void setUp() throws Exception{

        mockMvc = MockMvcBuilders.standaloneSetup(personController)
                .build();
    }

    @Test
    public void testGetAllPersons() throws Exception{
        List<Person> persons = Arrays.asList(new Person(1, "Hans","Müller",  "67742", "Lauterecken", "blue"),
                new Person(2, "Peter","Petersen",  "18439", "Stralsund", "green"));

        when(personService.getAllPersons()).thenReturn(persons);

        mockMvc.perform(get("/persons")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void testGetPersonById()throws Exception{

        Person person = new Person(1, "Hans","Müller",  "67742", "Lauterecken", "blue");

        when(personService.getPersonById(1)).thenReturn(person);

        mockMvc.perform(get("/persons/{id}",1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*",Matchers.hasSize(6)));
    }

    @Test
    public void testGetPersonsByColor()throws Exception{

        List<Person> persons = Arrays.asList(new Person(1, "Hans","Müller",  "67742", "Lauterecken", "blue"));

        when(personService.getPersonsByColor("blue")).thenReturn(persons);

        mockMvc.perform(get("/persons/color/{color}","blue")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",Matchers.hasSize(1)));

    }


    @Test
    public void testInsertPersonToDb() throws Exception{
        String json = "{\n" +
                "  \"id\": 15,\n" +
                "  \"name\": \"James\",\n" +
                "  \"lastname\": \"Bond\",\n" +
                "  \"zipcode\": \"12345\",\n" +
                "  \"city\": \"Los Angeles\",\n" +
                "  \"color\": \"yellow\"\n" +
                "}";


        mockMvc.perform(post("/persons")
        .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

    }

}