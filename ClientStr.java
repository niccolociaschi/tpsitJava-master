import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.rmi.UnknownHostException;

public class ClientStr {
  String nomeServer = "ServerGoogle10110";
  int portaServer = 1234;
  Socket mioSocket;
  BufferedReader tastiera; // input utente
  String stringaUtente; // sttringa inserita
  String stringaRicevutaDalServer;
  DataOutputStream outVersoServer; // flusso di uscita
  BufferedReader inDalServer; // fluso di entrata

  public Socket connetti() {
    System.out.println("2 CLIENT partito in esecuzione...");
    try {
      tastiera = new BufferedReader(new InputStreamReader(System.in)); // input da tastiera
      mioSocket = new Socket(nomeServer, portaServer); // cerca di connettersi
      outVersoServer = new DataOutputStream(mioSocket.getOutputStream()); // stream di invio
      inDalServer = new BufferedReader(new InputStreamReader(mioSocket.getInputStream())); // stream di ricezione
    } // try
    catch (UnknownHostException e) {
      System.err.println("Host sconosciuto");
    } // catch
    catch (Exception e) {

      System.out.println(e.getMessage());
      System.out.println("Errore durante la connessione");
      System.exit(1);
    } // catch2
    return mioSocket;
  } // socket

  public void comunica() {
    try {
      System.out.println("4... inserisci la stringa da trasmettere al server" + '\n');
      stringaUtente = tastiera.readLine();
      System.out.println("5...invio la stringa al server e attendo");
      outVersoServer.writeBytes(stringaUtente + '\n');
      stringaRicevutaDalServer = inDalServer.readLine();
      System.out.println("8... risposta dal server" + '\n' + stringaRicevutaDalServer);
      System.out.println("9 CLIENT: termina elaborazione e chiude connessioni");
      mioSocket.close();
    } // try
    catch (Exception e) {
      System.out.println(e.getMessage());
      System.out.println("errore durante la comunicazione col server!");
      System.exit(1);
    } // catch

  }

  public static void main(String args[]) {
    ClientStr Client1 = new ClientStr();
    Client1.comunica();
    ;
    Client1.connetti();
  }

} // class
