package iotinfra.device.controller;

import iotinfra.device.dto.DeviceDto;
import iotinfra.device.dto.DeviceResource;
import iotinfra.device.dto.DevicesResource;
import iotinfra.device.model.Device;
import iotinfra.device.service.DeviceService;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/device")
public class DeviceController {

	public static final String X_OWNER_ID = "X-OwnerId";
	private final DeviceService deviceService;
	private final ConversionService conversionService;

	public DeviceController(DeviceService deviceService, ConversionService conversionService) {
		this.deviceService = deviceService;
		this.conversionService = conversionService;
	}

	@GetMapping("/{id}")
	public DeviceResource findById(@RequestHeader(X_OWNER_ID) String ownerId, @PathVariable String id) {
		Device data = deviceService.findByIdAndOwnerId(id, ownerId);
		return new DeviceResource(data);
	}

	@GetMapping()
	public DevicesResource findAllByOwnerId(@RequestHeader(X_OWNER_ID) String ownerId,
											@RequestParam(defaultValue = "0") int page,
											@RequestParam(defaultValue = "30") int size,
											@RequestParam(required = false) String sort) {
		Page<Device> data = deviceService.findAllByTenantId(ownerId,
			PageRequest.of(page, size, Sort.by(StringUtils.isEmpty(sort) ? "id" : sort)));

		return new DevicesResource(ownerId, data);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DeviceResource save(@RequestHeader(X_OWNER_ID) String ownerId, @RequestBody DeviceDto deviceDto) throws Exception {
		Device device = this.conversionService.convert(deviceDto, Device.class);
		if (device != null) {
			device.setOwnerId(ownerId);
		} else {
			throw new Exception("device is null in DeviceController.java");
		}
		return new DeviceResource(deviceService.save(device));
	}
}
