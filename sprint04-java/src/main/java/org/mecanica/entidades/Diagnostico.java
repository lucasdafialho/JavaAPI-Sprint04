package org.mecanica.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Diagnostico extends _EntidadeBase {
    private String problema;
    private double orcamento;
    private LocalDate dataEmissao;
    private Veiculo veiculo;

    public Diagnostico(int id, String problema, double orcamento, LocalDate dataEmissao) {
        super(id);
        this.problema = problema;
        this.orcamento = orcamento;
        this.dataEmissao = dataEmissao;
    }

}
