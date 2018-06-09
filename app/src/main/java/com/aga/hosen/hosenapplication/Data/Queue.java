package com.aga.hosen.hosenapplication.Data;

/**
 * Created by aodai on 09/06/2018.
 */

public class Queue {


    private String Phone;
    private String Name;
    private String Address;
    private String keyId;

    private String email;

    public Queue() {

    }

    public Queue(String phone, String name, String address) {
        Phone = phone;
        Name = name;
        Address = address;
        this.email = email;
    }

    public Queue(String phone, String name, String address, String keyId) {
        Phone = phone;
        Name = name;
        Address = address;
        this.keyId = keyId;

    }


    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getKeyId() {
        return keyId;
    }

    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
