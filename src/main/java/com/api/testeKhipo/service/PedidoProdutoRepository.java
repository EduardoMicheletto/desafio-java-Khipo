package com.api.testeKhipo.service;

import com.api.testeKhipo.model.entity.Pedido;
import com.api.testeKhipo.model.entity.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Long> {

    void deleteAllByPedido_Id(Long idPedido);

    boolean existsByProduto_id(Long idProduto);

}
