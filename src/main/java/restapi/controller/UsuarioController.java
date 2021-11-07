package restapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import restapi.model.UsuarioModel;
import restapi.repository.UsuarioRepository;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository repository;
	
	@ApiOperation(value = "Retorna os dados de todos os usuários")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna a lista de usuários"),
		    @ApiResponse(code = 401, message = "Não autenticado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Não foi encontrado nenhum dado."),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})	
	@GetMapping(path = "/api/usuario/listar-usuarios")
	public List<UsuarioModel> listarBase() {
		return (List<UsuarioModel>) repository.findAll();
	}
	
	@ApiOperation(value = "Retorna os dados do usuário pelo Id")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna o usuário do Id"),
		    @ApiResponse(code = 401, message = "Não autenticado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Não foi encontrado nenhum dado."),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping(path = "/api/usuario/pesquisar-codigo/{codigo}")
	public ResponseEntity consultar(@PathVariable("codigo") Integer codigo) {
		return repository.findById(codigo)
				.map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@ApiOperation(value = "Valida se o número do Id existe ou não na base")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna um booleano validando se o Id existe na base"),
		    @ApiResponse(code = 401, message = "Não autenticado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Não foi encontrado nenhum dado."),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@GetMapping(path = "/api/usuario/valida-codigo/{codigo}")
	public boolean valida(@PathVariable("codigo") Integer codigo) {
		return repository.existsById(codigo);
	}
	
	@ApiOperation(value = "Incluí um novo usuário na base de dados")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Inclui um novo usuário na base"),
		    @ApiResponse(code = 401, message = "Não autenticado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Não foi encontrado nenhum dado."),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@PostMapping(path = "/api/usuario/salvar")
	public UsuarioModel salvar(@RequestBody UsuarioModel usuario) {
		return repository.save(usuario);
	}
	
	@ApiOperation(value = "Deleta um usuário pelo Id informado")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Deleta um usuário pelo Id"),
		    @ApiResponse(code = 401, message = "Não autenticado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Não foi encontrado nenhum dado."),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@DeleteMapping(path = "/api/usuario/deletar/{codigo}")
	public String deletarProduto(@PathVariable("codigo") Integer codigo) {
		repository.deleteById(codigo);
		return "Usuário " + codigo + " removido com sucesso";
	}
	
	@ApiOperation(value = "Atualiza os dados de um usuário pelo Id informado")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna os dados do usuário atualizado"),
		    @ApiResponse(code = 401, message = "Não autenticado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Não foi encontrado nenhum dado."),
		    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
		})
	@PutMapping(path = "/api/usuario/atualizar-dados")
	public UsuarioModel atualizar(@RequestBody UsuarioModel usuariomodel) {
		UsuarioModel usuarioAtualizado = repository.findById(usuariomodel.getCodigo()).orElse(null);
		usuarioAtualizado.setNome(usuariomodel.getNome());
		usuarioAtualizado.setLogin(usuariomodel.getLogin());
		usuarioAtualizado.setSenha(usuariomodel.getSenha());
		return repository.save(usuarioAtualizado);
	}

}
