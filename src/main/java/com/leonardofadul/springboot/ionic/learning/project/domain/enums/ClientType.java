package com.leonardofadul.springboot.ionic.learning.project.domain.enums;

public enum ClientType {

    NATURALPERSON(1, "Natural Person"),
    LEGALPERSON(2, "Legal Person");

    private int cod;
    private String type;

    private ClientType(int cod, String type){
        this.cod = cod;
        this.type = type;
    }

    public int getCod() {
        return cod;
    }

    public String getType() {
        return type;
    }

    public static ClientType toEnum(Integer cod){
        if(cod == null){
            return null;
        }

        for(ClientType clientType : ClientType.values()){
            if(cod.equals(clientType.getCod())){
                return clientType;
            }
        }

        throw new IllegalArgumentException("Invalid Id: " + cod);
    }
}
