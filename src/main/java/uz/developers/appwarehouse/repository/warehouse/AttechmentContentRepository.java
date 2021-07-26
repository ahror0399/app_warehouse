package uz.developers.appwarehouse.repository.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.developers.appwarehouse.entity.warehouseEntity.Attechment_Content;

public interface AttechmentContentRepository extends JpaRepository<Attechment_Content,Long> {
    Attechment_Content findByAttechmentId(Long attechment_id);

}
