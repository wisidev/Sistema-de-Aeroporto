import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Principal {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Voo> voos = new ArrayList<Voo>();
		ArrayList<Passageiro> passageiros = new ArrayList<Passageiro>();
		Queue<Reserva> FilaReservas = new ArrayDeque<>();
		List<Reserva> ListaReservas = new ArrayList<>();
		Stack<CheckIn> checkIns = new Stack<>();
		int cpf = 0;
		int login;
		int opcao = 0;
		int aprovar = 0;
		Voo V1 = new Voo();

		while (true) {
			System.out.println("\n\n1- para usar o sistema sem fazer login \n2- para fazer login");
			login = scanner.nextInt();
			if (login == 1) {
				while (opcao != 4) {
					System.out.println("1- Exibir Voos\n2- Realizar reserva\n3- Realizar Check-in\n4- Sair");
					opcao = scanner.nextInt();
					switch (opcao) {
					case 1:
						V1.ExibeVoo(voos);
						if(voos.size() < 1) {
							System.out.println("Não existem voos cadastrados!");
						}
						break;
					case 2:
						realizarReserva(scanner, voos, passageiros, FilaReservas);
						break;
					case 3:
						realizarCheckIn(scanner, cpf, ListaReservas, aprovar, checkIns);
						break;
					case 4:
						login = 0;
						System.out.println("Aplicação encerrada!");
						break;
					}

				}
			} else if (login == 2) {
				System.out.println("Digite seu nome de usuario: ");
				scanner.nextLine();
				String user = scanner.nextLine();
				System.out.println("Digite sua senha: ");
				String senha = scanner.nextLine();
				if (!user.equals("admin") || !senha.equals("admin")) {
					System.out.println(user);
					System.out.println(senha);
					System.out.println("Usuário e/ou senha inválidos!");
				} else {
					System.out.println("Login bem-sucedido para o usuário: " + user);
					while (opcao != 6) {
						System.out.println("1- Exibir Voos\n2- Realizar reserva\n3- Realizar Check-in\n4- Cadastrar Voo\n5- Processar reserva\n6- Sair");
						opcao = scanner.nextInt();
						scanner.nextLine();
						switch (opcao) {
						case 1:
							V1.ExibeVoo(voos);
							if(voos.size() < 1) {
								System.out.println("Não existem voos cadastrados!");
							}
							break;
						case 2:
							realizarReserva(scanner, voos, passageiros, FilaReservas);
							break;
						case 3:
							realizarCheckIn(scanner, cpf, ListaReservas, aprovar, checkIns);
							break;
						case 4:
							cadastrarVoo(scanner, voos);
							break;
						case 5:
							processarReserva(scanner, FilaReservas, aprovar, ListaReservas);
							break;
						case 6:
							login = 0;
							System.out.println("Aplicação encerrada!");
							break;
						}

					}
				}

			}

		}

	}
	
	public static void realizarReserva(Scanner scanner, ArrayList<Voo> voos, ArrayList<Passageiro> passageiros, Queue<Reserva> FilaReservas) {
		String arroba = "@";
		
		while(true) {
			System.out.println("Digite o número do voo para o qual quer fazer reserva: ");
			int numVoo = scanner.nextInt();
			scanner.nextLine();

			var voo = Voo.findVoo(voos, numVoo);

			if (voo == null) {
				System.out.println("Este voo não existe ou está cheio!");
				break;
			}
			var P1 = new Passageiro();
			System.out.println("Voo encontrado!\n");
			System.out.println("E necessario fornecer informacoes de passageiro para fazer a reserva.\n");
			System.out.println("Digite seu nome: ");
			String nome = scanner.nextLine();
			P1.setNome(nome);
			System.out.println("Digite sua idade: ");
			int idade = scanner.nextInt();
			scanner.nextLine();
			P1.setIdade(idade);
			System.out.println("Digite seu cpf: ");
			int cpfPassageiro = scanner.nextInt();
			scanner.nextLine();
			P1.setCpf(cpfPassageiro);
			System.out.println("Digite seu email: ");
			String email = scanner.nextLine();
			while (true) {
				if (email.contains(String.valueOf(arroba))) {
					P1.setEmail(email);
					break;
				} else {
					System.out.println("Esse email não é válido! Digite um email novamente: ");
					email = scanner.nextLine();
					P1.setEmail(email);
				}
			}
			System.out.println("Passageiro adicionado ao sistema com sucesso!");
			passageiros.add(P1);

			var novaReserva = new Reserva();
			novaReserva.setPassageiro(P1);
			novaReserva.setVoo(voo);
			novaReserva.setPendente(true);

			FilaReservas.offer(novaReserva);
			break;
		}
		
	}
	public static void realizarCheckIn(Scanner scanner, int cpf, List<Reserva> ListaReservas, int aprovar, Stack<CheckIn> checkIns) {
		while(true) {
			if (ListaReservas.size() < 1) {
				System.out.println("\nNão existe check-in a serem feitos!\n");
				break;
			}

			System.out.println("Digite o número do CPF do passageiro a fazer check-in: ");
			cpf = scanner.nextInt();
			scanner.nextLine();

			var reservaValida = Reserva.findReserva(ListaReservas, cpf);

			if (reservaValida == null) {
				System.out.println("\nNão foi encontrado reservas para esse passageiro!\n");
				break;
			}

			System.out.println("Fazer Check-in para a seguinte Reserva");

			System.out.println("Nome passageiro: " + reservaValida.getPassageiro().getNome());
			System.out.println("CPF passageiro: " + reservaValida.getPassageiro().getCpf());
			System.out.println("Email passageiro: " + reservaValida.getPassageiro().getEmail());
			System.out.println("Voo número: " + reservaValida.getVoo().getNumeroVoo());
			System.out.println("Lotação maxima do Voo: " + reservaValida.getVoo().getPassageiros());
			System.out.println("Hora Partida do Voo: " + reservaValida.getVoo().getHoraPartida());
			System.out.println("Origem do Voo: " + reservaValida.getVoo().getOrigem());
			System.out.println("Destino do Voo: " + reservaValida.getVoo().getDestino());

			System.out.println("\nDeseja Realizar check-in para esse voo?\n");
			System.out.println("1 - Sim");
			System.out.println("2 - Não");
			aprovar = scanner.nextInt();
			if (aprovar == 1) {
				var checkIn = new CheckIn(reservaValida, true);

				checkIns.push(checkIn);
				ListaReservas.remove(reservaValida);
				System.out.println("\n\nCHECK-IN REALIZADO COM SUCESSO\n\n");
			}
			break;
		}
	}
	public static void cadastrarVoo(Scanner scanner, ArrayList<Voo> voos) {
		while(true) {
			System.out.println("Digite o número do voo (4 algarismos): ");
			int numeroVoo = scanner.nextInt();
			scanner.nextLine();
			String numString = Integer.toString(numeroVoo);
			int tamanho = numString.length();
			if (tamanho != 4) {
				System.out.println("O número do voo deve ter 4 algarismos");
				break;
			} else {
				var novoVoo = new Voo();
				novoVoo.setNumeroVoo(numeroVoo);
				System.out.println("Digite a origem do voo: ");
				String origem = scanner.nextLine();
				novoVoo.setOrigem(origem);
				System.out.println("Digite o destino do voo: ");
				String destino = scanner.nextLine();
				novoVoo.setDestino(destino);
				System.out.println("Digite o horario de partida do voo: ");
				String partida = scanner.nextLine();
				novoVoo.setHoraPartida(partida);
				System.out.println("Digite o horario de chegada do voo: ");
				String chegada = scanner.nextLine();
				novoVoo.setHoraChegada(chegada);
				System.out.println("Digite a lotacao maxima do voo: ");
				int maxPassageiros = scanner.nextInt();
				novoVoo.setPassageiros(maxPassageiros);
				voos.add(novoVoo);
			}
			break;
		}
	}
	public static void processarReserva(Scanner scanner, Queue<Reserva> FilaReservas, int aprovar, List<Reserva> ListaReservas) {
		Reserva reserva;
		while(true) {
			if (FilaReservas.size() < 1) {
				System.out.println("\nNão existem reservas a serem processadas!\n");
				break;
			}

			while (!FilaReservas.isEmpty()) {
				reserva = FilaReservas.peek();
				System.out.println("Processar a seguinte reserva");

				System.out.println("Nome passageiro: " + reserva.getPassageiro().getNome());
				System.out.println("CPF passageiro: " + reserva.getPassageiro().getCpf());
				System.out.println("Email passageiro: " + reserva.getPassageiro().getEmail());
				System.out.println("Voo número: " + reserva.getVoo().getNumeroVoo());
				System.out.println("Lotação maxima do Voo: " + reserva.getVoo().getPassageiros());
				System.out.println("Hora Partida do Voo: " + reserva.getVoo().getHoraPartida());
				System.out.println("Origem do Voo: " + reserva.getVoo().getOrigem());
				System.out.println("Destino do Voo: " + reserva.getVoo().getDestino());

				System.out.println("\nDeseja aprovar essa Reserva?\n");
				System.out.println("1 - Sim");
				System.out.println("2 - Não");
				aprovar = scanner.nextInt();
				if (aprovar == 1) {

					if (reserva.getVoo().getVagasDisponiveis() < 1) {
						System.out.println("ESTE VOO ESTÁ CHEIO!\n\n");
						break;
					}

					reserva.setPendente(false);
					reserva.getVoo().setVagasDisponiveis(reserva.getVoo().getVagasDisponiveis() - 1);

					ListaReservas.add(reserva);

					System.out.println("RESERVA APROVADA COM SUCESSO!\n\n");

					if (FilaReservas.isEmpty()) {
						System.out.println("Não existe mais reservas a serem processadas!\n\n");
						break;
					}

				}

				FilaReservas.poll();

			}

			break;
			
		}
	}
}
