package uz.developers.appwarehouse.controller.warehouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.developers.appwarehouse.results.Result;
import uz.developers.appwarehouse.service.worehouseService.AttachmentService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/attechment")
public class AttechmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping
    public Result add(MultipartHttpServletRequest request) {
        return attachmentService.add(request);
    }

    @GetMapping("/{id}")
    public void getBYId(@PathVariable Long id, HttpServletResponse response) {
        attachmentService.getById(id, response);
    }

}
