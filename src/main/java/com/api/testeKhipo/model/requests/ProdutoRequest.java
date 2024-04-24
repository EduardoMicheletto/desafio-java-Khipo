package com.api.testeKhipo.model.requests;

import java.math.BigDecimal;

public class ProdutoRequest{

    public record ProdutoCriarRequest(String nome, BigDecimal valor, Long idCategoria){}

    public record ProdutoAlterarRequest(Long id, String nome, BigDecimal valor, Long idCategoria){}
}

