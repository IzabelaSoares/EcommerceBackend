package com.treinamento.EcommerceBackend.entities.enums;

public enum StatusPaymentEnum {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

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
        throw new IllegalArgumentException("Tipo de Status de Pagamento Não Registrado");
    }
}
