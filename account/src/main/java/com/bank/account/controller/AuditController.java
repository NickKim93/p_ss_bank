package com.bank.account.controller;

import com.bank.account.entity.Audit;
import com.bank.account.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/audit")
@RequiredArgsConstructor
public class AuditController {
    private final AuditService service;
    @GetMapping("/{id}")
    Audit getAudit(@PathVariable Long id) {
        return service.getAudit(id);
    }
    @GetMapping
    List<Audit> getAllAudits() {
        return service.getAllAudits();
    }

}
