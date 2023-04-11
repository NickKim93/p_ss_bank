package com.bank.profile.mapper;

import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Методы преобразуют Entity в DTO и обратно
 * */
@Mapper
public interface ProfileMapper {
    ProfileMapper INSTANCE = Mappers.getMapper(ProfileMapper.class);
    ProfileDto profileToProfileDto(Profile profile);
    Profile profileDtoToProfile(ProfileDto profileDto);
}
