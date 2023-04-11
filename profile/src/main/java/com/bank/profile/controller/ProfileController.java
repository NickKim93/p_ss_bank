package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
/**
 * REST контроллер
 * доступ по /api/profile
 * помио базовых CRUD операций
 * обеспечивает поиск по ИНН или номеру телефона через /api/profile/n/{number}
 * номер метефона указывается в формате 9991234567, без +7
 */
@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /**
     * /api/profile
     * получение списка всех профилей, метод GET
     * */
    @GetMapping
    public ResponseEntity<List<ProfileDto>> findAll() {
        List<ProfileDto> profileDtoList = profileService.findAll();
        return profileDtoList == null || profileDtoList.isEmpty() ?
            new ResponseEntity<>(null, HttpStatus.NO_CONTENT)
            :
            new ResponseEntity<>(profileDtoList, HttpStatus.OK);
    }

    /**
     * /api/profile/{id}
     * поиск профиля по id, метод GET
     * */
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> findOne(@PathVariable Long id) {
        ProfileDto profileDto = profileService.findOne(id);
        return profileDto == null ?
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
            :
            new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    /**
     * /api/profile/{id}
     * поиск профиля по ИНН или номеру телефона, метод GET
     * */
    @GetMapping("/n/{n}")
    public ResponseEntity<ProfileDto> findByInnOrPhoneNumber(@PathVariable Long n) {
        ProfileDto profileDto = profileService.findByInnOrPhoneNumber(n);
        return profileDto == null ?
            new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
            :
            new ResponseEntity<>(profileDto, HttpStatus.OK);
    }

    /**
     * /api/profile/{id}
     * удаление профиля по id, метод DELETE
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /**
     * создание профиля, метод POST
     * форма JSON:
     * {
     *     "phoneNumber": 9991234567,                   - not null
     *     "email": null,
     *     "nameOnCard": null,
     *     "inn": null,                                 - unique
     *     "snils": null,                               - unique
     *     "passport": {                                - not null
     *         "series": 1234,                          - not null
     *         "number": 123456,                        - not null
     *         "lastName": "Фамилия",                   - not null
     *         "firstName": "Имя",                      - not null
     *         "middleName": null,
     *         "gender": "Ж",                           - not null
     *         "birthDate": "1997-04-01",               - not null
     *         "birthPlace": "Сыктывкар",               - not null
     *         "issuedBy": "МВД гор.Сыктывкара",        - not null
     *         "dateOfIssue": "2012-11-21",             - not null
     *         "divisionCode": 901347,                  - not null
     *         "expirationDate": null,
     *         "registration": {                        - not null
     *             "country": "Российская Федерация",   - not null
     *             "region": null,
     *             "city": null,
     *             "district": null,
     *             "locality": null,
     *             "street": null,
     *             "houseNumber": null,
     *             "houseBlock": null,
     *             "flatNumber": null,
     *             "index": 123456                      - not null
     *         }
     *     },
     *     "actualRegistration": {
     *         "country": "Российская Федерация",       - not null
     *         "region": null,
     *         "city": null,
     *         "district": null,
     *         "locality": null,
     *         "street": null,
     *         "houseNumber": null,
     *         "houseBlock": null,
     *         "flatNumber": null,
     *         "index": 123456                          - not null
     *     },
     *     "accountDetailsSet": null
     * }
     * */
    @PostMapping
    public ResponseEntity<ProfileDto> create(@Valid @RequestBody ProfileDto profileDto) {
        ProfileDto profileDtoLocal = profileService.create(profileDto);
        return profileDtoLocal == null ?
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<>(profileDtoLocal, HttpStatus.OK);
    }

    /**
     * /api/profile/{id}
     * обновление данных профиля, метод PATCH
     * данные по банковским счетам добавляются в виде списка
     * "accountDetailsSet": [
     *          {
     *              "id": 1
     *          }
     * ]
     * */
    @PatchMapping
    public ResponseEntity<ProfileDto> update(@Valid @RequestBody ProfileDto profileDto) {
        ProfileDto profileDtoLocal = profileService.update(profileDto);
        return profileDtoLocal == null ?
                new ResponseEntity<>(null, HttpStatus.BAD_REQUEST)
                :
                new ResponseEntity<>(profileDtoLocal, HttpStatus.OK);
    }
}
