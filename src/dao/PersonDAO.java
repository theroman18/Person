/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import JpaController.PersonJpaController;
import JpaController.exceptions.NonexistentEntityException;

/**
 *
 * @author Roman-Desktop
 */
public class PersonDAO {
    private final PersonJpaController personController;
    private final EntityManagerFactory emf;
    
    public PersonDAO() {
        emf = Persistence.createEntityManagerFactory("PersonEntityPU");
        personController = new PersonJpaController(emf);
    }
    
    public void addPerson(Person person) throws Exception{
        personController.create(person);
    }
    
    public void editPerson(Person person) throws Exception{
        personController.edit(person);
    }
    
    public void removePerson(int personID) throws NonexistentEntityException{
        personController.destroy(personID);
    }
    
    public List<Person> getAllPersons() {
        return personController.findPersonEntities();
    }
    
    public Person getPersonById(int personID) {
        return personController.findPerson(personID);
    }
}
