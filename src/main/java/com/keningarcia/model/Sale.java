package com.keningarcia.model;

import com.keningarcia.dto.ProcedureDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

import java.time.LocalDateTime;
import java.util.List;

@NamedNativeQuery(
        name = "Sales.fn_sales",
        query = "select * from fn_sales()",
        resultSetMapping = "Procedure.ProcedureDTO"
)

@SqlResultSetMapping(
        name = "Procedure.ProcedureDTO",
        classes = @ConstructorResult(targetClass = ProcedureDTO.class,
                columns = {
                        @ColumnResult(name = "quantityfn", type = Integer.class),
                        @ColumnResult(name = "datetimefn", type = String.class),
        })
)

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "FK_SALE_CLIENT"))
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_SALE_USER"))
    private User user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double total;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;

    @Column(nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetail> details;


}
