package com.supermap.controller;

import com.supermap.mapper.CityMapper;
import com.supermap.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/syshome")
public class SysTestController {

    @Autowired
    CityMapper cityMapper;
    /**
     * freemarker Demo
     * 模板文件在 ops-web 包中
     */
    @RequestMapping("/")
    public String syshome(ModelMap modelMap) {
        modelMap.put("name", "Hello Sys");
        List<String> list = new ArrayList<>();
        list.add("First Element");
        list.add("Second");
        list.add("第三个");
        list.add("四号Element");
        modelMap.put("list", list);
        return "homesys";
    }

    /**
     * layui Demo
     */
    @RequestMapping("/layuidemo")
    public String layuiDemo(){
        return "layuidemo";
    }

    @RequestMapping("/layuidemo/table/user")
    @ResponseBody
    public String layuiDemoTable(){
        return "{\"code\":0,\"msg\":\"\",\"count\":1000,\"data\":[{\"id\":10000,\"username\":\"user-0\",\"sex\":\"女\",\"city\":\"城市-0\",\"sign\":\"签名-0\",\"experience\":255,\"logins\":24,\"wealth\":82830700,\"classify\":\"作家\",\"score\":57},{\"id\":10001,\"username\":\"user-1\",\"sex\":\"男\",\"city\":\"城市-1\",\"sign\":\"签名-1\",\"experience\":884,\"logins\":58,\"wealth\":64928690,\"classify\":\"词人\",\"score\":27},{\"id\":10002,\"username\":\"user-2\",\"sex\":\"女\",\"city\":\"城市-2\",\"sign\":\"签名-2\",\"experience\":650,\"logins\":77,\"wealth\":6298078,\"classify\":\"酱油\",\"score\":31},{\"id\":10003,\"username\":\"user-3\",\"sex\":\"女\",\"city\":\"城市-3\",\"sign\":\"签名-3\",\"experience\":362,\"logins\":157,\"wealth\":37117017,\"classify\":\"诗人\",\"score\":68},{\"id\":10004,\"username\":\"user-4\",\"sex\":\"男\",\"city\":\"城市-4\",\"sign\":\"签名-4\",\"experience\":807,\"logins\":51,\"wealth\":76263262,\"classify\":\"作家\",\"score\":6},{\"id\":10005,\"username\":\"user-5\",\"sex\":\"女\",\"city\":\"城市-5\",\"sign\":\"签名-5\",\"experience\":173,\"logins\":68,\"wealth\":60344147,\"classify\":\"作家\",\"score\":87},{\"id\":10006,\"username\":\"user-6\",\"sex\":\"女\",\"city\":\"城市-6\",\"sign\":\"签名-6\",\"experience\":982,\"logins\":37,\"wealth\":57768166,\"classify\":\"作家\",\"score\":34},{\"id\":10007,\"username\":\"user-7\",\"sex\":\"男\",\"city\":\"城市-7\",\"sign\":\"签名-7\",\"experience\":727,\"logins\":150,\"wealth\":82030578,\"classify\":\"作家\",\"score\":28},{\"id\":10008,\"username\":\"user-8\",\"sex\":\"男\",\"city\":\"城市-8\",\"sign\":\"签名-8\",\"experience\":951,\"logins\":133,\"wealth\":16503371,\"classify\":\"词人\",\"score\":14},{\"id\":10009,\"username\":\"user-9\",\"sex\":\"女\",\"city\":\"城市-9\",\"sign\":\"签名-9\",\"experience\":484,\"logins\":25,\"wealth\":86801934,\"classify\":\"词人\",\"score\":75}]}";
    }

    /**
     * 数据源
     */
    @RequestMapping("/dbtest")
    @ResponseBody
    public String DbTest(){
        City city1 = cityMapper.findByState("CA");
        City city3 = cityMapper.selectCityById(1);
        return city1.getCountry() + "," + city3.getCountry();
    }

}
