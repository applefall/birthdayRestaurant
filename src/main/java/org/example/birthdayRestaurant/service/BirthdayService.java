package org.example.birthdayRestaurant.service;

import org.example.birthdayRestaurant.model.BirthdayInfo;

import java.time.LocalDate;
import java.util.List;

public interface BirthdayService {

    /**
     * 添加生日信息
     * @param customer
     * @param tableNumber
     * @param restaurantName
     */
    long addBirthdayInfo(String customer,Integer gender,String tableNumber,String restaurantName);

    /**
     * 获取店铺生日信息
     * @param startDate
     * @param endDate
     */
    long getBirthdayInformation(LocalDate startDate,LocalDate endDate);

    /**
     * 当天弹幕列表
     */
    List<BirthdayInfo> todayBarrage();
}
