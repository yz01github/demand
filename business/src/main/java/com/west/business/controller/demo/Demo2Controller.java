package com.west.business.controller.demo;

import com.west.business.annotation.ResultAdvice;
import com.west.business.service.seq.ISequenceService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: []
 * title: Demo2Controller
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/5/12
 */
@Api(tags = "接口Demo2")
@Slf4j
@RestController
@RequestMapping("/demo2")
@ResultAdvice
public class Demo2Controller {

    @Autowired
    private ISequenceService sequenceService;

    @GetMapping("/test/{str}")
    public String aopTest(@PathVariable String str) {
        log.debug("aopTest:{}", str);
        return str;
    }

    @GetMapping("/test/seq")
    public Long genSeq() {
        Long nextval = sequenceService.nextval("SEQ_TEST_ID");
        return nextval;
    }

    @GetMapping("/test/seqCycle")
    public Long genSeqCycle() {
        Long nextval = sequenceService.nextval("SEQ_TEST_ID");
        return nextval;
    }

}
