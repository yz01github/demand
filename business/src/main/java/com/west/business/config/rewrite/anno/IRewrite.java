package com.west.business.config.rewrite.anno;

import java.util.Map;

/**
 * description: []
 * title: RewriteAnno
 *
 * @author <a href="mailto:learnsoftware@163.com">yangzhi</a>
 * created 2021/6/10
 */
public interface IRewrite<T> {

    void refresh() throws Exception;

    Map<String, T> loadData() throws Exception;

}
