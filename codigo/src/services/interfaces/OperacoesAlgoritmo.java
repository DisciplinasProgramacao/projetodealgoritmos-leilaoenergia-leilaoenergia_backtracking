package services.interfaces;

import entities.Lance;
import entities.MaxResultado;

import java.util.List;

public interface OperacoesAlgoritmo {

    void executar(
            int lucro, MaxResultado maxResultado, int indice, List<Lance> lancesTotais,
            List<Lance> lancesSelecionados);
}
