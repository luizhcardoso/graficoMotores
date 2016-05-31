package bancoDeDados;


import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;



@Entity
@Table
public class Grafico implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo = 0;
	
//	@Column(nullable = true)
//	@Temporal(TemporalType.TIMESTAMP)
//	private Date data;
	
	@Column(nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data;
	
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Column(nullable = true)
	private double x;
	
	@Column(nullable = true)
	private double y;

	public int getCodigo() {
		return codigo;
	}


	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
	
}
