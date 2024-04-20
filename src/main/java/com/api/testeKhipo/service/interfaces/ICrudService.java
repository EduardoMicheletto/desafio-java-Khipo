package com.api.testeKhipo.service.interfaces;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICrudService<T> {

    List<T> findAll(Pageable paginacao);

    T findById(Long id);

    T criar(T t) throws Exception;

    T alterar(T t) throws Exception;

    void deletar(Long id) throws Exception;
}
