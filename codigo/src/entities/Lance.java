package entities;

import lombok.*;


@Data
@EqualsAndHashCode(callSuper = true)
public class Lance extends BaseEntity{

    private Integer valor;

    private Long idCompradora;

    public Lance(Integer valor, Integer quantidade, Long idCompradora) {
        super(quantidade);
        this.valor = valor;
        this.idCompradora = idCompradora;
    }

    public double valorPorMw() {
        return (double) valor / this.getQuantidade ();
    }
}
