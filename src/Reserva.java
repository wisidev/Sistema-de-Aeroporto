import java.util.List;

public class Reserva {

	private Passageiro passageiro;
	private Voo voo;
	private boolean isPendente;

	public Reserva() {

	}

	public Reserva(Passageiro passageiro, Voo voo, boolean isPendente) {
		super();
		this.passageiro = passageiro;
		this.voo = voo;
		this.isPendente = isPendente;
	}

	public Passageiro getPassageiro() {
		return passageiro;
	}

	public void setPassageiro(Passageiro passageiro) {
		this.passageiro = passageiro;
	}

	public Voo getVoo() {
		return voo;
	}

	public void setVoo(Voo voo) {
		this.voo = voo;
	}

	public boolean isPendente() {
		return isPendente;
	}

	public void setPendente(boolean isPendente) {
		this.isPendente = isPendente;
	}

	public static Reserva findReserva(List<Reserva> reservas, int cpfPassageiro) {
		for (var reserva : reservas) {
			if (reserva.getPassageiro().getCpf() == cpfPassageiro && !reserva.isPendente) {
				return reserva;
			}
		}

		return null;
	}

}
