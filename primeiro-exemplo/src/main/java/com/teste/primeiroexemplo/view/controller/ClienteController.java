package com.teste.primeiroexemplo.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.primeiroexemplo.services.ClienteService;
import com.teste.primeiroexemplo.shared.ClienteDTO;
import com.teste.primeiroexemplo.view.model.ClienteRequest;
import com.teste.primeiroexemplo.view.model.ClienteResponse;




@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> obterTodos(){
        List<ClienteDTO> clientes = clienteService.obterTodos();

        ModelMapper mapper = new ModelMapper();

        List<ClienteResponse> resposta = clientes.stream()
        .map(clienteDto -> mapper.map(clienteDto, ClienteResponse.class))
        .collect(Collectors.toList());

        return new ResponseEntity<>(resposta, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ClienteResponse>> obterPorId(@PathVariable Integer id){
        
    //   try {
        Optional<ClienteDTO> dto = clienteService.obterPorId(id);
        
        ClienteResponse cliente = new ModelMapper().map(dto.get(), ClienteResponse.class);
        
        return new ResponseEntity<>(Optional.of(cliente), HttpStatus.OK);

   //    } catch (Exception e) {
   //        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   //    }
       
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> adicionar(@RequestBody ClienteRequest clienteReq){
        ModelMapper mapper = new ModelMapper();

        ClienteDTO clienteDto = mapper.map(clienteReq, ClienteDTO.class);

        clienteDto = clienteService.adicionar(clienteDto);
        
        return new ResponseEntity<>(mapper.map(clienteDto, ClienteResponse.class), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Integer id){
        clienteService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);    
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> atualizar(@RequestBody ClienteRequest clienteReq, @PathVariable Integer id){
        ModelMapper mapper = new ModelMapper();
        ClienteDTO clienteDto = mapper.map(clienteReq, ClienteDTO.class);

        clienteDto = clienteService.atualizar(id, clienteDto);

        return new ResponseEntity<>(
            mapper.map(clienteDto, ClienteResponse.class),
            HttpStatus.OK);
    }
    
}
