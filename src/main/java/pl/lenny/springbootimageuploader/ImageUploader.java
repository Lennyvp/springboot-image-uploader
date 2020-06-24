package pl.lenny.springbootimageuploader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lenny.springbootimageuploader.model.Image;
import pl.lenny.springbootimageuploader.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class ImageUploader {
    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    @Autowired
    public ImageUploader(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;
        this.cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dlootk72x",
                "api_key", "565233566671455",
                "api_secret", "ykWZ5VTepRuHKXB8-jgjKiqf0Os"));
    }

    public String uploadFile(String path) {
        // "G:\\test\\IMG_0005.jpg"
        File file = new File(path);
        Map uploadResult = null;
        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//            uploadResult = cloudinary.uploader().unsignedUpload( + file,ObjectUtils.emptyMap());
        } catch (IOException e) {
            e.printStackTrace();
            // todo
        }
        return uploadResult.get("url").toString();
    }

    public void saveFileToDb(String path) {
        String address = uploadFile(path);
        System.out.println(address);
        imageRepo.save(new Image(address));
    }

//    public List<Image> loadFiles() {
//        return
//    }
}
