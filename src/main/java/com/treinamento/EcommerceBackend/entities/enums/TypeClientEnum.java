package com.treinamento.EcommerceBackend.entities.enums;


public enum TypeClientEnum {
    PHYSICAL_PERSON(1, "Physical Person"),
    LEGAL_PERSON(2, "Legal Person");

    private int number;
    private String description;

    TypeClientEnum(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static TypeClientEnum toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (TypeClientEnum x : TypeClientEnum.values()) {
            if (codigo.equals(x.getNumber())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Person type "+ codigo +" not found!");
    }
}
