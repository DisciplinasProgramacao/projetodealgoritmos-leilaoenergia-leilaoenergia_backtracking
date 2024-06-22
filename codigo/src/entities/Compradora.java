package entities;

import lombok.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Data
@EqualsAndHashCode(callSuper = true)
public class Compradora extends BaseEntity{

    private String nome;

    private List<Lance> lances;

    public Compradora(String nome, Integer quantidade) {
        super(quantidade); //quantidade de megawatts que a empresa possui para ofertas
        this.nome = nome;
        this.lances = new ArrayList<> ();
    }

    public static Compradora findById(List<Compradora> compradoras, Long id) {
        return compradoras.stream()
                .filter(c -> c.id.equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException ("Compradora não encontrada com o ID: " + id));
    }

    public void addLance(Lance lance) {
        this.lances.add (lance);
    }
}
