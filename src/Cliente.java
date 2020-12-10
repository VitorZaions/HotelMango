
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente extends Pessoa implements Serializable {

    
    void insere() {
        Scanner sc = new Scanner(System.in);
        //System.out.println(nome + endereco + idade + telefone);
        System.out.println("Digite o nome : ");
        while (super.nome == null) {
            super.nome = sc.nextLine();
        }
        System.out.println("Digite o endereço : ");
        while ( super.endereco == null) {

             super.endereco = sc.nextLine();
        }
        System.out.println("Digite o idade : ");

        while (super.idade == 0) {
             super.idade = sc.nextInt();
        }

        System.out.println("Digite o telefone : ");

        while ( super.telefone == null) {
             super.telefone = sc.next();
        }
    }

    void salvar(Cliente cliente) {
        try {

            boolean existe = false;
            File f = new File("C:\\Hotel\\Clientes\\" + super.nome);
            if (f.exists()) {
                existe = true;
            }

            //Gera o arquivo para armazenar o objeto
            FileOutputStream arquivoGrav = new FileOutputStream("C:\\Hotel\\Clientes\\" + super.nome);
            //Classe responsavel por inserir os objetos

            ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav);
            //Grava o objeto cliente no arquivo      

            objGravar.writeObject(cliente);
            objGravar.flush();
            objGravar.close();
            arquivoGrav.flush();
            arquivoGrav.close();

            if (existe == true) {
                System.out.println("\nCliente modificado com sucesso!\n");
            } else {
                System.out.println("\nCliente registrado com sucesso!\n");
            }
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println("\nOops, aconteceu algum erro, por favor tente novamente!\n");
        }

    }

    String getnome() {
        return super.nome;
    }

}
