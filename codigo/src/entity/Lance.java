package entity;

public class Lance {
    private static Long proximoId = 1L;
    public Long id;
    public int energia;
    public int valor;

    public Lance(int energia, int valor) {
        this.id = proximoId++;
        this.energia = energia;
        this.valor = valor;
    }

    public int getValor () {
        return valor;
    }

    public static Long getProximoId () {
        return proximoId;
    }

    public Long getId () {
        return id;
    }

    public int getEnergia () {
        return energia;
    }

    @Override
    public String toString() {
        return "Bid{energia=" + energia + ", valor=" + valor + "}";
    }
}