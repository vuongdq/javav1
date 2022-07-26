package com.vdq.controllers;


import com.vdq.models.ResponseObject;
import com.vdq.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api/v1/FileUpload")
public class FileUploadController {
    // this is controller receive file/image from client
    @Autowired
    private ImageStorageService imageStorageService;

    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file){
        try {
            String generatedFileName = imageStorageService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Upload file successfully",generatedFileName)
            );

        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed",exception.getMessage(),"")
            );

        }
    }
    //Get Image's url
    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName){
        try {
            byte[] bytes = imageStorageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception){
            return ResponseEntity.noContent().build();

        }
    }
    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFile(){
        try {
            List<String> urls = imageStorageService.loadAll()
                    .map(path -> {
                        // Convert fileName to Url (send request ReadeDetailFile)
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,"readDetailFile",path.getFileName()
                        .toString()).build().toUri().toString();
                        return urlPath;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(
                    new ResponseObject("ok","list file successfully",urls)
            );
        }
        catch (Exception exception){
            return ResponseEntity.ok(
                    new ResponseObject("Failed","List file failed",new String[] {})
            );

        }

    }


}
