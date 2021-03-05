package com.west.business.service.seq;

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

    private static final Map<String, Queue<Long>> CACHE_SEQ_MAP = new ConcurrentHashMap<>();

    @Autowired
    private SequenceDao sequenceDao;

    @Override
    public Long nextval(String seqId) {
        return nextval("busi_01", seqId);
    }

    @Override
    public Long nextval(String dataSource, String seqId) {
        // TODO 不同数据源暂时没有条件测试，后续有多台主机时完善,目前暂时写死
        String key = dataSource + "^" + seqId;
        // 获取缓存的序列，如果队列还没有缓存，则初始化队列
        while (Objects.isNull(CACHE_SEQ_MAP.get(key))) {
            initCacheQue(key);
        }
        Queue<Long> cacheSeq = CACHE_SEQ_MAP.get(key);
        Long pollValue = cacheSeq.poll();
        if (Objects.nonNull(pollValue)){
            return pollValue;
        }

        // 缓存队列中无值, 查询序列，并加入缓存队列
        initQueueData(cacheSeq, seqId);
        return cacheSeq.poll();
    }

    private void initQueueData(Queue<Long> cacheSeq, String seqId) {
        synchronized (this){
            Long pollValue = cacheSeq.poll();
            if(Objects.nonNull(pollValue)){
                return;
            }
            // callSeqStr eg: 1010101,5
            String callSeqStr = sequenceDao.callSeq(seqId);
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

    private void initCacheQue(String key) {
        synchronized (this) {
            Queue<Long> cacheSeq = CACHE_SEQ_MAP.get(key);
            if (Objects.nonNull(cacheSeq)) {
                return;// 加锁时，被其他线程完成初始化了
            }
            cacheSeq = new ConcurrentLinkedQueue<>();
            CACHE_SEQ_MAP.put(key, cacheSeq);
        }
    }
}
