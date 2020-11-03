package com.cofeapp.cofeappgw.controller.advice;

import com.cofeapp.cofeappgw.MessageSourceTestConfiguration;
import com.cofeapp.cofeappgw.exception.CofeAppGwException;
import feign.FeignException;
import feign.Request;
import feign.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TestController.class)
@ContextConfiguration(classes = MessageSourceTestConfiguration.class)
public class GlobalControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void it_should_resolve_exception() throws Exception {
        //When
        ResultActions resultActions = mockMvc.perform(get("/generic-exception"));

        //Then
        resultActions
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.exception", is("java.lang.Exception")))
                .andExpect(jsonPath("$.errors[0]", is("Generic exception occurred.")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    public void it_should_resolve_cofeAppGwException() throws Exception {
        //When
        ResultActions resultActions = mockMvc.perform(get("/cofe-app-exception"));

        //Then
        resultActions
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.exception", is("CofeAppGwException")))
                .andExpect(jsonPath("$.errors[0]", is("CofeAppGwException occurred.")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    public void it_should_resolve_methodArgumentNotValidException() throws Exception {
        //when
        ResultActions resultActions = mockMvc.perform(get("/method-argument-not-valid-exception"));

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception", is("MethodArgumentNotValidException")))
                .andExpect(jsonPath("$.errors[0]", is("message.key")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    public void it_should_handle_bind_exception() throws Exception {
        // When
        ResultActions resultActions = mockMvc.perform(get("/bind-exception"));

        // Then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception", is("BindException")))
                .andExpect(jsonPath("$.errors[0]", is("message.key")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
    }

    @Test
    public void it_should_handle_feign_exception() throws Exception {
        //when
        ResultActions resultActions = mockMvc.perform(get("/feign-exception"));

        //then
        resultActions
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.exception", is("FeignException")))
                .andExpect(jsonPath("$.errors[0]", is("error-message")))
                .andExpect(jsonPath("$.timestamp", is(11111)));
    }
}


@RestController
class TestController {

    @GetMapping("/generic-exception")
    public void throwException() throws Exception {
        throw new Exception("Generic exception occurred.");
    }

    @GetMapping("/cofe-app-exception")
    public void throwCofeAppException() {
        throw new CofeAppGwException("message.key", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/method-argument-not-valid-exception")
    public void throwMethodArgumentNotValidException() throws MethodArgumentNotValidException {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("request", "fieldName", "message.key");
        given(bindingResult.getFieldErrors()).willReturn(Collections.singletonList(fieldError));
        throw new MethodArgumentNotValidException(mock(MethodParameter.class), bindingResult) {
            @Override
            public String getMessage() {
                return "method-argument-not-valid-exception";
            }
        };
    }

    @GetMapping("/bind-exception")
    public void throwBindException() throws BindException {
        BindingResult bindingResult = mock(BindingResult.class);
        FieldError fieldError = new FieldError("request", "fieldName", "message.key");
        given(bindingResult.getFieldErrors()).willReturn(Collections.singletonList(fieldError));
        throw new BindException(bindingResult);
    }

    @GetMapping("/feign-exception")
    public void throwFeignException() {
        String responseBody = "{\"exception\": \"FeignException\", \"timestamp\": 11111, \"errors\": [\"error-message\"]}";
        Request request = Request.create(Request.HttpMethod.GET,
                                         "http://www.hurriyetemlak.com",
                                         new HashMap<>(),
                                         new byte[]{1, 2, 3},
                                         StandardCharsets.UTF_8);
        Response response = Response.builder()
                .request(request)
                .status(400)
                .body(responseBody, StandardCharsets.UTF_8)
                .headers(new HashMap<>())
                .build();
        throw FeignException.errorStatus("methodKey", response);
    }
}