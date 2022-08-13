package iotinfra.device.repository;

import iotinfra.device.model.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DeviceRepository extends MongoRepository<Device, String> {
	Optional<Device> findByIdAndOwnerIdAndToken(String id, String ownerId, String token);
	Optional<Device> findByIdAndOwnerId(String id, String ownerId);
	Page<Device> findAllByOwnerId(String ownerId, PageRequest of);
}
