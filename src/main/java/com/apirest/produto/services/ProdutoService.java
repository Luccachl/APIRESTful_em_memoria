package com.apirest.produto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apirest.produto.model.Produto;
import com.apirest.produto.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Método que retorna uma lista de produtos
     * @return ista de produtos
     */
    public List<Produto> obterTodos(){
       return produtoRepository.obterTodos(); 
    }

     /**
     * Retorna o produto caso encontre o id e null caso não encontre
     * @param id do produto a ser localizado
     * @return Produto || null
     */
    public Optional <Produto> obterPorId(Integer id){
        return produtoRepository.obterPorId(id);
    }

    /**
     * Método para adc novo produto
     * @param produto a ser adicionado
     * @return Produto adicionado
     */
    public Produto adicionar(Produto produto){
        return produtoRepository.adicionar(produto);
    }

    /**
     * Método para deletar produto por id
     * @param id do produto a ser deletado
     */
    public void deletar(Integer id){
       produtoRepository.deletar(id);
    }

    /**
     * Método para atualizar produto
     * @param produto a ser atualizado
     * @param id do produto
     * @return produto atualizado
     */
    public Produto atualizar(Integer id, Produto produto){
        produto.setId(id);

        return produtoRepository.atualizar(produto);
    }
}
