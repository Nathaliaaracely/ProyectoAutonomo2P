package com.bancodealimentos.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {
    @QueryMapping
    public String hello() {
        return "¡Hola desde GraphQL!";
    }
}