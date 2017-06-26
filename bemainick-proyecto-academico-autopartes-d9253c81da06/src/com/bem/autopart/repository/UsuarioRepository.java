package com.bem.autopart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bem.autopart.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Query("select c from Usuario c" + " where c.nombre like :nombre")
	public List<Usuario> obtenerUsuarioPorNombre(@Param("nombre") String nombre);

	@Query("select c from Usuario c"
			+ " where c.usuario like :usuario and c.contrasena like :contrasena")
	public Usuario obtenerUsuarioLogged(@Param("usuario") String usuario,
			@Param("contrasena") String contrasena);
}
