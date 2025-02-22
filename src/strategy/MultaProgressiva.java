package strategy;

public class MultaProgressiva implements IMultaStrategy {
    @Override
    public double calcularMulta(int diasAtraso) {
        return diasAtraso * diasAtraso * 0.5; // R$ 0,50 * (dias de atraso)^2
    }
}
