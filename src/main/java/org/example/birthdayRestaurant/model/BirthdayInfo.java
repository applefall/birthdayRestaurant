package org.example.birthdayRestaurant.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 生日信息
 */
@Document
public class BirthdayInfo   {
    /**
     * 顾客名称
     */
    private String customer;

    /**
     * 性别，男0 女1
     */
    private Integer gender;

    /**
     * 桌号
     */
    private String tableNumber;

    /**
     * 餐厅名称
     */
    private String restaurantName;

    /**
     * 光临时间
     */
    private LocalDateTime arrivalTime;

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
