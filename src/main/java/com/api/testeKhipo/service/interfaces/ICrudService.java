package com.api.testeKhipo.service.interfaces;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICrudService<T, C, A> {

    List<T> findAll(Pageable paginacao);

    T findById(Long id);

    T create(C c) throws Exception;

    T alter(A a) throws Exception;

    void delete(Long id) throws Exception;
}
