package com.hr.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Scope("prototype")
public class User {
    private long id;
    private String name;
    private String reg_time;


    public void setName(String name){
        if(name.length()==0){
            this.name = "default_name";
        }else{
            this.name = name;
        }

    }
}
