package org.example.birthdayRestaurant.service.impl;

import org.example.birthdayRestaurant.model.BirthdayInfo;
import org.example.birthdayRestaurant.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class BirthdayServiceImpl implements BirthdayService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public long addBirthdayInfo(String customer, Integer gender, String tableNumber, String restaurantName) {

        BirthdayInfo birthdayInfo = new BirthdayInfo();
        birthdayInfo.setCustomer(customer);
        birthdayInfo.setGender(gender);
        birthdayInfo.setTableNumber(tableNumber);
        birthdayInfo.setRestaurantName(restaurantName);
        birthdayInfo.setArrivalTime(LocalDateTime.now());
        mongoTemplate.save(birthdayInfo);

        Query query = new Query()
                .addCriteria(Criteria.where("restaurantName").all(restaurantName));

        long count = mongoTemplate.count(query, BirthdayInfo.class);

        return count;
    }

    @Override
    public long getBirthdayInformation(LocalDate startDate, LocalDate endDate) {
        Query query = new Query();
        query.addCriteria(new Criteria().andOperator(Criteria.where("arrivalTime").gte(startDate),
                Criteria.where("arrivalTime").lte(endDate)));
        long count = mongoTemplate.count(query, BirthdayInfo.class);
        return count;
    }

    @Override
    public List<BirthdayInfo> todayBarrage() {
        LocalDateTime startDate = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime endDate = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0);

        Query query = new Query()
                .addCriteria(new Criteria().andOperator(Criteria.where("arrivalTime").gte(startDate),
                        Criteria.where("arrivalTime").lte(endDate)));
        List<BirthdayInfo> birthdayInfoList = mongoTemplate.find(query, BirthdayInfo.class);

        return birthdayInfoList;
    }
}
