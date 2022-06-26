package com.jonas.config.response.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author shenjy
 * @date 2020/8/14
 * @description
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonPage<T> {
    private long totalElements;
    private long totalPages;
    private List<T> content;
}
