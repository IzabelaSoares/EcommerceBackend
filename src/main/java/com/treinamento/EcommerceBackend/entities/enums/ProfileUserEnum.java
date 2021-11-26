package com.treinamento.EcommerceBackend.entities.enums;

public enum ProfileUserEnum {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT"),
    INACTIVE(3, "ROLE_INACTIVE");

    private int number;
    private String description;

    ProfileUserEnum(int number, String description) {
        this.number = number;
        this.description = description;
    }

    public static ProfileUserEnum toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }
        for (ProfileUserEnum x : ProfileUserEnum.values()) {
            if (codigo.equals(x.getNumber())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Profile type "+ codigo +" not found!");
    }

    public int getNumber() {
        return number;
    }

    public String getDescription() {
        return description;
    }

}
