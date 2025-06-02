package com.sinewave.sinewavestreaming;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.StandardCopyOption.*;
@RestController

public class UploadingController {
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try {
            String filename = file.getOriginalFilename();
            Path path = Paths.get("music/" + filename);
            Files.copy(file.getInputStream(), path, REPLACE_EXISTING);
            return ResponseEntity.ok("File uploaded: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed");
        }
    }
}
