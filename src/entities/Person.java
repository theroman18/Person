package entities;

import java.io.Serializable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    , @NamedQuery(name = "Person.findByAdult", query = "SELECT p FROM Person p WHERE p.adult = :adult")})
public class Person implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public Person() {
    }

    public Person(Integer id) {
        setId(id);
    }

    public Person(Integer id, boolean adult) {
        setId(id);
        setAdult(adult);
    }
    
    private IntegerProperty id ;
    private Integer _id;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    public int getId() {
        if (id == null) {
            return _id;
        } else {
            return id.get();
        }
    }

    public void setId(int id) {
        if (this.id == null) {
            _id = id;
        } else {
            this.id.set(id);
        }
    }
    
    public IntegerProperty idProperty() {
        if (id==null) {
            id = new SimpleIntegerProperty(this, "id", _id);
        }
        return id ;
    }
    
    private StringProperty firstName;
    private String _firstName;
    @Column(name = "First_Name")
    public String getFirstName() {
        if (firstName == null) {
            return _firstName;
        } else {
            return firstName.get();
        }
    }

    public void setFirstName(String firstName) {
        if (this.firstName == null) {
            _firstName = firstName;
        } else {
            this.firstName.set(firstName);
        }
    }
    
    public StringProperty firstNameProperty() {
        if (firstName == null) {
            firstName = new SimpleStringProperty(this, "firstName");
        }
        return firstName;
    }    
    
    private StringProperty lastName;
    private String _lastName;
    @Column(name = "Last_Name")
    public String getLastName() {
        if (lastName == null) {
            return _lastName;
        } else {
            return lastName.get();
        }
    }

    public void setLastName(String lastName) {
        if (this.lastName == null) {
            _lastName = lastName;
        } else {
            this.lastName.set(lastName);
        }
    }
    
    public StringProperty lastNameProperty() {
        if (lastName == null) {
            lastName = new SimpleStringProperty(this, "firstName");
        }
        return lastName;
    }       
    
    private IntegerProperty age;
    private Integer _age;
    @Column(name = "Age")
    public Integer getAge() {
        if (age == null) {
            return _age;
        } else {
            return age.get();
        }
    }

    public void setAge(Integer age) {
        if (this.age == null) {
            _age = age;
        } else {
            this.age.set(age);
        }
    }
    
    public IntegerProperty ageProperty() {
        if (age == null) {
            age = new SimpleIntegerProperty(this, "age", _age);
        }
        return age;
    }    
    
    private BooleanProperty adult;
    private boolean _adult;
    @Basic(optional = false)
    @Column(name = "Adult")
    public boolean getAdult() {
        if (adult == null) {
            return _adult;
        } else {
            return adult.get();
        }
    }
    
    public void setAdult(boolean adult) {
        if (this.adult == null) {
            _adult = adult;
        } else {
            this.adult.set(adult);
        }
    }
    
    private BooleanProperty adultProperty() {
        if (adult == null) {
            adult = new SimpleBooleanProperty(this, "adult", false);
        }
        return adult;
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
    
}
