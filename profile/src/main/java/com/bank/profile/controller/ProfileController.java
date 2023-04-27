package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.ProfileService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * REST контроллер
 * доступ по /api/profile
 * помио базовых CRUD операций
 * обеспечивает поиск по ИНН или номеру телефона через /api/profile/n/{number}
 * номер метефона указывается в формате 9991234567, без +7
 */
@RestController
@RequestMapping //("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * /api/profile
     * получение списка всех профилей, метод GET
     */
    @Timed("gettingAllProfiles")
    @GetMapping
    public ResponseEntity<List<ProfileDto>> findAll() {
        final List<ProfileDto> profileDtoList = profileService.findAll();
        return profileDtoList == null || profileDtoList.isEmpty() ?
                ResponseEntity
                        .noContent()
                        .build() :
                ResponseEntity
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(profileDtoList);
    }

    /**
     * /api/profile/{id}
     * поиск профиля по id, метод GET
     */
    @Timed("getProfileById")
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> findOne(@PathVariable Long id) {
        final ProfileDto profileDto = profileService.findOne(id);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDto);
    }

    /**
     * /api/profile/{id}
     * поиск профиля по ИНН или номеру телефона, метод GET
     */
    @Timed("getProfileByInnOrPhoneNumber")
    @GetMapping("/n/{n}")
    public ResponseEntity<ProfileDto> findByInnOrPhoneNumber(@PathVariable Long n) {
        final ProfileDto profileDto = profileService.findByInnOrPhoneNumber(n);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDto);
    }

    /**
     * /api/profile/{id}
     * удаление профиля по id, метод DELETE
     */
    @Timed("deleteProfile")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        profileService.delete(id);
        return ResponseEntity
                .ok()
                .build();
    }

    /**
     * создание профиля, метод POST
     * форма JSON:
     * {
     * "phoneNumber": 9991234567,                   - not null
     * "email": null,
     * "nameOnCard": null,
     * "inn": null,                                 - unique
     * "snils": null,                               - unique
     * "passport": {                                - not null
     * "series": 1234,                          - not null
     * "number": 123456,                        - not null
     * "lastName": "Фамилия",                   - not null
     * "firstName": "Имя",                      - not null
     * "middleName": null,
     * "gender": "Ж",                           - not null
     * "birthDate": "1997-04-01",               - not null
     * "birthPlace": "Сыктывкар",               - not null
     * "issuedBy": "МВД гор.Сыктывкара",        - not null
     * "dateOfIssue": "2012-11-21",             - not null
     * "divisionCode": 901347,                  - not null
     * "expirationDate": null,
     * "registration": {                        - not null
     * "country": "Российская Федерация",   - not null
     * "region": null,
     * "city": null,
     * "district": null,
     * "locality": null,
     * "street": null,
     * "houseNumber": null,
     * "houseBlock": null,
     * "flatNumber": null,
     * "index": 123456                      - not null
     * }
     * },
     * "actualRegistration": {
     * "country": "Российская Федерация",       - not null
     * "region": null,
     * "city": null,
     * "district": null,
     * "locality": null,
     * "street": null,
     * "houseNumber": null,
     * "houseBlock": null,
     * "flatNumber": null,
     * "index": 123456                          - not null
     * },
     * "accountDetailsSet": null
     * }
     */
    @Timed("createProfile")
    @PostMapping
    public ResponseEntity<ProfileDto> create(@Valid @RequestBody ProfileDto profileDto) {
        final ProfileDto profileDtoLocal = profileService.create(profileDto);
        return ResponseEntity
                .created(UriComponentsBuilder
                        .fromPath("/api/profile/{id}")
                        .build(Map.of("id", profileDtoLocal.getId())))
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDtoLocal);
    }

    /**
     * /api/profile/{id}
     * обновление данных профиля, метод PATCH
     * данные по банковским счетам добавляются в виде списка
     * "accountDetailsSet": [
     * {
     * "id": 1
     * }
     * ]
     */
    @Timed("updateProfile")
    @PatchMapping
    public ResponseEntity<ProfileDto> update(@Valid @RequestBody ProfileDto profileDto) {
        final ProfileDto profileDtoLocal = profileService.update(profileDto);
        return ResponseEntity
                .accepted()
                .contentType(MediaType.APPLICATION_JSON)
                .body(profileDtoLocal);
    }
}
