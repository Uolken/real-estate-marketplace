package marketplace.controller;

import marketplace.dto.AdDtoRequest;
import marketplace.entity.House;
import marketplace.entity.HouseStatus;
import marketplace.entity.Image;
import marketplace.entity.User;
import marketplace.service.HouseService;
import marketplace.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class HouseController {

    private HouseService houseService;

    private ImageService imageService;

    @Autowired
    public HouseController(HouseService houseService, ImageService imageService) {
        this.houseService = houseService;
        this.imageService = imageService;
    }

    @GetMapping("/points")
    public List<House> getPointsByCoord(
            @RequestParam Double southWest_x,
            @RequestParam Double southWest_y,
            @RequestParam Double northEast_x,
            @RequestParam Double northEast_y
    ) {

        List<House> houses = houseService.findMarkerByCoord(southWest_x, southWest_y, northEast_x, northEast_y);
        return houses;
    }


    @GetMapping("/house")
    public List<House> getHouse() {
        List<House> houses = houseService.findAll();
        return houses;
    }

    @GetMapping("/house/{id}")
    public House getHouse(@PathVariable Long id) throws Exception {
        return houseService.findById(id);
    }

    @PostMapping("/points")
    public void addPoints(@RequestBody House house) {
        houseService.saveHouse(house);
    }

    @PostMapping("/publish_ad")
    public void publishAd(Authentication authentication, @ModelAttribute AdDtoRequest ad) throws Exception {
        User user = (authentication != null) ? (User) authentication.getPrincipal() : null;
        House house = new House();
        String[] split = ad.getCoordinates().split(",");
        house.setCountry(ad.getCountry());
        house.setCity(ad.getCity());
        house.setStreet(ad.getStreet());
        house.setBuildingNumber(ad.getBuildingNumber());
        house.setLat(Double.parseDouble(split[1]));
        house.setLng(Double.parseDouble(split[0]));
        house.setOwner(user);
        house.setStatus(HouseStatus.valueOf(ad.getType()));
        house.setPrice(ad.getPrice());
        house.setArea(ad.getArea());
        house.setCountOfFloor(ad.getCountOfFloor());
        house.setCountOfRoom(ad.getCountOfRoom());
        house.setFloor(ad.getFloor());
        house.setDescription(ad.getDescription());


        for (MultipartFile i : ad.getFiles()){
            house.getPictures().add(imageService.saveImage(i).getId());
        }
        houseService.saveHouse(house);
    }


    @PostMapping("/upload")
    String uploadImage(@RequestParam(value = "file", required = false) MultipartFile file) {
        imageService.saveImage(file);
        return "Success";
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


//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleStorageFileNotFound(Exception e) {
//        return ResponseEntity.badRequest().build();
//    }
}
