package com.supermap.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CityEntity 模型层
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Data
@Table(name = "City")
@NoArgsConstructor
@AllArgsConstructor
public class CityEntity {
    @Id
    private Integer id;
    private String name;
    private String state;
    private String country;
}
