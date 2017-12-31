package client;


import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Kovvah on 26/12/2017.
 */
public class ClientBasic {

        public static void main(String[] zero) {

            InetAddress LocaleAdresse ;
            InetAddress ServeurAdresse;

            try {

                LocaleAdresse = InetAddress.getLocalHost();
                System.out.println("L'adresse locale est : "+LocaleAdresse );

                ServeurAdresse= InetAddress.getByName("www.ankama.com");
                System.out.println("L'adresse du serveur d'ankama : "+ServeurAdresse);

            } catch (UnknownHostException e) {

                e.printStackTrace();
            }
        }
}
