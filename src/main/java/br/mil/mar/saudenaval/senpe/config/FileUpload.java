package br.mil.mar.saudenaval.senpe.config;


import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUpload {


    private static String defaultPath = "/home/dsm/senpe/default/files/";

    public static String save(String folder, MultipartFile file) throws IOException {
       // Path uploadPath = Paths.get(defaultPath.concat(folder));
        Path uploadPath = Paths.get(defaultPath,folder);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(file.getOriginalFilename());
        Files.deleteIfExists(filePath);
        Files.copy(file.getInputStream(),filePath);



        return  folder + "/files/" + file.getOriginalFilename();

    }


    public static ResponseEntity<Resource> download(String folder, String filename){

        try {
            Path filePath = Paths.get(defaultPath.concat(folder)).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists()){
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            }else{
                return ResponseEntity.badRequest().build();
            }

        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.internalServerError().build();
        }
    }

    public static void removeFile(String folder,String filename) throws IOException {
        Path uploadPath = Paths.get(defaultPath.concat(folder));
        if (!Files.exists(uploadPath)){
            Path filePath = uploadPath.resolve(filename);
            Files.deleteIfExists(filePath);
        }
    }
}
