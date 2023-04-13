package com.bank.publicinfo.controller;


import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.service.AtmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/atms")
public class AtmController {

    private final AtmService atmService;

    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    public ResponseEntity<List<AtmDto>> getAllAtms() {
        List<AtmDto> atmDtoList = atmService.getAtms();
        return ResponseEntity.ok(atmDtoList);
    }

    @PostMapping()
    public ResponseEntity<AtmDto> createAtm (@RequestBody AtmDto atmDto) {
        AtmDto createdDto = atmService.createAtm(atmDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtmDto> update(@PathVariable Long id, @RequestBody AtmDto atmDto) {
        AtmDto updatedDto = atmService.updateAtm(id, atmDto);
        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        atmService.deleteAtmById(id);
        return ResponseEntity.noContent().build();
    }
}
