package com.bank.publicinfo.service;

import com.bank.publicinfo.auditlistener.Auditable;
import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.BranchMapper;
import com.bank.publicinfo.repository.BranchRepository;
import io.micrometer.core.annotation.Timed;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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
    public BranchDto getBranchById(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        branch.setAtmList(branch.getAtmList());
        return branchMapper.branchToDto(branch);
    }

    @Override
    @Timed(value = "branch_service.findAll")
    public List<BranchDto> getAllBranches() {
        List<Branch> branchList = branchRepository.findAll();
        return branchMapper.branchListToDto(branchList);
    }

    @Override
    @Auditable(operationType = "create")
    @Timed(value = "branch_service.create")
    public BranchDto createBranch(BranchDto branchDto) {
        Branch branch = branchMapper.branchToEntity(branchDto);
        branch = branchRepository.save(branch);
        return branchMapper.branchToDto(branch);
    }

    @Override
    @Auditable(operationType = "update")
    @Timed(value = "branch_service.update")
    public BranchDto updateBranch(Long id, BranchDto branchDto) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("branch not found"));
        branchMapper.updateEntityFromDto(branchDto, branch);
        branch = branchRepository.save(branch);
        return branchMapper.branchToDto(branch);
    }

    @Override
    @Auditable(operationType = "delete")
    @Timed(value = "branch_service.delete")
    public void deleteBranchById(Long id) {
        Branch branch = branchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Branch not found"));
        branchRepository.delete(branch);
    }
}
