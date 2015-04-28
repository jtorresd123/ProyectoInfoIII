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
 * ParametroId generated by hbm2java
 */
@Entity
@Table(name = "parametro", schema = "public")
public class Parametro implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2537539322247607012L;
	private int id;
	private String modulo;
	private String parametro;
	private String valor;
	private Character estado;

	public Parametro() {
	}

	public Parametro(int id) {
		this.id = id;
	}

	public Parametro(int id, String modulo, String parametro, String valor,
			Character estado) {
		this.id = id;
		this.modulo = modulo;
		this.parametro = parametro;
		this.valor = valor;
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

	@Column(name = "modulo", length = 80)
	public String getModulo() {
		return this.modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	@Column(name = "parametro", length = 100)
	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Column(name = "valor")
	public String getValor() {
		return this.valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Column(name = "estado", length = 1)
	public Character getEstado() {
		return this.estado;
	}

	public void setEstado(Character estado) {
		this.estado = estado;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Parametro))
			return false;
		Parametro castOther = (Parametro) other;

		return (this.getId() == castOther.getId())
				&& ((this.getModulo() == castOther.getModulo()) || (this
						.getModulo() != null && castOther.getModulo() != null && this
						.getModulo().equals(castOther.getModulo())))
				&& ((this.getParametro() == castOther.getParametro()) || (this
						.getParametro() != null
						&& castOther.getParametro() != null && this
						.getParametro().equals(castOther.getParametro())))
				&& ((this.getValor() == castOther.getValor()) || (this
						.getValor() != null && castOther.getValor() != null && this
						.getValor().equals(castOther.getValor())))
				&& ((this.getEstado() == castOther.getEstado()) || (this
						.getEstado() != null && castOther.getEstado() != null && this
						.getEstado().equals(castOther.getEstado())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37 * result
				+ (getModulo() == null ? 0 : this.getModulo().hashCode());
		result = 37 * result
				+ (getParametro() == null ? 0 : this.getParametro().hashCode());
		result = 37 * result
				+ (getValor() == null ? 0 : this.getValor().hashCode());
		result = 37 * result
				+ (getEstado() == null ? 0 : this.getEstado().hashCode());
		return result;
	}

}