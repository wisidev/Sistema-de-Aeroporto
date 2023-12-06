import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Aeroporto {
    private ArrayList<Voo> voos = new ArrayList<>();
    private Queue<Reserva> reservasPendentes = new LinkedList<>();
    private Stack<Passageiro> passageirosComCheckin = new Stack<>();

    public Stack<Passageiro> getPassageirosComCheckin() {
        return passageirosComCheckin;
    }

    public void setPassageirosComCheckin(Stack<Passageiro> passageirosComCheckin) {
        this.passageirosComCheckin = passageirosComCheckin;
    }

    private boolean isAdmin = false;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Aeroporto aeroporto = new Aeroporto();
        aeroporto.iniciarSistema();
    }

    public void iniciarSistema() {
        realizarLoginAdmin();
        {
            System.out.print("Digite o nome de usuário do administrador: ");
            String usuario = scanner.nextLine();
            System.out.print("Digite a senha do administrador: ");
            String senha = scanner.nextLine();

            // Validar o login do administrador (exemplo simples, substituir por um sistema
            // seguro em um ambiente real)
            if (usuario.equals("admin") && senha.equals("senhaAdmin")) {
                isAdmin = true;
                System.out.println("Login de administrador bem-sucedido.");
            } else {
                System.out.println("Credenciais de administrador inválidas. Acesso negado.");
                System.exit(0); // Encerra o programa se as credenciais estiverem incorretas
            }
        }

        System.out.println("Bem-vindo ao sistema de controle de aeroporto!");

        while (true) {
            exibirOpcoesMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar a quebra de linha

            switch (opcao) {
                case OpcoesMenu.CADASTRAR_VOO:
                    cadastrarVoo();
                    break;

                case OpcoesMenu.CADASTRAR_PASSAGEIRO:
                    cadastrarPassageiro();
                    break;

                case OpcoesMenu.REALIZAR_RESERVA:
                    realizarReserva(opcao, null);
                    break;

                case OpcoesMenu.PROCESSAR_RESERVAS:
                    processarReservas();
                    break;

                case OpcoesMenu.REALIZAR_CHECK_IN:
                    realizarLoginAdmin();
                    break;

                case OpcoesMenu.EXIBIR_INFO_VOOS:
                    exibirInformacoesVoos();
                    break;

                case OpcoesMenu.SAIR:
                    System.out.println("Obrigado por utilizar o sistema de controle de aeroporto. Adeus!");
                    salvarDados(); // Adiciona a chamada para salvar os dados antes de sair
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
            }
        }
    }

    private void realizarLoginAdmin() {
    }

    private void exibirOpcoesMenu() {
        System.out.println("Escolha uma opção:");
        System.out.println("1 - Cadastrar Voo");
        System.out.println("2 - Cadastrar Passageiro");
        System.out.println("3 - Realizar Reserva");
        System.out.println("4 - Processar Reservas (Apenas para administradores)");
        System.out.println("5 - Realizar Check-in");
        System.out.println("6 - Exibir Informações de Voos");
        System.out.println("7 - Sair");

        // Adicionado verificação para exibir a opção "4" apenas se for administrador
        if (isAdmin) {
            System.out.println("4 - Processar Reservas (Apenas para administradores)");
        }
    }

    private void cadastrarVoo() {
        System.out.print("Digite o número do voo (4 dígitos): ");
        int numeroVoo = scanner.nextInt();
        scanner.nextLine(); // Limpar a quebra de linha
        System.out.print("Digite a origem do voo: ");
        String origem = scanner.nextLine();
        System.out.print("Digite o destino do voo: ");
        String destino = scanner.nextLine();
        System.out.print("Digite a hora de partida do voo: ");
        String horaPartida = scanner.next();
        System.out.print("Digite os minutos de partida do voo: ");
        String minutoPartida = scanner.next();
        String horarioPartida = formatarHorario(horaPartida, minutoPartida);

        System.out.print("Digite a hora de chegada do voo: ");
        String horaChegada = scanner.next();
        System.out.print("Digite os minutos de chegada do voo: ");
        String minutoChegada = scanner.next();
        String horarioChegada = formatarHorario(horaChegada, minutoChegada);
        System.out.print("Digite a quantidade máxima de passageiros do voo: ");
        int maxPassageiros = scanner.nextInt();

        Voo novoVoo = new Voo(numeroVoo, origem, destino, horarioPartida, horarioChegada, maxPassageiros);
        voos.add(novoVoo);

        System.out.println("Voo cadastrado com sucesso!");
    }

    private String formatarHorario(String horaPartida, String minutoPartida) {
        return null;
    }

    private void cadastrarPassageiro() {
        System.out.print("Digite o nome do passageiro: ");
        String nomePassageiro = scanner.nextLine();
        System.out.print("Digite a idade do passageiro: ");
        int idadePassageiro = scanner.nextInt();
        scanner.nextLine(); // Limpar a quebra de linha
        System.out.print("Digite o CPF do passageiro: ");
        String cpfPassageiro = scanner.nextLine();
        System.out.print("Digite o e-mail do passageiro: ");
        String emailPassageiro = scanner.nextLine();

        new Passageiro(nomePassageiro, idadePassageiro, cpfPassageiro, emailPassageiro);

        System.out.println("Passageiro cadastrado com sucesso!");
    }

    private void realizarReserva(int numeroVoo2, Passageiro passageiro2) {
        System.out.print("Digite o número do voo: ");
        int numeroVoo = scanner.nextInt();
        scanner.nextLine(); // Limpar a quebra de linha
        System.out.print("Nome do passageiro: ");
        String nomePassageiro = scanner.nextLine();
        System.out.print("Idade do passageiro: ");
        int idadePassageiro = scanner.nextInt();
        scanner.nextLine();
        System.out.print("CPF do passageiro: ");
        String cpfPassageiro = scanner.nextLine();
        System.out.print("E-mail do passageiro: ");
        String emailPassageiro = scanner.nextLine();

        Passageiro passageiro = new Passageiro(nomePassageiro, idadePassageiro, cpfPassageiro, emailPassageiro);
    }

    private void salvarDados() {
        // Aqui você pode implementar a lógica para salvar os dados em um arquivo, banco
        // de dados, etc.
        // Neste exemplo, apenas exibimos uma mensagem indicando que os dados foram
        // salvos.
        System.out.println("Dados salvos com sucesso!");
    }

    private void processarReservas() {
        if (isAdmin) {
            while (!reservasPendentes.isEmpty()) {
                Reserva reserva = reservasPendentes.poll();
                Voo voo = reserva.getVoo();
                Passageiro passageiro = reserva.getPassageiro();
                voo.addPassageiro(passageiro);
                System.out.println("Reserva processada para o voo " + voo.getNumeroVoo() +
                        " para o passageiro " + passageiro.getNome());
            }
        } else {
            System.out.println("Você não tem permissão para processar reservas.");
        }
    }

    private void exibirInformacoesVoos() {
        for (Voo voo : voos) {
            System.out.println("Voo " + voo.getNumeroVoo());
            System.out.println("Origem: " + voo.getOrigem());
            System.out.println("Destino: " + voo.getDestino());
            System.out.println("Horário de partida: " + voo.getHorarioPartida());
            System.out.println("Horário de chegada: " + voo.getHorarioChegada());
            System.out.println("Máximo de passageiros: " + voo.getMaxPassageiros());
            System.out.println("Passageiros a bordo: " + voo.getPassageiros().size());
            System.out.println();
        }
    }

    // Métodos e classes anteriores permanecem inalterados...

    // Adicionado verificação para exibir a opção "4" apenas se for administrador
    private static class OpcoesMenu {
        static final int CADASTRAR_VOO = 1;
        static final int CADASTRAR_PASSAGEIRO = 2;
        static final int REALIZAR_RESERVA = 3;
        static final int PROCESSAR_RESERVAS = 4;
        static final int REALIZAR_CHECK_IN = 5;
        static final int EXIBIR_INFO_VOOS = 6;
        static final int SAIR = 7;
    }
}
