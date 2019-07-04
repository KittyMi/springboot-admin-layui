package com.zhilian.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 学员清单实体
 * @author Andy_Lai
 * @date 2019-04-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO implements Serializable {
    /**
     * 学生姓名
     */
    private String name;

    /**
     * 学生电话
     */
    private String phone;

    /**
     * 区县
     */
    private String country;

    /**
     * 营销服务中心
     */
    private String center;

    /**
     * 片区
     */
    private String area;

    /**
     * 队名
     */
    private String teamName;

    /**
     * 是否为组长
     */
    private String teamLeader;
}
