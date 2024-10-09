package org.example.pruebaevaluable;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "caseta")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "nombre", "titular", "aforo", "tematica" })
public class CasetaFeria {
    @XmlElement(name = "id")
    private int id;

    @XmlElement(name = "nombre")
    private String nombre;

    @XmlElement(name = "titular")
    private String titular;

    @XmlElement(name = "aforo")
    private int aforo;

    @XmlElement(name = "tematica")
    private String tematica;

    public CasetaFeria() {

    }




    public CasetaFeria(int id, String nombre, String titular, int aforo, String tematica) {
        this.id = id;
        this.nombre = nombre;
        this.titular = titular;
        this.aforo = aforo;
        this.tematica = tematica;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    @Override
    public String toString() {
        return "CasetaFeria {" +
                "\n  ID: " + id +
                "\n  Nombre: '" + nombre + '\'' +
                "\n  Titular: '" + titular + '\'' +
                "\n  Aforo: " + aforo +
                "\n  Temática: '" + tematica + '\'' +
                "\n}";
    }
}
