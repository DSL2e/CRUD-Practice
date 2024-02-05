package com.example.webproject.service;
import com.example.webproject.domain.Charger;
import com.example.webproject.repository.ChargerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChargerService {

    private final ChargerRepository chargerRepository;
    @Transactional
    public void saveCharger(Charger charger) {
        chargerRepository.save(charger);
    }

    public List<Charger> findItems() {
        return chargerRepository.findAll();
    }

    public Charger findOne(Long itemId) {
        return chargerRepository.findOne(itemId);
    }
}
