package com.apirest.produto.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import java.util.NoSuchElementException;
import org.springframework.stereotype.Repository;

import com.apirest.produto.model.Produto;
import com.apirest.produto.model.exception.ResourceNotFoundException;

@Repository
public class ProdutoRepository {
    
    private ArrayList<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Método que retorna uma lista de produtos
     * @return Lista de produtos
     */
    public List<Produto> obterTodos(){
        return produtos;
    }

    /**
     * Retorna o produto caso encontre o id e null caso não encontre
     * @param id do produto a ser localizado
     * @return Produto || null
     */
    public Optional <Produto> obterPorId(Integer id){
        return produtos
                .stream()
                .filter(produto -> produto.getId() == id)
                .findFirst();
    }

    /**
     * Método para adc novo produto
     * @param produto a ser adicionado
     * @return Produto adicionado
     */
    public Produto adicionar(Produto produto){
        ultimoId++;

        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Método para deletar produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
        produtos.removeIf(produto -> produto.getId() == id); 
    }

    /**
     * Método para atualizar produto
     * @param produto a ser atualizado
     * @return produto atualizado
     */
    public Produto atualizar(Produto produto){
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if(produtoEncontrado.isEmpty()){
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        deletar(produto.getId());
 
        produtos.add(produto);

        return produto;

    }
}
