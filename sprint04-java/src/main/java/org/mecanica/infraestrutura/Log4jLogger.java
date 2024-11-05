package org.mecanica.infraestrutura;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mecanica.entidades.*;

import java.util.List;

public class Log4jLogger {
    private final Logger loginLogger;
    private final Logger usuarioLogger;
    private final Logger veiculoLogger;
    private final Logger diagnosticoLogger;

    public Log4jLogger() {
        this.loginLogger = LogManager.getLogger(Login.class);
        this.usuarioLogger = LogManager.getLogger(Usuario.class);
        this.veiculoLogger = LogManager.getLogger(Veiculo.class);
        this.diagnosticoLogger = LogManager.getLogger(Diagnostico.class);
    }

    public void logLoginCadastrado(Login login) {
        loginLogger.info("Cadastrado: " + login);
    }

    public void logLoginAtualizado(Login login) {
        loginLogger.info("Atualizado: " + login);
    }

    public void logLoginDeletado(Login login) {
        loginLogger.info("Deletado: " + login);
    }

    public void logLoginsListados(List<Login> listaLogins) {
        loginLogger.info("Listados: " + listaLogins);
    }

    public void logLoginBuscadoPorId(Login login) {
        loginLogger.info("Buscado: " + login);
    }

    public void logUsuarioCadastrado(Usuario usuario) {
        usuarioLogger.info("Cadastrado: " + usuario);
    }

    public void logUsuarioAtualizado(Usuario usuario) {
        usuarioLogger.info("Atualizado: " + usuario);
    }

    public void logUsuarioDeletado(Usuario usuario) {
        usuarioLogger.info("Deletado: " + usuario);
    }

    public void logUsuariosListados(List<Usuario> listaUsuarios) {
        usuarioLogger.info("Listados: " + listaUsuarios);
    }

    public void logUsuarioBuscadoPorId(Usuario usuario) {
        usuarioLogger.info("Buscado: " + usuario);
    }

    public void logVeiculoCadastrado(Veiculo veiculo) {
        veiculoLogger.info("Cadastrado: " + veiculo);
    }

    public void logVeiculoAtualizado(Veiculo veiculo) {
        veiculoLogger.info("Atualizado: " + veiculo);
    }

    public void logVeiculoDeletado(Veiculo veiculo) {
        veiculoLogger.info("Deletado: " + veiculo);
    }

    public void logVeiculosListados(List<Veiculo> listaVeiculos) {
        veiculoLogger.info("Listados: " + listaVeiculos);
    }

    public void logVeiculoBuscadoPorId(Veiculo veiculo) {
        veiculoLogger.info("Buscado: " + veiculo);
    }

    public void logDiagnosticoCadastrado(Diagnostico diagnostico) {
        diagnosticoLogger.info("Cadastrado: " + diagnostico);
    }

    public void logDiagnosticoAtualizado(Diagnostico diagnostico) {
        diagnosticoLogger.info("Atualizado: " + diagnostico);
    }

    public void logDiagnosticoDeletado(Diagnostico diagnostico) {
        diagnosticoLogger.info("Deletado: " + diagnostico);
    }

    public void logDiagnosticosListados(List<Diagnostico> listaDiagnosticos) {
        diagnosticoLogger.info("Listados: " + listaDiagnosticos);
    }

    public void logDiagnosticoBuscadoPorId(Diagnostico diagnostico) {
        diagnosticoLogger.info("Buscado: " + diagnostico);
    }
}
