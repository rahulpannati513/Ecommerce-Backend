package org.rahul.ecommercebackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "street_address")
    private  String street;

    @Column(name = "city")
    private  String city;

    @Column(name ="state")
    private String state;

    @Column(name = "zip_code")
    private  String zip;




    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private User user;



    public Address(){

    }

    public Address(Long id, String street, String city, String state, String zip, User user) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.user = user;
    }
}
