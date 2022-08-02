/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Roman-Desktop
 */
@Entity
@Access(AccessType.PROPERTY)
@Table(name = "Person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findById", query = "SELECT p FROM Person p WHERE p.id = :id")
    , @NamedQuery(name = "Person.findByFirstName", query = "SELECT p FROM Person p WHERE p.firstName = :firstName")
    , @NamedQuery(name = "Person.findByLastName", query = "SELECT p FROM Person p WHERE p.lastName = :lastName")
    , @NamedQuery(name = "Person.findByAge", query = "SELECT p FROM Person p WHERE p.age = :age")
    , @NamedQuery(name = "Person.findByEmployed", query = "SELECT p FROM Person p WHERE p.employed = :employed")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String firstName;
    private String lastName;
    private Integer age;
    private boolean employed;
    private final PropertyChangeSupport propertySupport;
    
    public Person() {
       this.propertySupport = new PropertyChangeSupport(this);
    }

    public Person(Integer id) {
        this.id = id;
        this.propertySupport = new PropertyChangeSupport(this);
    }

    public Person(Integer id, boolean employed) {
        this.id = id;
        this.employed = employed;
        this.propertySupport = new PropertyChangeSupport(this);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        propertySupport.firePropertyChange("id", oldId, this.id);
    }

    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        propertySupport.firePropertyChange("firstName", oldFirstName, this.firstName);
    }

    @Column(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        propertySupport.firePropertyChange("lastName", oldLastName, this.lastName);
    }

    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        Integer oldAge = this.age;
        this.age = age;
        propertySupport.firePropertyChange("age", oldAge, this.age);
    }

    @Basic(optional = false)
    @Column(name = "employed")
    public boolean getEmployed() {
        return employed;
    }

    public void setEmployed(boolean employed) {
        boolean oldEmployed = this.employed;
        this.employed = employed;
        propertySupport.firePropertyChange("employed", oldEmployed, this.employed);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Person[ id=" + id + " ]";
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }
    
}
