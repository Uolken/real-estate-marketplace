package marketplace.controller;

import marketplace.Coordinates;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @PutMapping("/points")
    public void addPoints(@RequestBody Coordinates coordinates){
        arr.add(coordinates);
    }


}
