package org.example.pruebaevaluable;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.model.InOnlyDefinition;
import org.example.marshalling.Usuarios;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JAXBException, IOException {
        Scanner sc=new Scanner(System.in);
        int opcion;


        // Menú principal del programa

        do {
            System.out.println("Bienvenido a la FeriaApp 🌟");
            System.out.println("=================================");
            System.out.println("Elige una de las siguientes opciones:");
            System.out.println("1 - Marshalling casetas a XML 📄");
            System.out.println("2 - Unmarshalling casetas de XML 📥");
            System.out.println("3 - Mostrar la caseta número X desde XML 🔍");
            System.out.println("4 - Marshalling casetas a JSON 📊");
            System.out.println("5 - Unmarshalling casetas de JSON 📬");
            System.out.println("6 - Mostrar la caseta número X desde JSON 🔍");
            System.out.println("7 - Salir 🚪");
            System.out.println("=================================");

             opcion=sc.nextInt();


             // Manejamos cada uno de los métodos con un switch

             switch(opcion) {

                 case 1:
                     MarshallingXML();
                     break;
                 case 2:
                     UnMarshallingXMl();
                     break;
                 case 3:
                        mostrarCasetaX();
                     break;
                 case 4:
                   MarshallingJSON();
                     break;
                 case 5:
                     UnmarshallingJSON();
                     break;
                 case 6:
                    mostrarCasetaJson();
                     break;
                 case 7:
                     System.out.println("Saliendo del programa...");
                    break;

                 default:
                     System.out.println("Error en el sistema");

             }
        }while(opcion!=7);

    }

    // Metodo Marshalling
    public static void MarshallingXML() {


        // Instanciamos lista de casetas y ejecutamos leercasetas en la ruta correspondiente
        ListaDeCasetas listaDeCasetas = new ListaDeCasetas();
        listaDeCasetas.leerCasetas("./src/main/java/org/example/pruebaevaluable/casetas.txt");

        try {


            // Iniciamos el contexto para poder inicializar el marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(ListaDeCasetas.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);


            // Añadimos la lista y el archivo en el cual generaremos el xml
            marshaller.marshal(listaDeCasetas, new File("casetas.xml"));

            // Imprimimos el resultado por pantalla una vez generado
            System.out.println("Archivo casetas.xml generado");

        } catch (Exception e) {
            e.printStackTrace();
        }



    }



    // Metodo Unmarshalling
    public static void UnMarshallingXMl() throws JAXBException {

        // Creamos un String que defina el nombre de nuestro archivo
        String xmlFilePath = "casetas.xml";

        // Iniciamos el contexto y el unmarshaller
        JAXBContext jaxbContext = JAXBContext.newInstance(ListaDeCasetas.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        // Generamos el objeto listafromxml para poder hacer el unmarshaller a nuestro xml
        ListaDeCasetas listafromxml = (ListaDeCasetas) unmarshaller.unmarshal(new File(xmlFilePath));

        // Imprimimos la lista de casetas desde el objeto listafromxml para comprobar si todo funciona correctamente
        System.out.println("Mostrando Listas de casetas ");
        listafromxml.mostrarCasetas();


    }


    // Metodo mostrarCaseta
    public static void mostrarCasetaX() throws JAXBException {


        // Inicializamos un scanner para pedir un id
        Scanner sc=new Scanner(System.in);
        System.out.println("Escribe el número de id de la caseta");
        int idcaseta= sc.nextInt();


        // Hacemos el unmarshaller a nuestro archivo xml
        String xmlFilePath = "casetas.xml";
        JAXBContext jaxbContext = JAXBContext.newInstance(ListaDeCasetas.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        ListaDeCasetas listafromxml2 = (ListaDeCasetas) unmarshaller.unmarshal(new File(xmlFilePath));

        boolean encontrado = false;


        // Una vez tenemos el objeto listafromxml2 operativo para poder ser manipulado
        // Generamos un forEach en el cual recorremos todos los objetos del interior
            for (CasetaFeria c : listafromxml2.getCasetaferia()) {


                // Si el id que elegimos al principio se corresponde con el de alguna caseta, el bucle lo encontrará
                if (idcaseta == c.getId()) {
                    System.out.println("Caseta encontrada");
                    System.out.println(c.toString());
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("La caseta no ha sido encontrada");
            }

    }


    // Metodo MarshallingJSON
    public static void  MarshallingJSON(){

        // Repetimos el proceso anterior
        String jsonFilePath = "casetas.json";
        ListaDeCasetas listaDeCasetas = new ListaDeCasetas();
        listaDeCasetas.leerCasetas("./src/main/java/org/example/pruebaevaluable/casetas.txt");

        // Inicializamos un objeto Mapper para poder escribir nuestros objetos en un archivo json
        try {

            // Utilizamos WritterWithDefaultPrettyPrinter para que se estructure de una manera más estética
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonFilePath), listaDeCasetas);
            System.out.println("Objetos CasetaFeria serializados a JSON y guardados en " + jsonFilePath);
        } catch (IOException e) {
            System.err.println("Error al generar el archivo JSON: " + e.getMessage());
        }
    }


    // Metodo UnmarshallingJSON
    public static void UnmarshallingJSON() throws JAXBException {
        String jsonFilePath = "casetas.json";


        //Repetimos el proceso de nuestro Unmarshalling anterior pero esta vez
        // Con los objetos y elementos adecuados al archivo JSON
        try {

            ObjectMapper objectMapper = new ObjectMapper();
            ListaDeCasetas listafromjson = objectMapper.readValue(new File(jsonFilePath), ListaDeCasetas.class);

            for (CasetaFeria c : listafromjson.getCasetaferia()) {
                System.out.println(c.toString());
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo JSON: " + e.getMessage());
        }


    }


    // Y finalmente, utilizamos el mismo mecanismo que nuestra anterior función
    // para poder encontrar la caseta requerida en nuestro archivo JSON
    public static void mostrarCasetaJson() throws IOException {

        String jsonFilePath = "casetas.json";
        Scanner sc=new Scanner(System.in);
        System.out.println("Escribe el número de id de la caseta");
        int idcaseta= sc.nextInt();

            ObjectMapper objectMapper = new ObjectMapper();
            ListaDeCasetas listafromjson = objectMapper.readValue(new File(jsonFilePath), ListaDeCasetas.class);

        boolean encontrado = false;

        for (CasetaFeria c : listafromjson.getCasetaferia()) {

            if (idcaseta == c.getId()) {
                System.out.println("Caseta encontrada");
                System.out.println(c.toString());
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("La caseta no ha sido encontrada");
        }

    }
}




