package Educacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Instituto")
public class Instituto {
    @Id
    @Column(name = "id", insertable = false, nullable = false)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "n_alumnos")
    private Integer nAlumnos;

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

    public Integer getNAlumnos() {
        return nAlumnos;
    }

    public void setNAlumnos(Integer nAlumnos) {
        this.nAlumnos = nAlumnos;
    }

    public String toString() {
        return "Instituto{id=" + id +
                ", nombre=" + nombre +
                ", nAlumnos=" + nAlumnos +
                "}";
    }
}