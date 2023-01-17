package com.west.business.controller.demo;

import com.west.business.annotation.ResultAdvice;
import com.west.business.pojo.vo.demands.DemandsVO;
import com.west.business.service.seq.ISequenceService;
import com.west.business.util.redis.RedisUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Arrays;

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
    public String aopTest(@PathVariable String str) throws InterruptedException {
        log.debug("aopTest:{}", str);
        Thread.sleep(100000);
        return str;
    }

    @GetMapping("/test/seq")
    public Long genSeq() throws InterruptedException {
        Thread.sleep(100000);
        Long nextval = sequenceService.nextval("SEQ_TEST_ID");
        return nextval;
    }

    @GetMapping("/test/seqCycle")
    public Long genSeqCycle() throws InterruptedException {
        Thread.sleep(100000);
        Long nextval = sequenceService.nextval("SEQ_TEST_ID");
        return nextval;
    }

    @PostMapping("/test/redis/cluster/set")
    public Long testRedis() throws InterruptedException {
        RedisUtil.set("aaa", "a1");
        RedisUtil.set("ccc", "a2");
        RedisUtil.set("12esaa", "a3");
        Long nextval = sequenceService.nextval("SEQ_TEST_ID");
        return nextval;
    }

    @GetMapping("/test/redis/cluster/get")
    public List<String> testRedisGet() throws InterruptedException {
        // TODO springCache
        RedisUtil.set("aaa", "a1");
        RedisUtil.set("ccc", "a2");
        RedisUtil.set("12esaa", "a3");
        String aaa = RedisUtil.get("aaa");
        String ccc = RedisUtil.get("ccc");
        String s = RedisUtil.get("12esaa");
        return Arrays.asList(aaa, ccc, s);
    }

    /**
     * 缓存数据
     * @Cacheable 缓存;使用@Cacheable标注的方法，Spring在每次执行前都会检查Cache中是否存在相同key的缓存元素，如果存在就不再执行该方法，而是直接从缓存中获取结果进行返回，否则才会执行并将返回结果存入指定的缓存中
     * @CachePut：缓存更新;与@Cacheable不同的是使用@CachePut标注的方法在执行前不会去检查缓存中是否存在之前执行过的结果，而是每次都会执行该方法，并将执行结果以键值对的形式存入指定的缓存中
     * @CacheEvict：缓存删除;用来标注在需要清除缓存元素的方法或类上的。当标记在一个类上时表示其中所有的方法的执行都会触发缓存的清除操作
     * https://blog.csdn.net/dalong_bamboo/article/details/103844246
     */
    @Cacheable(value = "TEST_CACHE", key="'Demo2Controller-testEnableCache-'+#demandsVO.demandId+'-'+#demandsVO.demandCode")
    @PostMapping("/test/redis/cache/cacheable")
    public List<String> testEnableCache(@RequestBody DemandsVO demandsVO) {
        return Arrays.asList(demandsVO.getDemandId()+demandsVO.getDemandCode());
    }

    @CachePut
    @PostMapping("/test/redis/cache/cachePut")
    public List<String> testPutCache(@RequestBody DemandsVO demandsVO) {
        return Arrays.asList(demandsVO.getDemandId()+demandsVO.getDemandCode());
    }

    @CacheEvict
    @PostMapping("/test/redis/cache/cacheEvict")
    public List<String> testEvictCache(@RequestBody DemandsVO demandsVO) {
        return Arrays.asList(demandsVO.getDemandId()+demandsVO.getDemandCode());
    }
    @Caching
    @PostMapping("/test/redis/cache/caching")
    public List<String> testCaching(@RequestBody DemandsVO demandsVO) {
        return Arrays.asList(demandsVO.getDemandId()+demandsVO.getDemandCode());
    }
    /**
     * description: [使用指定的缓存key生成器]
     * created 2023/1/16
     */
    @Cacheable(value = "TEST_CACHE", keyGenerator="defaultKeyGenerator")
    @PostMapping("/test/redis/cache/KeyGenerator")
    public List<String> testEnableCacheKeyGenerator(@RequestBody DemandsVO demandsVO) {
        return Arrays.asList(demandsVO.getDemandId()+demandsVO.getDemandCode());
    }


}
