package com.treinamento.EcommerceBackend.entities.enums;

public enum StatusPaymentEnum {
    PENDING(1, "Pending"),
    SETTLED(2, "Settled"),
    CANCELED(3, "Cancelled");

    private int number;
    private String description;

    StatusPaymentEnum(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

    public static StatusPaymentEnum toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (StatusPaymentEnum x : StatusPaymentEnum.values()) {
            if (codigo.equals(x.getNumber())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Payment Status "+ codigo +" not found!");
    }
}
