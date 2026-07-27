package dev.caiovitor.eventticketing.entity;

import dev.caiovitor.eventticketing.enums.BrazilianState;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Address {

    @Column(name = "cep", nullable = false,length = 8)
    private String cep;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "complement")
    private String complement;

    @Column(name = "neighborhood", nullable = false)
    private String neighborhood;

    @Column(name = "city", nullable = false)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false,length = 30)
    private BrazilianState state;
}
