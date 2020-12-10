
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Quarto implements Serializable {

    private String nome;
    Cliente clientehospedado;
    private int tipo;
    private float valor;
    private boolean ocupado = false;
    private LocalDateTime terminodata;
    private int totaldias;
    private String nomeacompanhante;
    private LocalDateTime datahoracheckin;

    float precolavanderia;
    float precocafemanha;
    float precoalmoco;
    float precojanta;

    float totalhospedagem;
    float totalservicos;

    List<ServicoQuarto> servicoquarto;

    void insere() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite uma identificação para o quarto (nome ou número) : ");
        while (nome == null) {
            nome = sc.nextLine();
        }
        System.out.println("Digite o tipo (1 = Solteiro, 2 = Solteiro Duplo, 3 = Casal : ");
        while (tipo == 0 || tipo > 3 || tipo < 0) {
            tipo = sc.nextInt();
        }
        System.out.println("Digite o valor da diária : ");
        while (valor == 0) {
            valor = sc.nextFloat();
        }
    }

    boolean checkin(List<Cliente> Clientes) {

        if (Clientes.size() > 0) {

            Scanner sc = new Scanner(System.in);

            System.out.println("**************[CLIENTES]*************");

            for (int i = 0; i < Clientes.size(); i++) {
                System.out.println(i + " : " + Clientes.get(i).getnome());
            }
            System.out.println("*************************************");

            System.out.println("Digite o código do cliente : ");

            int codigo = -1;
            while (codigo == -1) {
                codigo = sc.nextInt();
            }

            clientehospedado = Clientes.get(codigo);
            ocupado = true;

            LocalDateTime now = LocalDateTime.now();
            datahoracheckin = now;

            System.out.println("Dias hospedado : ");
            while (totaldias <= 0) {
                totaldias = sc.nextInt();
            }

            LocalDateTime data = LocalDateTime.now();
            data.plusDays(totaldias);
            terminodata = data;

            if (tipo == 2 || tipo == 3) {
                System.out.println("Possui acompanhante (1 - Não, 2 - Sim) : ");

                int acompanhado = 0;

                while (acompanhado <= 0 || acompanhado > 2) {
                    acompanhado = sc.nextInt();
                }

                if (acompanhado == 2) {

                    System.out.println("Digite o primeiro nome do acompanhante : ");

                    while (nomeacompanhante == null) {
                        nomeacompanhante = sc.next();
                    }
                }
            }
        } else {
            System.out.println("\nNão há nenhum cliente cadastrado, cadastre um para fazer CheckIn\n");
            return false;
        }
        return true;
    }

    void salvar(Quarto quarto) {
        try {

            boolean existe = false;
            File f = new File("C:\\Hotel\\Quartos\\" + nome);
            if (f.exists()) {
                existe = true;
            }

            //Gera o arquivo para armazenar o objeto
            FileOutputStream arquivoGrav = new FileOutputStream("C:\\Hotel\\Quartos\\" + nome);
            //Classe responsavel por inserir os objetos

            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
            //Grava o objeto cliente no arquivo      

            objGravar.writeObject(quarto);
            objGravar.flush();
            objGravar.close();
            arquivoGrav.flush();
            arquivoGrav.close();

            if (existe == true) {
                System.out.println("\nQuarto modificado com sucesso!\n");
            } else {
                System.out.println("\nQuarto registrado com sucesso!\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\nOops, aconteceu algum erro, por favor tente novamente!\n");
        }
    }

    void calculartotalhospedagem() {
        totalhospedagem = valor * totaldias;
    }

    void calculartotalservicosextras() {
        totalservicos += precolavanderia;
        totalservicos += precojanta;
        totalservicos += precoalmoco;
        totalservicos += precocafemanha;
    }

    boolean checkout() {
        System.out.println("********************");
        System.out.println("CheckOut concluído!");
        System.out.println("Cliente : " + clientehospedado.getnome());
        System.out.println("Dias hospedado :  " + totaldias);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Data de CheckIn : " + dtf.format(datahoracheckin));
        System.out.println("Data de CheckOut : " + dtf.format(now));

        calculartotalhospedagem();
        System.out.println("Valor da hospedagem : " + totalhospedagem);
        calculartotalservicosextras();
        System.out.println("Valor serviços extras : " + totalservicos);
        System.out.println("********************");

        clientehospedado = null;
        ocupado = false;
        terminodata = null;
        totaldias = 0;
        nomeacompanhante = null;
        datahoracheckin = null;
        precolavanderia = 0;
        precocafemanha = 0;
        precoalmoco = 0;
        precojanta = 0;
        totalhospedagem = 0;
        totalservicos = 0;

        return true;
    }

    String getnome() {
        return nome;
    }

    int gettipo() {
        return tipo;
    }

    Cliente getcliente() {
        return clientehospedado;
    }

    float getvalor() {
        return valor;
    }

    boolean getocupado() {
        return ocupado;
    }

    int getdias() {
        return totaldias;
    }

    LocalDateTime getterminodata() {
        return terminodata;
    }

    String getnomeacompanhante() {
        return nomeacompanhante;
    }
}
