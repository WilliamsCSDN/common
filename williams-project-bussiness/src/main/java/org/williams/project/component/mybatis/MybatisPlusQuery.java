package org.williams.project.component.mybatis;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求
 */
@Data
public class MybatisPlusQuery implements Serializable {

    private static final long serialVersionUID = 1L;
    @JSONField(name = "CurrentPage")
    private Integer currentPage = 1;
    @JSONField(name = "PageSize")
    private Integer pageSize = 10;

    public <P> Page<P> createPage() {
        return new Page<>(this.currentPage, this.pageSize);
    }
}
