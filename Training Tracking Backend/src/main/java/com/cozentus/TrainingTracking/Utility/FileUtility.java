package com.cozentus.TrainingTracking.Utility;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;



@Component
public class FileUtility {

    public final String ABSOLUTE_PATH = "D:\\Secure_Backend\\Main_Backend_Security\\src\\main\\resources\\static\\FileUpload\\";

    public FileUtility() {}

    public boolean uploadFile(MultipartFile multiPartFile) {
        try {
            Files.copy(multiPartFile.getInputStream(), Paths.get(ABSOLUTE_PATH, multiPartFile.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
