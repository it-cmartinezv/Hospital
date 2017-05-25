package entidades;

public class detalleOrdenMedicamentePK {
	
	private int ordenMedicamento;
	private int medicamento;
	
	public detalleOrdenMedicamentePK() {
	}

	public detalleOrdenMedicamentePK(int ordenMedicamento, int medicamento) {
		super();
		this.ordenMedicamento = ordenMedicamento;
		this.medicamento = medicamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + medicamento;
		result = prime * result + ordenMedicamento;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		detalleOrdenMedicamentePK other = (detalleOrdenMedicamentePK) obj;
		if (medicamento != other.medicamento)
			return false;
		if (ordenMedicamento != other.ordenMedicamento)
			return false;
		return true;
	}

	public int getOrdenMedicamento() {
		return ordenMedicamento;
	}

	public void setOrdenMedicamento(int ordenMedicamento) {
		this.ordenMedicamento = ordenMedicamento;
	}

	public int getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(int medicamento) {
		this.medicamento = medicamento;
	}	
}
