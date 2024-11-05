package org.mecanica.services;

import lombok.var;
import org.mecanica.entidades.Veiculo;
import org.mecanica.infraestrutura.Log4jLogger;
import org.mecanica.repositorios.VeiculoRepositorio;

import java.util.List;
import java.util.Optional;

public class VeiculoService {
    private VeiculoRepositorio veiculoRepositorio;
    private Log4jLogger logger;

    private static final String PLACA_REGEX = "^[A-Z]{3}-\\d{4}$|^[A-Z]{3}\\d[A-Z]\\d{2}$";

    public VeiculoService() {
        veiculoRepositorio = new VeiculoRepositorio();
        logger = new Log4jLogger();
    }

    public void Cadastrar(Veiculo veiculo) {
        var carroValido = validarVeiculo(veiculo);
        if (carroValido) {
            veiculoRepositorio.cadastrar(veiculo);
            logger.logVeiculoCadastrado(veiculo);
        }
    }

    public void Deletar(int id) {
        var carro = veiculoRepositorio.buscarPorId(id);
        if (carro.isPresent()) {
            veiculoRepositorio.remover(id);
            logger.logVeiculoDeletado(carro.get());
        }
    }

    public void Atualizar(Veiculo veiculo, int id) {
        var veiculoValido = validarVeiculo(veiculo);
        if (veiculoValido) {
            veiculoRepositorio.atualizar(veiculo, id);
            logger.logVeiculoAtualizado(veiculo);
        }
    }

    public Optional<Veiculo> BuscarPorId(int id) {
        var veiculo = veiculoRepositorio.buscarPorId(id);
        veiculo.ifPresent(logger::logVeiculoBuscadoPorId);
        return veiculo;
    }

    public List<Veiculo> Listar() {
        var veiculos = veiculoRepositorio.listar();
        logger.logVeiculosListados(veiculos);
        return veiculos;
    }

    private static boolean validarVeiculo(Veiculo veiculo) {
        return validarPlaca(veiculo.getPlaca()) && veiculo.getAno() > 1885 && veiculo.getQuilometragem() >= 0;
    }

    private static boolean validarPlaca(String placa) {
        return placa != null && placa.toUpperCase().matches(PLACA_REGEX);
    }
}
