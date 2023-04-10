package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping
    public ResponseEntity<List<ProfileDto>> findAll() {
        List<ProfileDto> profileDtoList = profileService.findAll();
        return profileDtoList == null || profileDtoList.isEmpty() ?
            new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
            :
            new ResponseEntity<>(profileDtoList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> findOne(@PathVariable BigInteger id) {
        ProfileDto profileDto = profileService.findOne(id);
        return profileDto == null ?
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
            :
            new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @GetMapping("/n/{n}")
    public ResponseEntity<ProfileDto> findByInn(@PathVariable Long n) {
        ProfileDto profileDto = profileService.findByInnOrPhoneNumber(n);
        return profileDto == null ?
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
            :
            new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable BigInteger id) {
        return profileService.delete(id) ?
            new ResponseEntity<>(null, HttpStatus.OK)
            :
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> create(@RequestBody ProfileDto profileDto) {
        ProfileDto profileDtoLocal = profileService.create(profileDto);
        return profileDtoLocal == null ?
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<>(profileDtoLocal, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<ProfileDto> update(@RequestBody ProfileDto profileDto) {
        ProfileDto profileDtoLocal = profileService.update(profileDto);
        return profileDtoLocal == null ?
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<>(profileDtoLocal, HttpStatus.OK);
    }

}
