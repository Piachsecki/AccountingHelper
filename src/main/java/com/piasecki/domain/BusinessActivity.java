package com.piasecki.domain;

import lombok.Getter;

@Getter
public enum BusinessActivity {
    SOFTWARE_DEVELOPER(14),
    LAWYER(17),
    DOCTOR(17),
    MEDIC_SERVICES(12),
    NON_MATERIAL_SERVICE(15),
    SELLER(3),
    HAND_MADE_SERVICE(5);

    private final double percentage;

    BusinessActivity(double percentage) {
        this.percentage = percentage;
    }

}
