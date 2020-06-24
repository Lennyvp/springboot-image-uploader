package pl.lenny.springbootimageuploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.lenny.springbootimageuploader.model.Image;
import pl.lenny.springbootimageuploader.repo.ImageRepo;

import java.util.List;

@RestController
@CrossOrigin
public class ImageUploaderController {

    private ImageUploader imageUploader;
    private ImageRepo imageRepo;

//    @Autowired
//    public ImageUploaderController(ImageRepo imageRepo) {
//        this.imageRepo = imageRepo;
//    }

        @Autowired
    public ImageUploaderController(ImageUploader imageUploader, ImageRepo imageRepo) {
        this.imageUploader = imageUploader;
        this.imageRepo = imageRepo;
    }

    @GetMapping("/load")
    public List<Image> getImages() {
            return imageRepo.findAll();
    }

    @PostMapping(value = "/upload", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void uploadImage(@RequestBody Image image) {
        imageUploader.saveFileToDb(image.getPath());
    }
    
}
