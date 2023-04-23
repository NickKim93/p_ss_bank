package com.bank.antifraud.service;

import com.bank.antifraud.aspect.Auditing;
import com.bank.antifraud.aspect.Type;
import com.bank.antifraud.dto.SuspiciousTransferDto;
import com.bank.antifraud.entity.SuspiciousTransfer;
import com.bank.antifraud.mapper.SuspiciousTransferMapper;
import com.bank.antifraud.repository.SuspiciousTransferRepository;
import com.bank.antifraud.util.OperationType;
import io.micrometer.core.annotation.Timed;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
public class SuspiciousTransferServiceImpl<T extends SuspiciousTransfer> implements SuspiciousTransferService<T> {
    private final SuspiciousTransferRepository<T> suspiciousTransferRepository;
    private final Class<T> clazz; // класс entity с которым работает реализация
    private final String TYPE; // для маппера

    public SuspiciousTransferServiceImpl(SuspiciousTransferRepository<T> suspiciousTransferRepository, Class<T> clazz) {
        this.suspiciousTransferRepository = suspiciousTransferRepository;
        this.clazz = clazz;
        TYPE = clazz.getAnnotation(Type.class).value();
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.CREATE)
    @Timed("service.save")
    public T save(SuspiciousTransferDto suspiciousTransferDto) {
        suspiciousTransferDto.setType(TYPE);
        SuspiciousTransfer suspiciousTransfer = SuspiciousTransferMapper.INSTANCE
                .suspiciousTransferDtoToSuspiciousTransfer(suspiciousTransferDto);

        return suspiciousTransferRepository.save(clazz.cast(suspiciousTransfer));
    }

    @Override
    @Timed(value = "service.findById", description = "метод поиска по id")
    public T findById(Long id) {
        Optional<T> suspiciousTransferOptional = suspiciousTransferRepository.findById(id);
        if (suspiciousTransferOptional.isPresent()) {
            return suspiciousTransferOptional.get();
        } else {
            throw new EntityNotFoundException("сущность SuspiciousTransfer с id: " + id + " не найдена.");
        }
    }

    @Override
    @Timed(value = "service.findAll", description = "метод поиска всех")
    public List<T> findAll() {
        return suspiciousTransferRepository.findAll();
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.DELETE)
    @Timed(value = "service.delete", description = "метод удаления")
    public void delete(Long id) {
        SuspiciousTransfer suspiciousTransfer = suspiciousTransferRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("сущность SuspiciousTransfer с id: " + id + " не найдена."));

        suspiciousTransferRepository.delete(clazz.cast(suspiciousTransfer));
    }

    @Override
    @Transactional
    @Auditing(operationType = OperationType.UPDATE)
    @Timed(value = "service.update", description = "метод обновления")
    public T update(SuspiciousTransferDto suspiciousTransferDto) {
        suspiciousTransferDto.setType(TYPE);
        SuspiciousTransfer updateEntity = suspiciousTransferRepository.findById(suspiciousTransferDto.getId())
                .orElseThrow(() -> new EntityNotFoundException(suspiciousTransferDto.getId().toString()));

        BeanUtils.copyProperties(suspiciousTransferDto, updateEntity);
        return suspiciousTransferRepository.save(clazz.cast(updateEntity));
    }
}
