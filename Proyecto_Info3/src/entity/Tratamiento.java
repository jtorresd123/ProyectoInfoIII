package entity;

// Generated 18-abr-2015 2:20:08 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TratamientoId generated by hbm2java
 */
@Entity
@Table(name = "tratamiento", schema = "public")
public class Tratamiento implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1714416043477659785L;
	private int id;
	private String descripcion;
	private char estado;

	public Tratamiento() {
	}

	public Tratamiento(int id, String descripcion, char estado) {
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "descripcion", nullable = false, length = 80)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "estado", nullable = false, length = 1)
	public char getEstado() {
		return this.estado;
	}

	public void setEstado(char estado) {
		this.estado = estado;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Tratamiento))
			return false;
		Tratamiento castOther = (Tratamiento) other;

		return (this.getId() == castOther.getId())
				&& ((this.getDescripcion() == castOther.getDescripcion()) || (this
						.getDescripcion() != null
						&& castOther.getDescripcion() != null && this
						.getDescripcion().equals(castOther.getDescripcion())))
				&& (this.getEstado() == castOther.getEstado());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37
				* result
				+ (getDescripcion() == null ? 0 : this.getDescripcion()
						.hashCode());
		result = 37 * result + this.getEstado();
		return result;
	}

}