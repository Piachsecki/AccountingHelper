package com.piasecki.domain;

import lombok.Getter;

@Getter
public enum BusinessActivity {
    SOFTWARE_DEVELOPER(0.14),
    LAWYER(0.17),
    DOCTOR(0.17),
    MEDIC_SERVICES(0.12),
    NON_MATERIAL_SERVICE(0.15),
    SELLER(0.3),
    HAND_MADE_SERVICE(0.5);

    private final double percentage;

    BusinessActivity(double percentage) {
        this.percentage = percentage;
    }

}
