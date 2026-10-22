
package br.escola.bibliohaydee.model;

public class Perfil {

    private String apelido;

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String novoApelido) {
        if (novoApelido == null || novoApelido.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "O apelido não pode ser vazio."
            );
        }

        apelido = novoApelido.trim();
    }
}
