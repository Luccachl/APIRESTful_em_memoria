package com.apirest.produto.repository;

import org.springframework.stereotype.Repository;

import com.apirest.produto.model.Produto;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{


}
