package dits.gov.bd.tax.entity;


import dits.gov.bd.auth.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Tax")
public class Tax extends BaseEntity {
    @Column(name = "tin",length = 12)
    @NotNull
    private String tin;
    @NotNull
    @Column(name = "tax_year", length = 9)
    private String incomeYear;
    @NotNull
    private double salary;
    private double totalTax;
}
