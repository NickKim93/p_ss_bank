package com.bank.account.util;

public enum AuditingActionType {
    CREATE("CREATE"), UPDATE("UPDATE"),
    DELETE("DELETE");
    AuditingActionType(String operation) {
    }
}
