package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class MaxResultado {

    private Empresa empresa;
    private CountTime countTime;
    private Set<Compradora> compradoras;
    private List<Lance> lancesEleitos;
    private int lucroMax;
    private int qtdVendida;

    public MaxResultado() {
        this.empresa = new Empresa("Produtora", 8000);
        this.countTime = new CountTime();
        this.compradoras = new HashSet<> ();
        this.lancesEleitos = new ArrayList<> ();
    }
}
