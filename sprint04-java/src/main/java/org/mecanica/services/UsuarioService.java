package org.mecanica.services;

import org.mecanica.entidades.Usuario;
import org.mecanica.infraestrutura.Log4jLogger;
import org.mecanica.repositorios.UsuarioRepositorio;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

public class UsuarioService {
    private final UsuarioRepositorio usuarioRepositorio;
    private final Log4jLogger logger;

    private static final String CPF_REGEX = "^\\d{11}$";
    private static final String SENHA_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\W).{8,}$";
    private static final int IDADE_MINIMA = 18;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public UsuarioService() {
        usuarioRepositorio = new UsuarioRepositorio();
        logger = new Log4jLogger();
    }

    public void registrar(Usuario usuario) {
        if (usuario.getDataNascimento() == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória.");
        }
        if (validarUsuario(usuario)) {
            usuarioRepositorio.cadastrar(usuario);
            logger.logUsuarioCadastrado(usuario);
        }
    }


    public void deletar(int id) {
        Optional<Usuario> usuario = usuarioRepositorio.buscarPorId(id);
        if (usuario.isPresent()) {
            usuarioRepositorio.remover(id);
            logger.logUsuarioDeletado(usuario.get());
        }
    }

    public void atualizar(Usuario usuario, int id) {
        if (validarUsuario(usuario)) {
            usuarioRepositorio.atualizar(usuario, id);
            logger.logUsuarioAtualizado(usuario);
        }
    }

    public Optional<Usuario> buscarPorId(int id) {
        Optional<Usuario> usuario = usuarioRepositorio.buscarPorId(id);
        usuario.ifPresent(logger::logUsuarioBuscadoPorId);
        return usuario;
    }

    public List<Usuario> listar() {
        List<Usuario> usuarios = usuarioRepositorio.listar();
        logger.logUsuariosListados(usuarios);
        return usuarios;
    }

    private static boolean validarUsuario(Usuario usuario) {
        if (!validarCpf(usuario.getCpf())) {
            throw new IllegalArgumentException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }
        if (!validarSexo(usuario.getSexo())) {
            throw new IllegalArgumentException("Sexo inválido. Deve ser 'M' ou 'F'.");
        }
        if (!validarDataNascimento(usuario.getDataNascimento())) {
            throw new IllegalArgumentException("Data de nascimento inválida. Usuário deve ter pelo menos 18 anos.");
        }
        if (!validarSenha(usuario.getSenha())) {
            throw new IllegalArgumentException("Senha inválida. Deve conter pelo menos 8 caracteres, incluindo uma letra maiúscula, uma minúscula e um caractere especial.");
        }
        return true;
    }

    private static boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches(CPF_REGEX);
    }

    private static boolean validarSexo(String sexo) {
        return sexo != null && (sexo.equalsIgnoreCase("M") || sexo.equalsIgnoreCase("F"));
    }

    private static boolean validarDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento == null || dataNascimento.isAfter(LocalDate.now())) {
            return false;
        }
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        System.out.println("Idade calculada: " + idade);
        return idade >= IDADE_MINIMA;
    }


    private static boolean validarSenha(String senha) {
        return senha != null && Pattern.matches(SENHA_REGEX, senha);
    }

    public boolean autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepositorio.buscarPorEmail(email);
        return usuarioOpt.map(usuario -> usuario.getSenha().equals(senha)).orElse(false);
    }
}
