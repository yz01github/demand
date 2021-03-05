package com.west.business.service.seq;

import com.baomidou.mybatisplus.extension.service.IService;
import com.west.domain.entity.SequenceEntity;

/**
 * description: [获取序列接口]
 * title: ISequenceService
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/1/21
 */
public interface ISequenceService extends IService<SequenceEntity> {

    /**
     * description: [获取序列, 不同的业务若有需要，可以重写该方法]
     * @param seqId 序列id
     * @return  序列值
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/1/21
     */
    Long nextval(String seqId);

    /**
     * description: [获取序列,指定数据源]
     * @param dataSource 获取序列的数据源
     * @param seqId 序列id
     * @return  序列值
     * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
     * created 2021/1/21
     */
    Long nextval(String dataSource, String seqId);
}
