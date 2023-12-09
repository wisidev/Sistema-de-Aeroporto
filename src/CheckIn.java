
public class CheckIn {

	private Reserva reserva;
	private boolean isCheckIn;

	public CheckIn(Reserva reserva, boolean isCheckIn) {
		super();
		this.reserva = reserva;
		this.isCheckIn = isCheckIn;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	public boolean isCheckIn() {
		return isCheckIn;
	}

	public void setCheckIn(boolean isCheckIn) {
		this.isCheckIn = isCheckIn;
	}

}
