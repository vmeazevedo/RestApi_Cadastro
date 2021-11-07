package restapi.repository;

import org.springframework.data.repository.CrudRepository;

import restapi.model.UsuarioModel;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {

}
