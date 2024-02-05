package com.example.webproject.domain;

import com.example.webproject.Dto.ChargerForm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Charger {
    @Id
    @GeneratedValue
    @Column(name = "charger_id")
    private Long id;

    private String title;
    private String Charger_type;
    private String location;
    private int price;
    boolean delete;

    public Charger() {}
    public Charger(ChargerForm form){
        this.title = form.getTitle();
        this.Charger_type = form.getCharger_type();
        this.location = form.getLocation();
        this.price = form.getPrice();
        this.delete = false;
    }

    public void updateCharger(ChargerForm form){
        if (form.getCharger_type() != null) {
            this.Charger_type = form.getCharger_type();
        }

        if (form.getTitle() != null) {
            this.title = form.getTitle();
        }

        if (form.getPrice() != 0) {
            this.price = form.getPrice();
        }

        if (form.getLocation() != null) {
            this.location = form.getLocation();
        }
    }
    public void updateDelStatus(boolean status){
        this.delete = status;
    }
}
