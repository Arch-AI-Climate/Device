package iotinfra.device.dto;

import iotinfra.device.controller.DeviceController;
import iotinfra.device.model.Device;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class DeviceResource extends EntityModel<Device> {
	public DeviceResource() {

	}

	public DeviceResource(Device device) {
		super(device,
			(Iterable<Link>) linkTo(methodOn(DeviceController.class).findById(device.getOwnerId(), device.getId()))
				.withSelfRel()
				.withType("GET"));
	}

}
