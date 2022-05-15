package com.vtripathi.blogproject.ServiceImpl;

import com.vtripathi.blogproject.Service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        String name = file.getOriginalFilename();

        //Generate random name for the file
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));

        //File path
        String filePath = path + File.separator + fileName;

        //Create folder if it doesn't exist
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        //Copy file to the folder
        Files.copy(file.getInputStream(), Paths.get(filePath));

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;

        InputStream inputStream = new FileInputStream(fullPath);
        return inputStream;
    }
}
