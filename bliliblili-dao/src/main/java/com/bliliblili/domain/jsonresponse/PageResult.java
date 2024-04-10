package com.bliliblili.domain.jsonresponse;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @ author 星星草去哪了
 * @ data 2024/3/30 14:03
 * @ 注释
 */
@Data
@AllArgsConstructor
public class PageResult<T> {
    //分页查询总数
    private Integer total;

    //分页数据
    private List<T> List;
}
