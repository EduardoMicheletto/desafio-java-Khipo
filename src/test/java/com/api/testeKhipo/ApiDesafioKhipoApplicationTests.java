package com.api.testeKhipo;

import com.api.testeKhipo.model.response.ProdutoResponse;
import com.api.testeKhipo.model.requests.ProdutoRequest;
import com.api.testeKhipo.repository.ProdutoRepository;
import com.api.testeKhipo.service.ProdutoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApiDesafioKhipoApplicationTests {

    static final ProdutoRequest.ProdutoCriarRequest criarProduto = new ProdutoRequest.ProdutoCriarRequest("teste", new BigDecimal(10.0), 1L);
    static final ProdutoRequest.ProdutoAlterarRequest alterarProduto = new ProdutoRequest.ProdutoAlterarRequest(0L,"teste", new BigDecimal(10.0), 1L);

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;


    @Test
    void testCreateProduto() {
        when(produtoService.create(criarProduto)).thenReturn(new ProdutoResponse());
    }


    @Test
    void testPaginatedProduto() {
        for(int i = 0; i < 15; i++){
            produtoService.create(criarProduto);
        }

        Pageable pageable = PageRequest.of(0, 10);
        List<ProdutoResponse> list = produtoService.findAll(pageable);
        assertEquals(10, list.size());
    }


    @Test
    void testAlterProduto() {
        try {
            ProdutoRequest.ProdutoAlterarRequest produtoTesteAlterar = new ProdutoRequest.ProdutoAlterarRequest(produtoService.create(criarProduto).getId(),"teste", new BigDecimal(10.0), 1L);
            when(produtoService.alter(produtoTesteAlterar)).thenReturn(new ProdutoResponse());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAlterProdutoRetornaErro() {
        try {
            ProdutoRequest.ProdutoAlterarRequest requestSemId = new ProdutoRequest.ProdutoAlterarRequest(null, "teste", new BigDecimal(10.0), 1L);

            doThrow(new Exception()).when(produtoService.alter(requestSemId));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    void testDeleteProduto() {
        try {
            produtoService.delete(1000000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
