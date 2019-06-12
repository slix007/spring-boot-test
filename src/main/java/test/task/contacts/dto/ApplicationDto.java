package test.task.contacts.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

    @JsonProperty("CONTACT_ID")
    private Long contactId;

    @JsonProperty("APPLICATION_ID")
    private Long applicationId;

    @JsonFormat(pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @JsonProperty("DT_CREATED")
    private Date dtCreated;

    @JsonProperty("PRODUCT_NAME")
    private String productName;

}
