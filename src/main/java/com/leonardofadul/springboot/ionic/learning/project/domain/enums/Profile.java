package com.leonardofadul.springboot.ionic.learning.project.domain.enums;

public enum Profile {

    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int cod;
    private String type;

    private Profile(int cod, String type) {
        this.cod = cod;
        this.type = type;
    }

    public int getCod() {
        return cod;
    }

    public String getType() {
        return type;
    }

    public static Profile toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(Profile paymentState : Profile.values()){
            if(cod.equals(paymentState.getCod())){
                return paymentState;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + cod);
    }
}
