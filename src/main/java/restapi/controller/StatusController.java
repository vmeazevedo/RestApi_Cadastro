package restapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class StatusController {

	@ApiOperation(value = "Retorna o Status de Conexão")
	@ApiResponses(value = {
		    @ApiResponse(code = 200, message = "Retorna o status de conexão"),
		    @ApiResponse(code = 401, message = "Não autenticado."),
		    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
		    @ApiResponse(code = 404, message = "Não foi encontrado nenhum dado."),
		})
	@GetMapping(path = "/api/status")
	public String check() {
		if (HttpStatus.OK != null) {
			return "Status: Online";
		}
		return null;
	}

}
