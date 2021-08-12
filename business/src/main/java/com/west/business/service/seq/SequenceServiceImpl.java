package com.west.business.service.seq;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.west.domain.dao.SequenceDao;
import com.west.domain.entity.SequenceEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * description: [获取序列服务实现]
 * title: SequenceServiceImpl
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/21
 */
@Service
public class SequenceServiceImpl extends ServiceImpl<SequenceDao, SequenceEntity>
        implements ISequenceService {

    // 序列结果缓存;final修饰,防止引用修改
    private static final Map<String, Queue<Long>> CACHE_SEQ_MAP = new ConcurrentHashMap<>();

    // 序列配置校验结果缓存
    private static final Map<String, Boolean> CACHE_SEQ_CHECK_MAP = new ConcurrentHashMap<>();

    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public Long nextval(String seqId) {
        return nextval("busi_01", seqId);
    }

    @Override
    public Long nextval(String dataSource, String seqId) {
        return nextval(dataSource, seqId, false);
    }

    @Override
    public Long nextvalByCycle(String dataSource, String seqId) {
        return nextval(dataSource, seqId, true);
    }

    @Override
    public Long nextvalByCycle(String seqId) {
        return nextval("busi_01", seqId, true);
    }

    private Long nextval(String dataSource, String seqId, boolean isCycle) {
        // 校验配置,防止配置导致业务异常
        checkSeqConfig(seqId);

        // TODO 不同数据源暂时没有条件测试，后续有多台主机时完善,目前暂时写死
        String key = dataSource + "^" + seqId;
        // 获取缓存的序列，如果队列还没有缓存，则初始化队列
        Queue<Long> cacheSeq = CACHE_SEQ_MAP.get(key);
        while (Objects.isNull(cacheSeq)) {
            synchronized (this) {
                if (Objects.nonNull(CACHE_SEQ_MAP.get(key))) {
                    break;// 加锁时，被其他线程完成初始化了
                }
                cacheSeq = new ConcurrentLinkedQueue<>();
                CACHE_SEQ_MAP.put(key, cacheSeq);
            }
        }
        Long pollValue = cacheSeq.poll();
        if (Objects.nonNull(pollValue)){
            return pollValue;
        }

        // 缓存队列中值已经用完, 查询序列新值,放入队列
        initQueueData(cacheSeq, seqId, isCycle);
        return cacheSeq.poll();
    }

    /**
     * description: [校验seq配置异常]
     * @param   seqId   seq唯一标识
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/8/11
     */
    private void checkSeqConfig(String seqId) {
        // 优先缓存获取结果
        Boolean checkCache = CACHE_SEQ_CHECK_MAP.getOrDefault(seqId, Boolean.FALSE);
        if(checkCache){
            return;
        }

        // 校验逻辑
        QueryWrapper<SequenceEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("SEQ_ID", seqId);
        SequenceEntity entity = sequenceDao.selectOne(wrapper);
        Integer limitMaxValue = entity.getLimitMaxValue();
        Integer cacheSize = entity.getCacheSize();

        if(Objects.isNull(cacheSize) || cacheSize<=1){
            throw new IllegalArgumentException("序列必须配置缓存长度,且缓存长度大于1:"+seqId);
        }
        if(Objects.nonNull(limitMaxValue) && cacheSize>limitMaxValue){
            throw new IllegalArgumentException("序列的缓存长度不可以大于最大值:"+seqId);
        }

        // 结果缓存
        CACHE_SEQ_CHECK_MAP.put(seqId, Boolean.TRUE);
    }


    private void initQueueData(Queue<Long> cacheSeq, String seqId, boolean isCycle) {
        synchronized (this){
            // 锁后校验
            Long pollValue = cacheSeq.poll();
            if(Objects.nonNull(pollValue)){
                return;
            }
            // callSeqStr eg: 1010101,5
            String callSeqStr = isCycle ? sequenceDao.callCycleSeq(seqId): sequenceDao.callSeq(seqId);
            if(StringUtils.isBlank(callSeqStr) || !callSeqStr.contains(",")){
                throw new IllegalStateException("序列获取异常["+seqId+"]["+callSeqStr+"]");
            }
            String[] splitStr = callSeqStr.split(",");
            if(splitStr.length != 2){
                throw new IllegalStateException("序列长度获取异常["+seqId+"]["+callSeqStr+"]");
            }
            try{
                long seqEnd = Long.parseLong(splitStr[0]);
                long cacheSize = Long.parseLong(splitStr[1]);
                long seqBegin = seqEnd-cacheSize;
                // 缓存结果, 前闭后开
                while (seqBegin < seqEnd){
                    cacheSeq.add(seqBegin);
                    seqBegin++;
                }
            }catch (Exception e){
                throw new IllegalStateException("序列解析异常["+seqId+"]"+splitStr);
            }
        }
    }

}
