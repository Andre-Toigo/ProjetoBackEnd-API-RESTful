package com.teste.primeiroexemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiroexemplo.model.Produto;
import com.teste.primeiroexemplo.model.exception.ResourceNotFoundException;
import com.teste.primeiroexemplo.repository.ProdutoRepository;
import com.teste.primeiroexemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

     /**
     * Metodo para retornar uma lista de produtos
     * @return Lista de produtos
     */
    public List<ProdutoDTO> obterTodos(){
        
        // Retorna uma lista de produto model
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    }

    /**
    * Metodo que retorna o produto encontrado pelo seu id.
    * @param id do produto que será localizado.
    * @return Retorna um produto caso seja encontrado.
    */
    public Optional<ProdutoDTO> obterPorId(Integer id){
        // Obtendo Optional de produto pelo id.
        Optional<Produto> produto = produtoRepository.findById(id);
        
        // Se não encontrar, lança exception.
        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto com id: "+ id + " não encontrado");
        }

        // Convertendo o meu Optional de produto em um ProdutoDTO.
        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
        
        //Criando e retornando um Optional de ProdutoDTO.
        return Optional.of(dto);
    }

    /**
     * Metodo para adicionar produto na lista.
     * @param produto que será adicionado.
     * @return Retorna o produto que foi adicionado na lista.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        // Removendo o id para conseguir fazer o cadastro.
        produtoDto.setId(null);

        // Criar um objeto de apeamento.
        ModelMapper mapper = new ModelMapper();

        // Converter o nosso ProdutoDTO em um Produto.
        Produto produto = mapper.map(produtoDto, Produto.class);
        
        // Salvar o Produto do banco.
        produto = produtoRepository.save(produto);
        
        produtoDto.setId(produto.getId());

        // Retornar o ProdutoDTO atualizado.
        return produtoDto;      
    }

    /**
     * Metodo para deletar o produto por id.
     * @param id do produto a ser deletado.
     */
    public void deletar(Integer id){
        // Verificar se o produto existe.
        Optional<Produto> produto = produtoRepository.findById(id);

        // Se não existir lança uma exception 
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com o id: "+ id +" - Produto não existe");
        }

        // Deleta o produto pelo id.
       produtoRepository.deleteById(id);
    }

    /**
     * Metodo para atualizar o produto na lista
     * @param produto que será atualizado.
     * @param id do produto.
     * @return Retorna o produto após atualizar a lista.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        
        // Passar o id para o produtoDto.
        produtoDto.setId(id);

        // Criar um objeto de mapeamento.
        ModelMapper mapper = new ModelMapper();

        // Converter o ProdutoDTO em um Produto.
        Produto produto = mapper.map(produtoDto, Produto.class);

        // Atualizar o produto no Banco de dados.
        produtoRepository.save(produto);

        // Retornar o produtoDto atualizado.
        return produtoDto;        
    }
    
}


