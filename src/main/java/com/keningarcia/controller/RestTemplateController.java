package com.keningarcia.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.keningarcia.dto.CategoryDTO;
import com.keningarcia.dto.GenericResponse;
import com.keningarcia.security.JwtRequest;
import com.keningarcia.security.JwtResponse;
import com.sun.net.httpserver.HttpsServer;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.swing.text.html.parser.Entity;
import java.util.*;

//Consume apis externos
@RestController
@RequestMapping("/rest")
@RequiredArgsConstructor
public class RestTemplateController {

    private final RestTemplate restTemplate;

    @GetMapping("/pokemon/{name}")
    public ResponseEntity<?> getPokemon(@PathVariable("name") String name) {
        String pokemonUrl = "https://pokeapi.co/api/v2/pokemon/" + name;
        String response = restTemplate.getForEntity(pokemonUrl, String.class).getBody();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/test1")
    public ResponseEntity<List<CategoryDTO>> test1() {
        String url = "http://localhost:8080/categories";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ParameterizedTypeReference<List<CategoryDTO>> typeRef = new ParameterizedTypeReference<List<CategoryDTO>>() {};

        return restTemplate.exchange(url, HttpMethod.GET, entity, typeRef);
    }

    @GetMapping("/test2")
    public ResponseEntity<?> test2(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size

    ) {
        String url = "http://localhost:8080/categories/pagination2?=" + page + "&s=" + size;

        ResponseEntity<?> response = restTemplate.getForEntity(url, String.class);

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/test3")
    public ResponseEntity<?> test3(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size

    ) {
        String url = "http://localhost:8080/categories/pagination2?={page}&s={size}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("page", page);
        uriVariables.put("size", size);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity requestEntity = new HttpEntity(httpHeaders);

        return restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class, uriVariables);
    }

    @PostMapping("/test4")
    public ResponseEntity<GenericResponse<CategoryDTO>> test4(@RequestBody CategoryDTO categoryDTO) {
        String url = "http://localhost:8080/categories";

        HttpEntity<CategoryDTO> request = new HttpEntity<>(categoryDTO);
        GenericResponse<CategoryDTO> dto = restTemplate.postForObject(url, request, GenericResponse.class);

        //GenericResponse<CategoryDTO> genericResponse = new GenericResponse<>(201, "success", Arrays.asList(dto));

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping("/test5/{id}")
    public ResponseEntity<CategoryDTO> test5(@RequestBody CategoryDTO categoryDTO, @PathVariable("id") Integer id) {
        String url = "http://localhost:8080/categories/" + id;

        HttpEntity<CategoryDTO> request = new HttpEntity<>(categoryDTO);

        return restTemplate.exchange(url, HttpMethod.PUT, request, CategoryDTO.class, id);
    }

    @DeleteMapping("/test6/{id}")
    public ResponseEntity<CategoryDTO> test6(@PathVariable("id") Integer id) {
        String url = "http://localhost:8080/categories/" + id;
        restTemplate.delete(url);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/test7/{id}")
    public ResponseEntity<CategoryDTO> test7(@PathVariable("id") Integer id) {
        String url = "http://localhost:8080/categories/{idCategory}";

        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("idCategory", id);

        restTemplate.delete(url, uriVariables);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/test8/{id}")
    public ResponseEntity<Void> test8(@PathVariable("id") Integer id, @RequestBody JwtRequest request) throws Exception{
        //generateToken("kenin", "123");
        String token = generateToken(request.getUsername(), request.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.set("ContentType" ,MediaType.APPLICATION_JSON_VALUE);
        headers.set("Accept" ,MediaType.APPLICATION_JSON_VALUE);
        headers.set("Authorization","Bearer" + token);

        HttpEntity<String> jwtEntity = new HttpEntity<>(headers);

        String url = "http://localhost:8080/categories/{idCategory}";
        Map<String, Integer> uriVariables = new HashMap<>();
        uriVariables.put("idCategory", id);

        restTemplate.exchange(url, HttpMethod.DELETE, jwtEntity, String.class, uriVariables);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //generando el token
    private String generateToken(String username, String password) throws Exception {
        final String AUTHENTICATION_URL = "http://localhost:8080/login";
        JwtRequest userRequest = new JwtRequest(username,password);
        String userRequestJson = new ObjectMapper().writeValueAsString(userRequest);

        HttpHeaders authHeaders = new HttpHeaders();
        authHeaders.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        authHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<String> authEntity = new HttpEntity<>(userRequestJson, authHeaders);
        ResponseEntity<JwtResponse> authResponse = restTemplate.exchange(AUTHENTICATION_URL, HttpMethod.POST, authEntity, JwtResponse.class);
        String token = authResponse.getBody().jwtToken();

        System.out.println(token);
        return "Bearer " + token;
    }
}
