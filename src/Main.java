import java.io.*;
import java.nio.file.*;
import java.util.Scanner;

public class Main {
    private static final String JOURNAL_FILE = "filesystem.journal";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo ao Simulador de Sistema de Arquivos!");
        System.out.println("Digite 'exit' para sair.");

        while (true) {
            System.out.print("> ");
            String comando = scanner.nextLine();
            if (comando.equalsIgnoreCase("exit")) break;
            processarComando(comando);
        }
        scanner.close();
    }

    private static void processarComando(String comando) {
        try {
            String[] partes = comando.split(" ");
            String operacao = partes[0];
            switch (operacao) {
                case "copy":
                    copiarArquivo(partes[1], partes[2]);
                    break;
                case "rm":
                    apagarArquivo(partes[1]);
                    break;
                case "mkdir":
                    criarDiretorio(partes[1]);
                    break;
                case "rmdir":
                    apagarDiretorio(partes[1]);
                    break;
                case "rename":
                    renomear(partes[1], partes[2]);
                    break;
                case "ls":
                    listarDiretorio(partes[1]);
                    break;
                default:
                    System.out.println("Comando desconhecido!");
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar comando: " + e.getMessage());
        }
    }

    private static void copiarArquivo(String origem, String destino) throws IOException {
        Files.copy(Paths.get(origem), Paths.get(destino), StandardCopyOption.REPLACE_EXISTING);
        registrarJournaling("copy " + origem + " " + destino);
        System.out.println("Arquivo copiado.");
    }

    private static void apagarArquivo(String caminho) throws IOException {
        Files.delete(Paths.get(caminho));
        registrarJournaling("rm " + caminho);
        System.out.println("Arquivo apagado.");
    }

    private static void criarDiretorio(String caminho) throws IOException {
        Files.createDirectories(Paths.get(caminho));
        registrarJournaling("mkdir " + caminho);
        System.out.println("Diretório criado.");
    }

    private static void apagarDiretorio(String caminho) throws IOException {
        Path dir = Paths.get(caminho);
        if (!Files.exists(dir)) {
            System.out.println("Diretório não encontrado: " + caminho);
            return;
        }

        Files.walk(dir)
                .sorted((a, b) -> b.compareTo(a)) // Apagar arquivos antes de diretórios
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        registrarJournaling("delete " + path);
                    } catch (IOException e) {
                        System.out.println("Erro ao apagar: " + path);
                    }
                });

        System.out.println("Diretório apagado: " + caminho);
    }

    private static void renomear(String caminho, String novoCaminho) throws IOException {
        Path origem = Paths.get(caminho);
        Path destino = Paths.get(novoCaminho);
        Files.move(origem, destino, StandardCopyOption.REPLACE_EXISTING);
        registrarJournaling("rename " + caminho + " " + novoCaminho);
        System.out.println("Renomeado para: " + novoCaminho);
    }

    private static void listarDiretorio(String caminho) throws IOException {
        Files.list(Paths.get(caminho)).forEach(System.out::println);
    }

    private static void registrarJournaling(String operacao) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(JOURNAL_FILE, true))) {
            writer.write(operacao);
            writer.newLine();
        }
    }
}
