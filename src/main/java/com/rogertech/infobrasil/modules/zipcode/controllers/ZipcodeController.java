package com.rogertech.infobrasil.modules.zipcode.controllers;

import com.rogertech.infobrasil.modules.zipcode.services.ZipcodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/zipcode/v1/")
public class ZipcodeController {

    private final ZipcodeService zipcodeService;

    @GetMapping(value="/search/{zipcode}")
    public ResponseEntity<?> getZipcode(@PathVariable String zipcode) {
        return ResponseEntity.status(HttpStatus.OK).body(zipcodeService.getAddress(zipcode));
    }

}
