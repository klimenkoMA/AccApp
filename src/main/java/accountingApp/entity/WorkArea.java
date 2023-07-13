package accountingApp.entity;

import javax.persistence.*;
import java.util.List;

/**
 * Название заболевания
 */
@Entity
@Table(name = "WORK_AREA") //aeger_captions
public class WorkArea {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID") //c_aeger
	//ИНН заболевания
	private int id;
	@Column(name = "AREA_NAME") //caption
	// Название заболевания
	// nvarchar(25)
	private String areaName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
