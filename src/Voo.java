import java.util.ArrayList;
import java.util.List;

public class Voo {
	private int numeroVoo;
	private String origem;
	private String destino;
	private String horaPartida;
	private String horaChegada;
	private int passageiros;
	private int vagasDisponiveis;

	public Voo() {

	}

	public Voo(int numeroVoo, String origem, String destino, String horaPartida, String horaChegada, int passageiros) {
		super();
		this.numeroVoo = numeroVoo;
		this.origem = origem;
		this.destino = destino;
		this.horaPartida = horaPartida;
		this.horaChegada = horaChegada;
		this.passageiros = passageiros;
	}

	public int getNumeroVoo() {
		return numeroVoo;
	}

	public void setNumeroVoo(int numeroVoo) {
		this.numeroVoo = numeroVoo;
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}

	public String getHoraChegada() {
		return horaChegada;
	}

	public void setHoraChegada(String horaChegada) {
		this.horaChegada = horaChegada;
	}

	public int getPassageiros() {
		return passageiros;
	}

	public void setPassageiros(int passageiros) {
		this.passageiros = passageiros;
		this.vagasDisponiveis = passageiros;
	}

	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}

	public void setVagasDisponiveis(int vagasDisponiveis) {
		this.vagasDisponiveis = vagasDisponiveis;
	}

	public void ExibeVoo(ArrayList<Voo> voos) {
		for (Voo voo : voos) {
			if (voo == null) {
				System.out.println("Não tem voos cadastrados!");
				break;
			}
			System.out.println("Número do voo: " + voo.getNumeroVoo());
			System.out.println("Origem do voo: " + voo.getOrigem());
			System.out.println("Destino do voo: " + voo.getDestino());
			System.out.println("Horário de partida: " + voo.getHoraPartida());
			System.out.println("Horário de chegada: " + voo.getHoraChegada());
			System.out.println("Limite de passageiros: " + voo.getPassageiros());
			System.out.println("Vagas disponiveis: " + voo.getVagasDisponiveis() + "\n");

		}
	}

	public static Voo findVoo(List<Voo> voos, int numeroVoo) {
		for (var voo : voos) {
			if (voo.getNumeroVoo() == numeroVoo && voo.getVagasDisponiveis() > 0) {
				return voo;
			}
		}

		return null;
	}


}
