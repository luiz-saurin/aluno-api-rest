package br.com.compasso.alunoapirest.config.validacao;

/**
 * Essa classe trabalha junto com a classe @{@link ErroDeValidacaoHandler},
 * formatando a resposta .json enviada ao usuario. No caso, ira visualizar
 * a mensagem da excessao ocorrida e o campo aonde ocorreu.
 */
public class ErroDeFormularioDto {
    private final String campo;
    private final String erro;

    public ErroDeFormularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}
