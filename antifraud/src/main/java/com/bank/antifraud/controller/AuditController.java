package com.bank.antifraud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("audit")
public class AuditController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SuspiciousAccountTransfersController.class);
    private final JdbcTemplate jdbcTemplate;

    public AuditController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("all")
    public ResponseEntity<List<Map<String, Object>>> findAll() {
        List<Map<String, Object>> data = jdbcTemplate.queryForList("SELECT * FROM anti_fraud.audit");

        return ResponseEntity.ok(data);
    }
}