package com.awt.signin.signin.controller;

import com.awt.signin.signin.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteController {

    private final DeleteService deleteService;

    @Autowired
    public DeleteController(DeleteService deleteService) {
        this.deleteService = deleteService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        String result = deleteService.deleteProfile(id);
        if (result.contains("not found")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        } else {
            return ResponseEntity.ok(result);
        }
    }
}
