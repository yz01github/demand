package com.west.business.pojo.dto.context;

import lombok.Builder;
import lombok.Data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: []
 * title: Visit
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2022/12/27
 */
@Data
@Builder
public class Visit {

    private HttpServletRequest request ;

    private HttpServletResponse response ;

}
