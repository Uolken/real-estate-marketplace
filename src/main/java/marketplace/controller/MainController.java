package marketplace.controller;

import marketplace.Coordinates;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    private List<Coordinates> arr;

    public MainController(){
        arr = new ArrayList<>();
        arr.add(new Coordinates(52.353226, 4.889268));
        arr.add(new Coordinates(52.363049, 4.901847));
        arr.add(new Coordinates(52.365292, 4.857654));
        arr.add(new Coordinates(52.366852,4.893838));
    }

    @GetMapping("/home")
    public String getMainPage(){
        return "home";
    }

    @GetMapping("/points")
    @ResponseBody
    public List<Coordinates> getPoints(){
        return arr;
    }

    @GetMapping("/points1")
    @ResponseBody
    public List<Coordinates> getPointsByCoord(
            @RequestParam Double southWest_x,
            @RequestParam Double southWest_y,
            @RequestParam Double northEast_x,
            @RequestParam Double northEast_y
    ){

        return arr.stream().filter(coordinates -> {
            return (coordinates.getX() >= southWest_x &&
                    coordinates.getX() <= northEast_x &&
                    coordinates.getY() >= southWest_y &&
                    coordinates.getY() <= northEast_y);
        }).collect(Collectors.toList());
    }



    @PutMapping("/points")
    public void addPoints(@RequestBody Coordinates coordinates){
        arr.add(coordinates);
    }



}
