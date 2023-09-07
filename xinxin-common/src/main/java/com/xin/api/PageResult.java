package com.xin.api;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * @Author: ljt
 * @Date: 2023/9/6 15:42
 * @Description: PageResult
 * @Version 1.0.0
 */
@Data
public class PageResult<T> {
    private List<T> data;
    private Integer pageNum;
    private Integer pageSize;
    private Long totalNum;

    public PageResult() {
    }

    public PageResult(List<T> data, Integer pageNum, Integer pageSize, long totalNum) {
        this.data = data;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
    }

    public static <T> PageResult<T> convertPageResult(List<T> data) {
        PageInfo pageInfo = new PageInfo(data);
        return new PageResult<>(data, pageInfo.getPageNum(), pageInfo.getPageSize(), pageInfo.getTotal());
    }
}
