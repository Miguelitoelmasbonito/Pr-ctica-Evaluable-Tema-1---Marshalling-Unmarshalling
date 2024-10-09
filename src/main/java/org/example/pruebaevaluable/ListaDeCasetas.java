package org.example.pruebaevaluable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name= "feria")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaDeCasetas {

    @XmlElement(name = "caseta")
    private List<CasetaFeria> casetaferia;


    public ListaDeCasetas() {

    }


    public List<CasetaFeria> getCasetaferia() {
        return casetaferia;
    }

    public void setCasetaferia(List<CasetaFeria> casetaferia) {
        this.casetaferia = casetaferia;
    }

    public void leerCasetas(String rutaArchivo) {
        casetaferia = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int id = 1; // Identificador único
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(" - ");
                if (partes.length == 4) {
                    CasetaFeria caseta = new CasetaFeria(id++, partes[0], partes[1], Integer.parseInt(partes[2]), partes[3]);
                    casetaferia.add(caseta);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void mostrarCasetas() {
        for (CasetaFeria caseta : casetaferia) {
            System.out.println(caseta);
        }
    }

}











