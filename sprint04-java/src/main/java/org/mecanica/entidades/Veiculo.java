package org.mecanica.entidades;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo extends _EntidadeBase {
    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private double quilometragem;
    private Usuario usuario;

    public Veiculo(int id, String placa, String modelo, String marca, int ano, double quilometragem) {
        super(id);
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.quilometragem = quilometragem;
    }
}
