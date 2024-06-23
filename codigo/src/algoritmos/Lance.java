package algoritmos;

public class Lance {
    public int energia;
    public int valor;

    public Lance(int energia, int valor) {
        this.energia = energia;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Bid{energia=" + energia + ", valor=" + valor + "}";
    }
}