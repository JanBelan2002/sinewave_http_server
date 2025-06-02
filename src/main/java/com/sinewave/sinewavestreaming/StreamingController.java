package com.sinewave.sinewavestreaming;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
@RestController
public class StreamingController {

    @GetMapping("/stream")
    public ResponseEntity<InputStreamResource> streamMusic(@RequestParam String song) throws IOException {
        File file = new File("music/" + song);
        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        InputStream inputStream = new FileInputStream(file);
        InputStreamResource resource = new InputStreamResource(inputStream);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("audio/mpeg"));
        headers.setContentLength(file.length());
        headers.set("Accept-Ranges", "bytes");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
}
}
