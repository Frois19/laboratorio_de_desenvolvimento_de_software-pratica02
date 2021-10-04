package br.com.springboot.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateAgentDTO {
    private String login;
    private String password;
    private String name;
}
