package com.bank.account.aspect;

import com.bank.account.aspect.Auditable;
import com.bank.account.aspect.AuditingAspect;
import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.entity.AccountDetails;
import com.bank.account.entity.Audit;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.AuditRepository;
import com.bank.account.util.AuditingActionType;
import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AuditingAspectTest {
    @Mock
    private AuditRepository auditRepository;
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private Auditable auditable = Mockito.mock(Auditable.class);
    @Mock
    private JoinPoint joinPoint = Mockito.mock(JoinPoint.class);

    @InjectMocks
    private AuditingAspect auditAspect;
    Audit audit = new Audit(1L, "entity_type", "operation_type", "by_me", "by_me_too", Timestamp.valueOf(LocalDateTime.now()),
            Timestamp.valueOf(LocalDateTime.now()), "new_entity", "entity");

    AccountDetailsDTO detailsDTO = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);
    AccountDetails accountDetails = new AccountDetails(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);

    @Test
    public void shouldCreteOptionAudit() {
        when(auditable.actionType()).thenReturn(AuditingActionType.CREATE);
        when(joinPoint.getArgs()).thenReturn(new Object[]{detailsDTO});
        auditAspect.logAuditActivity(joinPoint, auditable);
        verify(auditRepository, Mockito.times(1)).save(Mockito.any());
    }


    @Test
    public void shouldUpdateOptionAudit() {
        when(auditable.actionType()).thenReturn(AuditingActionType.UPDATE);
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));
        when(joinPoint.getArgs()).thenReturn(new Object[]{detailsDTO});
        when(accountRepository.findAccountDetailsByAccountNumber(1L)).thenReturn(Optional.of(accountDetails));
        auditAspect.logAuditActivity(joinPoint, auditable);
        verify(auditRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void shouldDeleteOptionAudit(){
        when(auditable.actionType()).thenReturn(AuditingActionType.DELETE);
        when(joinPoint.getArgs()).thenReturn(new Object[]{1L});
        when(auditRepository.findById(1L)).thenReturn(Optional.of(audit));
        auditAspect.logAuditActivity(joinPoint, auditable);
        verify(auditRepository, Mockito.times(1)).save(Mockito.any());
    }
}
