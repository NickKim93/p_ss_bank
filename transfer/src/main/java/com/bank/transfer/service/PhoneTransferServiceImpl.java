package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
/**
 * Бизнес-логика приложения для Phone Transfer Entity
 * @author Savenkov Artem
 */
@Service
public class PhoneTransferServiceImpl implements PhoneTransferService {

    private final PhoneTransferRepository phoneTransferRepository;

    public PhoneTransferServiceImpl(PhoneTransferRepository phoneTransferRepository) {
        this.phoneTransferRepository = phoneTransferRepository;
    }

    @Override
    public List<PhoneTransferEntity> getAll() {
        return phoneTransferRepository.findAll();
    }

    @Override
    public PhoneTransferEntity getById(BigInteger id) {
        Optional<PhoneTransferEntity> optional = phoneTransferRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new EntityNotFoundException("Phone Transfer Entity с id: " + id + " не найден");
        }
    }

    @Override
    public PhoneTransferEntity saveOrUpdate(PhoneTransferDto phoneTransferDto) {
        PhoneTransferEntity phoneTransferEntity = PhoneTransferMapper.getPhoneTransferMapper.
                dtoToEntityPhoneTransfer(phoneTransferDto);
        return phoneTransferRepository.save(phoneTransferEntity);
    }

    @Override
    public void delete(BigInteger id) {
        PhoneTransferEntity phoneTransferEntity = phoneTransferRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException(id.toString()));
        phoneTransferRepository.delete(phoneTransferEntity);
    }
}
