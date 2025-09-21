package xyz.inhalt.springcloak.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class User {
    private String id;
    private String username;
    private String email;
    private List<String> realmRoles;
    private List<String> clientRoles;
}
