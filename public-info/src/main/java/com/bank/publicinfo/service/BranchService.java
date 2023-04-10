package com.bank.publicinfo.service;


import com.bank.publicinfo.dto.BranchDto;

import java.math.BigInteger;
import java.util.List;

public interface BranchService {

    public BranchDto getBranchById(BigInteger id);

    public List<BranchDto> getAllBranches ();

    public BranchDto createBranch(BranchDto branchDto);

    public BranchDto updateBranch (BigInteger id, BranchDto branchDto);

    public void deleteBranchById(BigInteger id);
}
