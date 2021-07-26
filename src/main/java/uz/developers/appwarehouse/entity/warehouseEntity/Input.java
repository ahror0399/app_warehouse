package uz.developers.appwarehouse.entity.warehouseEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Input {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Timestamp date;

    @ManyToOne(optional = false)
  private   Warehouse warehouse;

    @ManyToOne(optional = false)
    private Supplier supplier;

    @ManyToOne(optional = false)
    private Currency currency;

    private String factureNumber;

    @Column(nullable = false, unique = true)
    private String code;

}
