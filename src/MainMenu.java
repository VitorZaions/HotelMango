
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Computador
 */
public class MainMenu {

    Scanner sc = new Scanner(System.in);

    //Objetos para funções
    List<Cliente> clientes = new ArrayList<Cliente>();
    List<Quarto> quartos = new ArrayList<Quarto>();

    void Iniciar() {

        clientes = pegarclientes();
        quartos = pegarquartos();

        System.out.println("Bem vindo ao sistema Mangojata Hotéis");
        System.out.println("O que você deseja fazer?  \n 1 = Clientes \n 2 = Quartos \n 3 = Serviços \n 4 = Sair");
        int type = 0;

        while (type <= 0 || type > 3) {
            if (type == 4) {
                System.exit(1);
            }
            type = sc.nextInt();
        }

        if (type == 1) {
            System.out.println("[CLIENTES] O que deseja fazer : (1 - Inserir / Modificar)");
            int tipo = 0;

            while (tipo <= 0 || tipo > 1) {
                tipo = sc.nextInt();
            }

            if (tipo == 1) {
                Cliente c1 = new Cliente();
                c1.insere();
                c1.salvar(c1);
                Iniciar();
            }
        }

        if (type == 2) {

            System.out.println("[QUARTOS] O que deseja fazer : (1 - Inserir / Modificar), 2 - (CheckIn), 3 - (CheckOut)");

            int tipo = 0;

            while (tipo <= 0 || tipo > 4) {
                tipo = sc.nextInt();
            }

            if (tipo == 1) {
                Quarto q1 = new Quarto();
                q1.insere();
                q1.salvar(q1);
                Iniciar();
            }
            if (tipo == 2) {

                if (quartos.size() > 0) {

                    Quarto q = selecionarquarto();

                    if (q.getocupado() == true) {
                        System.out.println("\nOops, este quarto está ocupado, faça o CheckOut para ocupa-lo novamente.\n");
                    } else {
                        boolean concluido = q.checkin(clientes);
                        if (concluido) {
                            q.salvar(q);
                        }
                    }
                } else {
                    System.out.println("\nNão há nenhum quarto cadastrado, cadastre um para fazer CheckIn.\n");
                }
                Iniciar();
            }

            if (tipo == 3) {
                if (quartos.size() > 0) {
                    Quarto q = selecionarquarto();

                    if (q.getocupado() == true) {
                        boolean concluido = q.checkout();
                        if (concluido) {
                            q.salvar(q);
                        }

                    } else {
                        System.out.println("\nEste quarto não está ocupado, selecione outro.\n");
                    }
                } else {
                    System.out.println("\nNão há nenhum quarto cadastrado, cadastre um para fazer CheckOut.\n");
                }
                Iniciar();
            }
        }

        if (type == 3) {

            if (quartos.size() > 0) {

                Quarto q = selecionarquarto();

                if (q.getocupado() == true) {

                    System.out.println("Selecione o serviço ( 1 - café da manha, 2 - almoço, 3 - janta, 4 - lavanderia : ");

                    int idservico = 0;
                    while (idservico <= 0 || idservico > 4) {
                        idservico = sc.nextInt();
                    }

                    if (idservico == 1) {

                        System.out.println("[CAFÉ DA MANHA] Digite o preço : ");

                        float precocafe = 0;
                        while (precocafe <= 0) {
                            precocafe = sc.nextFloat();
                        }

                        q.precocafemanha = precocafe;
                        q.salvar(q);

                        System.out.println("\n[CAFÉ DA MANHA] Serviço adicionado com sucesso!\n");

                    } else if (idservico == 2) {

                        System.out.println("[ALMOÇO] Digite o preço : ");

                        float precoalmoco = 0;
                        while (precoalmoco <= 0) {
                            precoalmoco = sc.nextFloat();
                        }

                        q.precoalmoco = precoalmoco;
                        q.salvar(q);

                        System.out.println("\n[ALMOÇO] Serviço adicionado com sucesso!\n");

                    } else if (idservico == 3) {
                        System.out.println("[JANTA] Digite o preço : ");

                        float precojanta = 0;
                        while (precojanta <= 0) {
                            precojanta = sc.nextFloat();
                        }

                        q.precojanta = precojanta;
                        q.salvar(q);

                        System.out.println("\n[JANTA] Serviço adicionado com sucesso!\n");

                    } else {
                        System.out.println("\n[LAVANDERIA] Digite o preço : ");

                        float precolavanderia = 0;
                        while (precolavanderia <= 0) {
                            precolavanderia = sc.nextFloat();
                        }

                        q.precolavanderia = precolavanderia;
                        q.salvar(q);

                        System.out.println("\n[LAVANDERIA] Serviço adicionado com sucesso!\n");

                    }

                } else {
                    System.out.println("\nEste quarto não está ocupado, selecione outro.\n");
                }

            } else {
                System.out.println("\nNão há nenhum quarto cadastrado, cadastre um para fazer CheckOut.\n");
            }

            Iniciar();
        }
    }

    Quarto selecionarquarto() {
        System.out.println("************[QUARTOS]****************");
        System.out.println(quartos.get(0).getnome());
        for (int i = 0; i < quartos.size(); i++) {
            String nome = quartos.get(i).getnome();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String datatermino = "--";
            String situacao = "Vago";
            String tipoquarto = "Solteiro";

            if (quartos.get(i).gettipo() == 2) {
                tipoquarto = "Solteiro Duplo";
            } else if (quartos.get(i).gettipo() == 3) {
                tipoquarto = "Casal";
            }

            float valor = quartos.get(i).getvalor();

            if (quartos.get(i).getocupado() == true) {
                situacao = "Ocupado";
                dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                datatermino = dtf.format(quartos.get(i).getterminodata());
            }
            System.out.println("[" + i + "] Nome : " + nome + ", situação : " + situacao + ", Tipo : " + tipoquarto + ", Diária : R$" + valor + ", Término da locação : " + datatermino);
        }
        System.out.println("*************************************");
        int escolha = -1;
        System.out.println("Digite o código do quarto : ");
        while (escolha <= -1 || escolha > quartos.size()) {
            escolha = sc.nextInt();
        }
        Quarto q = new Quarto();
        q = quartos.get(escolha);
        return q;
    }

    List<Cliente> pegarclientes() {
        clientes.clear();
        try {
            File file = new File("C:\\Hotel\\Clientes");
            File afile[] = file.listFiles();

            for (int j = 0; j < afile.length; j++) {
                File arquivos = afile[j];
                FileInputStream arquivoLeitura = new FileInputStream("C:\\Hotel\\Clientes\\" + arquivos.getName());
                ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
                Cliente client = new Cliente();
                client = (Cliente) objLeitura.readObject();
                clientes.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    List<Quarto> pegarquartos() {
        quartos.clear();
        try {
            File file = new File("C:\\Hotel\\Quartos");
            File afile[] = file.listFiles();

            for (int j = 0; j < afile.length; j++) {
                File arquivos = afile[j];
                FileInputStream arquivoLeitura = new FileInputStream("C:\\Hotel\\Quartos\\" + arquivos.getName());
                ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura);
                Quarto room = new Quarto();
                room = (Quarto) objLeitura.readObject();
                quartos.add(room);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return quartos;
    }

}
