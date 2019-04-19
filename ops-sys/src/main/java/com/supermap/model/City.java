package com.supermap.model;

import lombok.Data;

/**
 * City
 *
 * @author leishiguang
 * @version v1.0.0
 * @since v1.0
 */
@Data
public class City {
    private Long id;
    private String name;
    private String state;
    private String country;
}
