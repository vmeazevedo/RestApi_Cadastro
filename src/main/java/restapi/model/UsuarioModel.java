package restapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity(name = "usuario")
public class UsuarioModel {
	
	@ApiModelProperty(value = "Código do usuário")
	@Id // informa que a variável é o Id da tabela
	public Integer codigo;

	@ApiModelProperty(value = "Nome do usuário")
	@Column(nullable = false, length = 100) // informa que a coluna não pode ser nula, e o tamanho dela
	public String nome;
	
	@ApiModelProperty(value = "Login cadastrado")
	@Column(nullable = false, length = 20)
	public String login;
	
	@ApiModelProperty(value = "Senha cadastrada")
	@Column(nullable = false, length = 15)
	public String senha;
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

}
