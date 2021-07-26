package uz.developers.appwarehouse.entity.warehouseEntity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Attechment_Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private byte [] bytes;
    @OneToOne
    private Attechment attechment;

}
