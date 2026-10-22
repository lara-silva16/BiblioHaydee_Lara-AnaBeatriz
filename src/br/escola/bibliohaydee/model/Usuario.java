
package br.escola.bibliohaydee.model;

public class Usuario {

    private String nome;
    private String matricula;
    private String tipo;
    private Integer limiteEmprestimos;
    private Integer emprestimosAtivos;

    public Usuario(String novoNome, String novaMatricula,
                   String novoTipo, Integer novoLimiteEmprestimos) {

        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }

        if (novoTipo == null ||
                (!novoTipo.equalsIgnoreCase("ALUNO") &&
                        !novoTipo.equalsIgnoreCase("PROFESSOR"))) {
            throw new IllegalArgumentException(
                    "O tipo deve ser ALUNO ou PROFESSOR."
            );
        }

        nome = novoNome.trim();
        matricula = novaMatricula;
        tipo = novoTipo.toUpperCase();
        limiteEmprestimos = novoLimiteEmprestimos;
        emprestimosAtivos = 0;
    }

    public String getNome() {
        return nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public Integer getLimiteEmprestimos() {
        return limiteEmprestimos;
    }

    public Integer getEmprestimosAtivos() {
        return emprestimosAtivos;
    }









}
