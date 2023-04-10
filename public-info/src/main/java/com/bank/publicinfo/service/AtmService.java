package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;

import java.math.BigInteger;
import java.util.List;

public interface AtmService {

    public AtmDto getAtmById(BigInteger id);

    public List<AtmDto> getAtms ();

    public AtmDto createAtm(AtmDto atmDto);

    public AtmDto updateAtm (BigInteger id, AtmDto atmDto);

    public void deleteAtmById(BigInteger id);
}


