package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;

import java.math.BigInteger;
import java.util.List;

public interface AtmService {

    AtmDto getAtmById(Long id);

    List<AtmDto> getAtms ();

    AtmDto createAtm(AtmDto atmDto);

    AtmDto updateAtm (Long id, AtmDto atmDto);

    void deleteAtmById(Long id);
}


