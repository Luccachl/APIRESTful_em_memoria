package com.apirest.produto.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.produto.model.Produto;
import com.apirest.produto.model.exception.ResourceNotFoundException;
import com.apirest.produto.repository.ProdutoRepository;
import com.apirest.produto.shared.ProdutoDTO;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método que retorna uma lista de produtos
     * @return ista de produtos
     */
    public List<ProdutoDTO> obterTodos(){

        List<Produto> produtos = produtoRepository.findAll(); 
        
        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class))
        .collect(Collectors.toList());
    } 

     /**
     * Retorna o produto caso encontre o id e null caso não encontre
     * @param id do produto a ser localizado
     * @return Produto || null
     */
    public Optional <ProdutoDTO> obterPorId(Integer id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Produto com id: " + id + " não encontrado");
        }

        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class);
    
        return Optional.of(dto);
    }

    /**
     * Método para adc novo produto
     * @param produto a ser adicionado
     * @return Produto adicionado
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto){
        // tirar id para garantir inserção
        produtoDto.setId(null);

        //obj de mapeamento
        ModelMapper mapper = new ModelMapper();

        //converter produtoDTO em produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        // salvar o produto no banco
        produto = produtoRepository.save(produto);

        produtoDto.setId(produto.getId());

        return produtoDto;


    }

    /**
     * Método para deletar produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){

        //verificar a existencia do produto
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()){
            throw new ResourceNotFoundException("Não foi possivel deletar o produto com id: " + id + " - Produto não existe");
        }

       produtoRepository.deleteById(id);
    }

    /**
     * Método para atualizar produto
     * @param produto a ser atualizado
     * @param id do produto
     * @return produto atualizado
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto){
        
        //Passar o id para o produtoDto
        produtoDto.setId(id);

        //Criar um obj de mapeamento
        ModelMapper mapper = new ModelMapper();

        //Converter o ProdutoDTO em um Produto
        Produto produto = mapper.map(produtoDto, Produto.class);

        //Atualizar o produto no BD
        produtoRepository.save(produto);

        //Retornar o produto
        return produtoDto;
    }
}
