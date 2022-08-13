package iotinfra.device.dto;

// DeviceDto to make API more flexible
public class DeviceDto {
	private double[] position;
	private String name;
	private String description;

	public double[] getPosition() {
		return position;
	}

	public void setPosition(double[] position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
