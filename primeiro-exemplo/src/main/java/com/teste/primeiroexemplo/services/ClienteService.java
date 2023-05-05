package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Cliente;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiroexemplo.repository.ClienteRepository;
import com.teste.primeiroexemplo.shared.ClienteDTO;


@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

     /**
     * Metodo para retornar uma lista de clientes
     * @return Lista de clientes
     */
    public List<ClienteDTO> obterTodos(){
        
        // Retorna uma lista de cliente model
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
        .map(cliente -> new ModelMapper().map(cliente, ClienteDTO.class))
        .collect(Collectors.toList());
    }

    /**
    * Metodo que retorna o cliente encontrado pelo seu id.
    * @param id do cliente que será localizado.
    * @return Retorna um cliente caso seja encontrado.
    */
    public Optional<ClienteDTO> obterPorId(Integer id){
        // Obtendo Optional de cliente pelo id.
        Optional<Cliente> cliente = clienteRepository.findById(id);
        
        // Se não encontrar, lança exception.
        if(cliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente com id: "+ id + " não encontrado");
        }

        // Convertendo o meu Optional de cliente em um ClienteDTO.
        ClienteDTO dto = new ModelMapper().map(cliente.get(), ClienteDTO.class);
        
        //Criando e retornando um Optional de ClienteDTO.
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar cliente na lista.
     * @param cliente que será adicionado.
     * @return Retorna o cliente que foi adicionado na lista.
     */
    public ClienteDTO adicionar(ClienteDTO clienteDto){
        // Removendo o id para conseguir fazer o cadastro.
        clienteDto.setId(null);

        // Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        // Converter o nosso ClienteDTO em um Cliente.
        Cliente cliente = mapper.map(clienteDto, Cliente.class);
        
        // Salvar o Cliente do banco.
        cliente = clienteRepository.save(cliente);
        
        clienteDto.setId(cliente.getId());

        // Retornar o ClienteDTO atualizado.
        return clienteDto;      
    }

    /**
     * Metodo para deletar o cliente por id.
     * @param id do cliente a ser deletado.
     */
    public void deletar(Integer id){
        // Verificar se o cliente existe.
        Optional<Cliente> cliente = clienteRepository.findById(id);

        // Se não existir lança uma exception 
        if(cliente.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o cliente com o id: "+ id +" - Cliente não existe");
        }

        // Deleta o cliente pelo id.
       clienteRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar o cliente na lista
     * @param cliente que será atualizado.
     * @param id do cliente.
     * @return Retorna o cliente após atualizar a lista.
     */
    public ClienteDTO atualizar(Integer id, ClienteDTO clienteDto){
        
        // Passar o id para o clienteDto.
        clienteDto.setId(id);

        // Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        // Converter o ClienteDTO em um Cliente.
        Cliente cliente = mapper.map(clienteDto, Cliente.class);

        // Atualizar o cliente no Banco de dados.
        clienteRepository.save(cliente);

        // Retornar o clienteDto atualizado.
        return clienteDto;        
    }
    
}

