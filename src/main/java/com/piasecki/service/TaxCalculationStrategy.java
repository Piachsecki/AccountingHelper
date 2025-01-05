package com.piasecki.service;

import com.piasecki.domain.Entrepreneurship;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface TaxCalculationStrategy {
    BigDecimal calculateIncomeTax();
}


/*
Tax calculation strategy i ona by miala taka metode jak calculateTax,
I w zaleznosci od



Tam gdzie liczymy ten podatek to tam powinien byc serwis przeliczenia tego podatku wlasnie, ktora wezmie te 2 parametry - amount i entreprenuership i
w zaleznosci od tych parametrow zwroci odpowiednia strategie

i potem 3 implementacje tego interface

spring sie wyjebie


TAM JUZ TWARDO DEFINIOWAC
ZE np. liniowka to 17%


 */


//if (this.rozliczenia = "asdasd")