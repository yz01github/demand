package com.west.business.pojo.vo.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * description: [前台分页查询入参封装(仅用于mybatisPlus不适用于pageHelper)]
 * title: PageVO
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2020/7/27
 */
@Data
public class PageVO<T> {

    private long pageNum;

    private long pageSize;

    public Page getPage(){
        if(pageNum == 0 && pageSize == 0){// 分页查询默认值
            pageNum  = 1;
            pageSize = 10;
        }
        return new Page<T>(pageNum, pageSize);
    }
}
