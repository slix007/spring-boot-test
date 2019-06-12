package test.task.contacts.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Contact {

    public Contact(String contactName) {
        this.contactName = contactName;
    }

    public Contact() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sContGen")
    @SequenceGenerator(name = "sContGen", sequenceName = "s_contact", initialValue = 5)
    private Long contactId;

    private String contactName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "contact")
    @JsonIgnoreProperties("contact")
    private List<Application> applications = new ArrayList<>();

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application application) {
        this.applications.add(application);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId) &&
                Objects.equals(contactName, contact.contactName) &&
                Objects.equals(applications, contact.applications);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, contactName, applications);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", contactName='" + contactName + '\'' +
                ", applications=" + applications +
                '}';
    }
}
