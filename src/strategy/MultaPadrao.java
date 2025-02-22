package strategy;

public class MultaPadrao implements IMultaStrategy {
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * 1.0; // R$ 1,00 por dia de atraso
    }
}
