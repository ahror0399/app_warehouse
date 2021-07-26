package uz.developers.appwarehouse.service.worehouseService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.developers.appwarehouse.entity.warehouseEntity.Attechment;
import uz.developers.appwarehouse.entity.warehouseEntity.Attechment_Content;
import uz.developers.appwarehouse.repository.warehouse.AttechmentContentRepository;
import uz.developers.appwarehouse.repository.warehouse.AttechmentRepository;
import uz.developers.appwarehouse.results.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttechmentRepository attechmentRepository;

    @Autowired
    AttechmentContentRepository attechmentContentRepository;

    @Autowired
    Result result;

    public Result add(MultipartHttpServletRequest request){
        Iterator<String> fileNames = request.getFileNames();
        int count=0;
        while (fileNames.hasNext()) {
            MultipartFile file=request.getFile(fileNames.next());
            if (!file.isEmpty()){
                Attechment attechment=new Attechment();
                attechment.setContentType(file.getContentType());
                attechment.setName(file.getOriginalFilename());
                attechment.setSize(file.getSize());
                Attechment savedAttechmant = attechmentRepository.save(attechment);
                Attechment_Content attechment_content=new Attechment_Content();
                try {
                    attechment_content.setBytes(file.getBytes());
                    attechment_content.setAttechment(savedAttechmant);
                    attechmentContentRepository.save(attechment_content);
              count++;
                } catch (IOException e) {


                }


            }


        }

        result.setMessage(count+" file succesfully saved");
        result.setSuccess(true);
        return result;

    }

    public void getById(Long id, HttpServletResponse response){
        Optional<Attechment> optionalAttechment = attechmentRepository.findById(id);
        Attechment_Content attechment_content = attechmentContentRepository.findByAttechmentId(id);

        if (!optionalAttechment.isPresent()||  attechment_content!=null){
//            response.setHeader(
//                    "Content-Disposition","attechment; filename=\""+optionalAttechment.get().getName()+
//                            "\"");

            response.setContentType(optionalAttechment.get().getContentType());
            try {
                FileCopyUtils.copy(attechment_content.getBytes(),response.getOutputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }

}
