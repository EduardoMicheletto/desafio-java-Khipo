package com.api.testeKhipo.controller;

import com.api.testeKhipo.config.JwtService;
import com.api.testeKhipo.model.requests.LoginRequest;
import com.api.testeKhipo.model.requests.ResgistroRequest;
import com.api.testeKhipo.service.AuthenticationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = LoginController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class LoginControllerTest {

    @MockBean
    private JwtService jwtService;
    @MockBean
    private AuthenticationService authenticationService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private ResgistroRequest resgistroRequest;
    private LoginRequest loginRequest;


    @BeforeEach
    public void init() {
        resgistroRequest = new ResgistroRequest("test","test@test.com","test","test");
        loginRequest = new LoginRequest("teste","teste");
    }


    @Test
    public void loginController_registrar_returnCreated() throws Exception{

        ResultActions response = mockMvc.perform(post("/api/v1/login/registrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(resgistroRequest)));

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
    @Test
    public void loginController_login_returnOK() throws Exception{

        ResultActions response = mockMvc.perform(post("/api/v1/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(loginRequest)));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

}
