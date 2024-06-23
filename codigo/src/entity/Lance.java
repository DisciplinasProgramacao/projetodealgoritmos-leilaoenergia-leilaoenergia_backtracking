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

    @Override
    public String toString() {
        return "Bid{energia=" + energia + ", valor=" + valor + "}";
    }
}