package test.task.contacts.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sAppGen")
    @SequenceGenerator(name = "sAppGen", sequenceName = "s_application", initialValue = 5)
    private Long applicationId;

    private Date dtCreated;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(Date dtCreated) {
        this.dtCreated = dtCreated;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    @Override
    public String toString() {
        return "Application{" +
                "applicationId=" + applicationId +
                ", dtCreated=" + dtCreated +
                ", productName='" + productName + '\'' +
                '}';
    }
}
