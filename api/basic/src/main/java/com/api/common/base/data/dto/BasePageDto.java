package com.api.common.base.data.dto;

import cn.hutool.core.util.StrUtil;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 分页查询基类Dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BasePageDto extends BaseDto {

    // 页码值
    @NotNull(message = "页码值不能为空")
    @Min(value = 1, message = "页码值小于1")
    private Integer page;

    // 每页数量
    @NotNull(message = "每页数量不能为空")
    @Min(value = 1, message = "每页数量小于1")
    @Max(value = 1000, message = "每页数量不能超过1000")
    private Integer pageSize;

    // 排序字段
    private String sortFiled;

    // 排序方式。ASC、DESC
    private String sortType;

    /**
     * 设置排序方式
     *
     * @param sortType 排序方式
     */
    public void setSortType(String sortType) {
        if (StrUtil.isNotBlank(StrUtil.trim(sortType))) {
            String trimmedSortType = StrUtil.trim(sortType).toUpperCase();
            if (trimmedSortType.contains("END")) {
                trimmedSortType = trimmedSortType.replace("END", "");
            }
            if ("ASC".equals(trimmedSortType) || "DESC".equals(trimmedSortType)) {
                this.sortType = trimmedSortType;
            } else {
                // 如果传入的不是 ASC 或 DESC，可以设置一个默认值，例如 ASC
                this.sortType = "ASC";
            }
        }
    }
}
