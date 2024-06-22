package entities;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
public class Empresa extends BaseEntity{

    private String nome;

    public Empresa(String nome, Integer quantidade) {
        super(quantidade);
        this.nome = nome;
    }

}
