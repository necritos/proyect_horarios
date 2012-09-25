package models;

import play.db.jpa.GenericModel;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends GenericModel{

    @Id
    @GeneratedValue
    @Column(name = "use_iId")
    public Integer use_iId;

    @Column(name = "use_username")
    String use_username;

    @Column(name = "use_password")
    String use_password;

    public User(String username, String password){
        this.use_username = username;
        this.use_password = password;
    }

    public User(){
        this.use_username = "";
        this.use_password = "";
    }

}
