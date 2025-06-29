package com.andreadangiolillo.basecrudapp.controllers;

import com.andreadangiolillo.basecrudapp.models.User;
import com.andreadangiolillo.basecrudapp.services.UserService;
import com.andreadangiolillo.basecrudapp.services.parameters.UserSearchParameters;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController extends BaseCrudController<User, Long>{

    private final UserService service;

    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @GetMapping("/search")
    public ResponseEntity<List<User>> search(@ModelAttribute UserSearchParameters parameters) {
        return ResponseEntity.ok(service.search(parameters));
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upload(@RequestPart("file") MultipartFile file) throws IOException {
        service.upload(file);
        return ResponseEntity.ok().build();
    }
}
