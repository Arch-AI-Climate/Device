package iotinfra.device.dto;

import iotinfra.device.controller.DeviceController;
import iotinfra.device.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.PagedModel;

import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class DevicesResource extends PagedModel<DeviceResource> {

	public DevicesResource(){

	}

	public DevicesResource(String ownerId, Page<Device> data) {
		super(
			data.getContent().stream().map(DeviceResource::new).collect(Collectors.toList()),
			new PagedModel.PageMetadata(
				data.getSize(), data.getNumber(), data.getTotalElements(), data.getTotalPages()
			)
		);

		addSelfLink(ownerId, data);

		if(data.getNumber() < data.getTotalPages() - 1) {
			addNavigationLink(ownerId, data, data.getPageable().getPageNumber() + 1, IanaLinkRelations.NEXT);
		}
		if(data.getNumber() > 0) {
			addNavigationLink(ownerId, data, data.getPageable().getPageNumber() - 1, IanaLinkRelations.PREV);
		}
	}

	private void addSelfLink(String tenantId, Page<Device> data) {
		this.add(linkTo(methodOn(DeviceController.class)
			.findAllByOwnerId(tenantId, data.getPageable().getPageNumber(),
				data.getTotalPages(),
				data.getSort().toString())
		)
			.withSelfRel()
			.expand()
			.withType("GET"));
	}

	private void addNavigationLink(String ownerId, Page<Device> data, int page, LinkRelation prev) {
		this.add(linkTo(methodOn(DeviceController.class)
			.findAllByOwnerId(ownerId, page,
				data.getPageable().getPageSize(),
				data.getSort().toString())
		)
			.withRel(prev)
			.expand()
			.withType("GET"));
	}
}
