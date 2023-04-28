package com.example.exception.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import static jakarta.servlet.RequestDispatcher.ERROR_EXCEPTION;

@Controller
@Slf4j
public class ErrorPageController {
    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage404");
        return "error-page/500";
    }

    @RequestMapping(value = "/error-age/500",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> errorPage500Api(HttpServletRequest request, HttpServletResponse response){
        log.info("API errorPage 500");
        Map<String,Object> result = new HashMap<>();
        Exception attribute = (Exception) request.getAttribute(ERROR_EXCEPTION);
        result.put("status", request.getAttribute(ERROR_EXCEPTION));
        result.put("message", attribute.getMessage());

        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        return new ResponseEntity<>(result, HttpStatus.valueOf(statusCode));
    }

}
