package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.AtmMapper;
import com.bank.publicinfo.repository.AtmRepository;
import com.bank.publicinfo.repository.BranchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
public class AtmServiceImpl implements AtmService{

    private final AtmRepository atmRepository;
    private final AtmMapper atmMapper;

    private final BranchRepository branchRepository;

    public AtmServiceImpl(AtmRepository atmRepository, AtmMapper atmMapper, BranchRepository branchRepository) {
        this.atmRepository = atmRepository;
        this.atmMapper = atmMapper;
        this.branchRepository = branchRepository;
    }


    @Override
    public AtmDto getAtmById(BigInteger id) {
        Atm atm = atmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ATM not found"));
        return atmMapper.atmToDto(atm);
    }

    @Override
    public List<AtmDto> getAtms() {
        List<Atm> atmList = atmRepository.findAll();
        return atmMapper.atmListToDtoList(atmList);
    }

    @Override
    public AtmDto createAtm(AtmDto atmDto) {
        Atm atm = atmMapper.atmToEntity(atmDto);
        BigInteger branchId = atmDto.branchDto().id();
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        atm.setBranch(branch);
        atm = atmRepository.save(atm);
        return atmMapper.atmToDto(atm);
    }

    @Override
    public AtmDto updateAtm(BigInteger id, AtmDto atmDto) {
        Atm atm = atmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ATM not found"));
        atmMapper.update(atmDto, atm);
        BigInteger branchId = atmDto.branchDto().id();
        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        atm.setBranch(branch);
        atm = atmRepository.save(atm);
        return atmMapper.atmToDto(atm);
    }

    @Override
    public void deleteAtmById(BigInteger id) {
        Atm atm = atmRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ATM not found"));
        atmRepository.delete(atm);
    }
}