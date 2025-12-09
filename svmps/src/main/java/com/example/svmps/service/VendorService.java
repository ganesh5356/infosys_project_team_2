package com.example.svmps.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.svmps.dto.VendorDto;
import com.example.svmps.entity.Vendor;
import com.example.svmps.repository.VendorRepository;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public VendorDto createVendor(VendorDto dto) {
        Vendor vendor = new Vendor();
        vendor.setName(dto.getName());
        vendor.setContactName(dto.getContactName());
        vendor.setEmail(dto.getEmail());
        vendor.setPhone(dto.getPhone());
        vendor.setAddress(dto.getAddress());
        vendor.setGstNumber(dto.getGstNumber());
        vendor.setIsActive(true);

        Vendor saved = vendorRepository.save(vendor);
        return toDto(saved);
    }

    public List<VendorDto> getAllVendors() {
        return vendorRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public VendorDto getVendorById(Long id) {
        Vendor vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));
        return toDto(vendor);
    }

    private VendorDto toDto(Vendor vendor) {
        VendorDto dto = new VendorDto();
        dto.setId(vendor.getId());
        dto.setName(vendor.getName());
        dto.setContactName(vendor.getContactName());
        dto.setEmail(vendor.getEmail());
        dto.setPhone(vendor.getPhone());
        dto.setAddress(vendor.getAddress());
        dto.setGstNumber(vendor.getGstNumber());
        dto.setIsActive(vendor.getIsActive());
        return dto;
    }

    public VendorDto updateVendor(Long id, VendorDto dto) {
    Vendor vendor = vendorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

    vendor.setName(dto.getName());
    vendor.setContactName(dto.getContactName());
    vendor.setEmail(dto.getEmail());
    vendor.setPhone(dto.getPhone());
    vendor.setAddress(dto.getAddress());
    vendor.setGstNumber(dto.getGstNumber());
    if (dto.getIsActive() != null) {
        vendor.setIsActive(dto.getIsActive());
    }

    Vendor updated = vendorRepository.save(vendor);
    return toDto(updated);
}

public void deleteVendor(Long id) {
    Vendor vendor = vendorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));

    // Soft delete: mark inactive
    vendor.setIsActive(false);
    vendorRepository.save(vendor);
}

}
