package meng.xing.api.normol;

import meng.xing.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/papers")
public class PaperController {
    @Autowired
    PaperService paperService;
}
