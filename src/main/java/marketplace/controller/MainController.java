package marketplace.controller;

import marketplace.entity.House;
import marketplace.entity.HouseStatus;
import marketplace.entity.User;
import marketplace.service.HouseService;
import marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    //@Autowired
    private HouseService houseService;
    //@Autowired
    private UserService userService;



    @Autowired
    public MainController(HouseService houseService, UserService userService){
        this.userService = userService;
        this.houseService = houseService;
        User user = new User("login", "Password");
        userService.saveUser(user);
        houseService.saveHouse(new House(52.353226, 4.889268, user, HouseStatus.SALE, 100));
        houseService.saveHouse(new House(52.363049, 4.901847, user, HouseStatus.SALE, 100));
        houseService.saveHouse(new House(52.365292, 4.857654, user, HouseStatus.SALE, 100));
        houseService.saveHouse(new House(52.366852,4.893838, user, HouseStatus.SALE, 100));
    }


    @GetMapping("/home")
    public String getMainPage(){

        return "index";
    }


    @GetMapping("/points")
    @ResponseBody
    public List<House> getPointsByCoord(
            @RequestParam Double southWest_x,
            @RequestParam Double southWest_y,
            @RequestParam Double northEast_x,
            @RequestParam Double northEast_y
    ){

        List<House> houses = houseService.getHouseByCoord(southWest_x, southWest_y, northEast_x, northEast_y);
        return houses;
    }


    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("/points")
    public void addPoints(@RequestBody House house){

        houseService.saveHouse(house);
    }



}
