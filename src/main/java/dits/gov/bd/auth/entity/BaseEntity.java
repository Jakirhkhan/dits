package dits.gov.bd.auth.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON")
    private Date createdOn;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_ON")
    private Date updatedOn;

    @JsonIgnore
    @Column(name = "CREATED_BY")
    private String createdBy;

    @JsonIgnore
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    public BaseEntity(Date createdOn, Date updatedOn) {
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public BaseEntity(Date createdOn, Date updatedOn, String createdBy, String updatedBy) {
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public BaseEntity(int id, Date createdOn, Date updatedOn, String createdBy, String updatedBy) {
        this.id = id;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
