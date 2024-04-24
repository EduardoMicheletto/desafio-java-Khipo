package com.api.testeKhipo.controller.abstracts;

import com.api.testeKhipo.service.interfaces.ICrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public abstract class CrudController<T, C, A> {

    public abstract ICrudService<T, C, A> getService();

    @GetMapping
    public ResponseEntity<List<T>> findAllPaginado(Pageable paginacao) {
        List<T> p = getService().findAll(paginacao);
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<T> findById(@PathVariable Long id) {
        T t = getService().findById(id);
        if(t != null)
            return new ResponseEntity<>(t, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody @Valid C  c) {
        if(c == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>( getService().create(c), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<?> alterar(@RequestBody @Valid A a){
        if(a == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            return new ResponseEntity<>(getService().alter(a), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            getService().delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
