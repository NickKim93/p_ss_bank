package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService{

    private final BranchMapper branchMapper;
    private final BranchRepository branchRepository;

    public BranchServiceImpl(BranchMapper branchMapper, BranchRepository branchRepository) {
        this.branchMapper = branchMapper;
        this.branchRepository = branchRepository;
    }

    @Override
    public BranchDto getBranchById(BigInteger id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        branch.setAtmList(branch.getAtmList());
        return branchMapper.branchToDto(branch);
    }

    @Override
    public List<BranchDto> getAllBranches() {
        List<Branch> brancheList = branchRepository.findAll();
        return branchMapper.branchListToDto(brancheList);
    }

    @Override
    public BranchDto createBranch(BranchDto branchDto) {
        Branch branch = branchMapper.branchToEntity(branchDto);
        branch = branchRepository.save(branch);
        return branchMapper.branchToDto(branch);
    }

    @Override
    public BranchDto updateBranch(BigInteger id, BranchDto branchDto) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("branch not found"));
        branchMapper.updateEntityFromDto(branchDto, branch);
        branch = branchRepository.save(branch);
        return branchMapper.branchToDto(branch);
    }

    @Override
    public void deleteBranchById(BigInteger id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        branchRepository.delete(branch);
    }
}
