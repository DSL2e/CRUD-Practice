package com.example.webproject.repository;

import com.example.webproject.domain.Charger;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ChargerRepository {

    private final EntityManager em;

    public void save(Charger charger){
        if (charger.getId() == null){
            em.persist(charger);
        } else {
            em.merge(charger);
        }
    }

    public List<Charger> findAll(){
        return em.createQuery("select c from Charger c",Charger.class).getResultList();
    }

    public Charger findOne(Long id) {
           return em.find(Charger.class, id);
    }
}

