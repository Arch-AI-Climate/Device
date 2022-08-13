package iotinfra.device.service;

import iotinfra.device.model.Device;
import iotinfra.device.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;

@Service
public class DeviceService {
	private final static Logger LOGGER = LoggerFactory.getLogger(DeviceService.class.getName());

	private final DeviceRepository deviceRepository;

	public DeviceService(DeviceRepository deviceRepository){
		this.deviceRepository = deviceRepository;
	}

	public Page<Device> findAllByTenantId(String ownerId, PageRequest of) {
		return this.deviceRepository.findAllByOwnerId(ownerId, of);
	}

	public Device save(Device device) {
		if (StringUtils.isEmpty(device.getToken())) {
			device.setToken(UUID.randomUUID().toString());
		}
		return this.deviceRepository.save(device);
	}

	public Device findByIdAndOwnerId(String id, String ownerId) {
		return this.deviceRepository.findByIdAndOwnerId(id, ownerId).orElse(null);
	}
}
