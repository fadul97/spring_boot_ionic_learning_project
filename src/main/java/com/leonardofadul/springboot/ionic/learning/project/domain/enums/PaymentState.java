package com.leonardofadul.springboot.ionic.learning.project.domain.enums;

public enum PaymentState {

    PENDING(1, "Pending"),
    COMPLETED(2, "Completed"),
    CANCELED(3, "Canceled");

    private int cod;
    private String type;

    private PaymentState(int cod, String type) {
        this.cod = cod;
        this.type = type;
    }

    public int getCod() {
        return cod;
    }

    public String getType() {
        return type;
    }

    public static PaymentState toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(PaymentState paymentState : PaymentState.values()){
            if(cod.equals(paymentState.getCod())){
                return paymentState;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + cod);
    }
}
