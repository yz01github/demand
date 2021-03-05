package com.west.business.controller.demo;

import com.west.business.pojo.pub.ResResult;
import com.west.business.pojo.vo.demandHours.CreateDemandHoursVO;
import com.west.business.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * description: []
 * title: TransactionCommitDealDemo
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/8
 */
@Api(tags = "学习相关")
@Slf4j
@Controller
@RequestMapping("/learn")
public class TransactionCommitDealDemo {

    @Autowired
    private UserService userService;

    @ApiOperation(value="测试事务提交额外处理",notes="模拟一次事务提交")
    @PostMapping("/demo1")
    @ResponseBody
    public ResResult<Integer> putInfo() {
        commitDeal();

        int updateNum = 1;//userService.updateTest(null);
        log.debug("updateNum:{}",updateNum );
        return ResResult.success("demo1 end...");
    }

    private void commitDeal() {
        TransactionSynchronizationManager.registerSynchronization(
                new TransactionSynchronization(){
                    @Override
                    public void suspend() {
                        log.debug("commitDeal.suspend...");
                    }
                    @Override
                    public void resume() {
                        log.debug("commitDeal.resume...");
                    }
                    @Override
                    public void flush() {
                        log.debug("commitDeal.flush...");
                    }
                    @Override
                    public void beforeCommit(boolean readOnly) {
                        log.debug("commitDeal.beforeCommit...");
                    }
                    @Override
                    public void beforeCompletion() {
                        log.debug("commitDeal.beforeCompletion...");
                    }
                    @Override
                    public void afterCommit() {
                        log.debug("commitDeal.afterCommit...");
                    }
                    @Override
                    public void afterCompletion(int status) {
                        log.debug("commitDeal.afterCompletion...;{}", status);
                    }
                }
        );

    }
}
