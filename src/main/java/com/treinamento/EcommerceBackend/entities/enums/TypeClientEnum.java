package com.treinamento.EcommerceBackend.entities.enums;


public enum TypeClientEnum {
    PESSOA_FISICA(1, "Pessoa Fisica"),
    PESSOA_JURIDICA(2, "Pessoa Juridica");

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
        throw new IllegalArgumentException("Tipo de Pessoa Não Registrada");
    }
}
