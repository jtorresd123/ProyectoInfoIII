package entity;

// Generated 18-abr-2015 2:20:08 by Hibernate Tools 4.3.1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PhclinicaId generated by hbm2java
 */
@Entity
@Table(name = "phclinica", schema = "public")
public class Phclinica implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1620260070404336337L;
	private int id;
	private int idPaciente;
	private Date fechaHclinica;
	private Integer idEnfermedad;
	private Integer idTratamiento;
	private Integer idDieta;
	private char estado;

	public Phclinica() {
	}

	public Phclinica(int id, int idPaciente, Date fechaHclinica, char estado) {
		this.id = id;
		this.idPaciente = idPaciente;
		this.fechaHclinica = fechaHclinica;
		this.estado = estado;
	}

	public Phclinica(int id, int idPaciente, Date fechaHclinica,
			Integer idEnfermedad, Integer idTratamiento, Integer idDieta,
			char estado) {
		this.id = id;
		this.idPaciente = idPaciente;
		this.fechaHclinica = fechaHclinica;
		this.idEnfermedad = idEnfermedad;
		this.idTratamiento = idTratamiento;
		this.idDieta = idDieta;
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

	@Column(name = "id_paciente", nullable = false)
	public int getIdPaciente() {
		return this.idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	@Column(name = "fecha_hclinica", nullable = false, length = 35)
	public Date getFechaHclinica() {
		return this.fechaHclinica;
	}

	public void setFechaHclinica(Date fechaHclinica) {
		this.fechaHclinica = fechaHclinica;
	}

	@Column(name = "id_enfermedad")
	public Integer getIdEnfermedad() {
		return this.idEnfermedad;
	}

	public void setIdEnfermedad(Integer idEnfermedad) {
		this.idEnfermedad = idEnfermedad;
	}

	@Column(name = "id_tratamiento")
	public Integer getIdTratamiento() {
		return this.idTratamiento;
	}

	public void setIdTratamiento(Integer idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	@Column(name = "id_dieta")
	public Integer getIdDieta() {
		return this.idDieta;
	}

	public void setIdDieta(Integer idDieta) {
		this.idDieta = idDieta;
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
		if (!(other instanceof Phclinica))
			return false;
		Phclinica castOther = (Phclinica) other;

		return (this.getId() == castOther.getId())
				&& (this.getIdPaciente() == castOther.getIdPaciente())
				&& ((this.getFechaHclinica() == castOther.getFechaHclinica()) || (this
						.getFechaHclinica() != null
						&& castOther.getFechaHclinica() != null && this
						.getFechaHclinica()
						.equals(castOther.getFechaHclinica())))
				&& ((this.getIdEnfermedad() == castOther.getIdEnfermedad()) || (this
						.getIdEnfermedad() != null
						&& castOther.getIdEnfermedad() != null && this
						.getIdEnfermedad().equals(castOther.getIdEnfermedad())))
				&& ((this.getIdTratamiento() == castOther.getIdTratamiento()) || (this
						.getIdTratamiento() != null
						&& castOther.getIdTratamiento() != null && this
						.getIdTratamiento()
						.equals(castOther.getIdTratamiento())))
				&& ((this.getIdDieta() == castOther.getIdDieta()) || (this
						.getIdDieta() != null && castOther.getIdDieta() != null && this
						.getIdDieta().equals(castOther.getIdDieta())))
				&& (this.getEstado() == castOther.getEstado());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getId();
		result = 37 * result + this.getIdPaciente();
		result = 37
				* result
				+ (getFechaHclinica() == null ? 0 : this.getFechaHclinica()
						.hashCode());
		result = 37
				* result
				+ (getIdEnfermedad() == null ? 0 : this.getIdEnfermedad()
						.hashCode());
		result = 37
				* result
				+ (getIdTratamiento() == null ? 0 : this.getIdTratamiento()
						.hashCode());
		result = 37 * result
				+ (getIdDieta() == null ? 0 : this.getIdDieta().hashCode());
		result = 37 * result + this.getEstado();
		return result;
	}

}
