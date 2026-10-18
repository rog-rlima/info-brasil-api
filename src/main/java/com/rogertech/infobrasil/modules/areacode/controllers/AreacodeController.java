package com.rogertech.infobrasil.modules.areacode.controllers;

import com.rogertech.infobrasil.modules.areacode.services.AreacodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/areacode/v1/")
public class AreacodeController {

    private final AreacodeService areacodeService;

    @GetMapping(value="/search/{ddd}")
    public ResponseEntity<?> getAreacode(@PathVariable String ddd) {
        return ResponseEntity.status(HttpStatus.OK).body(areacodeService.getAreacode(ddd));
    }

}
