package com.zhilian.market.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 客户清单实体
 * @author Andy_Lai
 * @date 2019-04-11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentUserDTO implements Serializable {
    private Integer id;
    /**
     * 客户姓名
     */
    private String name;

    /**
     * 客户电话
     */
    private String phone;

    /**
     * 营销属性
     */
    private String attrs;

    /**
     * 市
     */
    private String city;

    /**
     * 区县
     */
    private String county;

    /**
     * 营销服务中心
     */
    private String center;

    /**
     * 片区
     */
    private String area;

    private String managerUser;

    private String managerPhone;

}
