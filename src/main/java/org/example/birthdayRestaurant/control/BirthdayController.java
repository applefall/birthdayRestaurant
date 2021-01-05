package org.example.birthdayRestaurant.control;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import org.example.birthdayRestaurant.model.BirthdayInfo;
import org.example.birthdayRestaurant.model.dto.BaseDTO;
import org.example.birthdayRestaurant.service.BirthdayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/birthdayRestaurant")
public class BirthdayController {

    @Autowired
    private BirthdayService birthdayService;

    @Autowired
    private SocketIOServer socketIONamespace;

    @RequestMapping(value = "/addBirthdayInformation")
    public BaseDTO addBirthdayInformation(String customer,String gender,String tableNumber,String restaurantName){
        long count = birthdayService.addBirthdayInfo(customer,Integer.parseInt(gender),tableNumber,restaurantName);

        BirthdayInfo birthdayInfo = new BirthdayInfo();
        birthdayInfo.setCustomer(customer);
        birthdayInfo.setTableNumber(tableNumber);
        birthdayInfo.setRestaurantName(restaurantName);
        birthdayInfo.setGender(Integer.parseInt(gender));
        socketIONamespace.getRoomOperations("barrage").sendEvent("birthdayInfo",birthdayInfo);

        return new BaseDTO<Long>().success(count);
    }

    @RequestMapping(value = "/getBirthdayInformation")
    public BaseDTO getBirthdayInformation(@RequestParam(required = false) String startDate,
                                          @RequestParam(required = false)String endDate){
        startDate = StringUtils.isEmpty(startDate)?"2000-01-01":startDate;
        endDate = StringUtils.isEmpty(endDate)?"2090-01-01":endDate;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        long count = birthdayService.getBirthdayInformation(
                LocalDate.parse(startDate,dateTimeFormatter), LocalDate.parse(endDate,dateTimeFormatter));

        return new BaseDTO<Long>().success(count);
    }

    @RequestMapping(value = "/todayBarrage")
    public BaseDTO<List<BirthdayInfo>> todayBarrage(){

        BaseDTO<List<BirthdayInfo>> result ;

        List<BirthdayInfo> list = birthdayService.todayBarrage();
        if(!CollectionUtils.isEmpty(list)){
            result = new BaseDTO<>().success(list);
        }else{
            result = new BaseDTO<>().fail();
        }

        return result;
    }
}
