package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

import static java.time.Instant.now;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountTime {

    private long inicio;
    private long fim;
    private long tempoExecucao;

    public void start() {
        this.inicio = System.currentTimeMillis();
    }

    public void stop() {
        this.fim = System.currentTimeMillis();
        calcularTempoExecucao();
    }

    private void calcularTempoExecucao() {
        this.tempoExecucao = this.fim - this.inicio;
    }

    public long getTempoExecucao() {
        return tempoExecucao;
    }
}

