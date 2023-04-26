package com.bank.publicinfo.service;


import com.bank.publicinfo.dto.BranchDto;

import java.util.List;

public interface BranchService {

    BranchDto getBranchById(Long id);

    List<BranchDto> getAllBranches ();

    BranchDto createBranch(BranchDto branchDto);

    BranchDto updateBranch (Long id, BranchDto branchDto);

    void deleteBranchById(Long id);
}
