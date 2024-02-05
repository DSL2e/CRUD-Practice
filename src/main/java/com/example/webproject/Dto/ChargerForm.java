package com.example.webproject.Dto;

import com.example.webproject.domain.Charger;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChargerForm {
    private Long id;
    private String Charger_type;
    private String title;
    private int price;
    private String location;
    boolean delete;

    public ChargerForm(){}

    public ChargerForm(Charger charger) {
        this.id = charger.getId();
        this.Charger_type = charger.getCharger_type();
        this.title = charger.getTitle();
        this.price = charger.getPrice();
        this.location = charger.getLocation();
        this.delete = charger.isDelete();
    }

}


