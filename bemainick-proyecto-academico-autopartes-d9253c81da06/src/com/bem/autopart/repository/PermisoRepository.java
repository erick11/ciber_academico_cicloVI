package com.bem.autopart.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Permiso;

@Repository
public interface PermisoRepository extends CrudRepository<Permiso, Long> {

}
