package iotinfra.device.converter;

import iotinfra.device.dto.DeviceDto;
import iotinfra.device.model.Device;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DeviceDtoToDeviceConverter implements Converter<DeviceDto, Device> {

	@Override
	public Device convert(DeviceDto deviceDto) {
		Device device = new Device();
		device.setDescription(deviceDto.getDescription());
		device.setPosition(deviceDto.getPosition());
		device.setName(deviceDto.getName());
		return device;
	}
}
