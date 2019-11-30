package marketplace.controller;

import marketplace.entity.*;
import marketplace.service.HouseService;
import marketplace.service.ImageService;
import marketplace.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@Controller
public class MainController {

    //@Autowired
    private HouseService houseService;
    //@Autowired
    private UserService userService;

    private ImageService imageService;



    @Autowired
    public MainController(HouseService houseService, UserService userService, ImageService imageService){
        this.userService = userService;
        this.houseService = houseService;
        this.imageService = imageService;
        User user = new User("id", "name","avatar", "email");
        userService.saveUser(user);
        houseService.saveHouse(new House(52.353226, 4.889268, user, HouseStatus.SALE, 100));
        houseService.saveHouse(new House(52.363049, 4.901847, user, HouseStatus.SALE, 100));
        houseService.saveHouse(new House(52.365292, 4.857654, user, HouseStatus.SALE, 100));
        houseService.saveHouse(new House(52.366852,4.893838, user, HouseStatus.SALE, 100));
    }


    @GetMapping("/home")
    public String getMainPage(){

        return "home";
    }

    @GetMapping("/index")
    public String getIndexPage(){

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

         List<House> houses = houseService.findMarkerByCoord(southWest_x, southWest_y, northEast_x, northEast_y);
        return houses;
    }

    @GetMapping("/user/{userId}")
    @ResponseBody
    public User getPointsByCoord(
            @PathVariable String userId
    ) throws Exception {

        return userService.findById(userId);
    }


    @GetMapping("/house")
    @ResponseBody
    public List<House> getHouse(){
        return houseService.findAll();
    }

    @GetMapping("/house/{id}")
    @ResponseBody
    public House getHouse(@PathVariable Long id) throws Exception{
        return houseService.findById(id);
    }

    @PutMapping("/points")
    @ResponseBody
    public void addPoints(@RequestBody House house){

        houseService.saveHouse(house);
    }

    @PostMapping("/upload")
    @ResponseBody
    String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file){
        imageService.saveImage(file);
        return "Success";
    }

    @GetMapping("/test")
    public String testImage(){
        return "test";
    }

    @GetMapping("/img/{imgId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long imgId) {
        // Load file from database
        Image image = imageService.getImage(imgId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getFileName() + "\"")
                .body(new ByteArrayResource(image.getData()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleStorageFileNotFound(Exception e) {
        return ResponseEntity.badRequest().build();
    }

}
