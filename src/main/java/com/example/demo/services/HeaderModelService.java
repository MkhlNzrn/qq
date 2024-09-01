package com.example.demo.services;

import com.example.demo.entities.HeaderModel;
import com.example.demo.repositories.HeaderModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HeaderModelService {

    private final HeaderModelRepository headerModelRepository;

    @Autowired
    public HeaderModelService(HeaderModelRepository headerModelRepository) {
        this.headerModelRepository = headerModelRepository;
    }

    public HeaderModel saveHeaderModel(HeaderModel headerModel) {
        return headerModelRepository.save(headerModel);
    }

    public Optional<HeaderModel> getHeaderModelById(Long id) {
        return headerModelRepository.findById(id);
    }

    public List<HeaderModel> getAllHeaderModels() {
        return headerModelRepository.findAll();
    }

    public HeaderModel updateHeaderModel(Long id, HeaderModel updatedHeaderModel) {
        Optional<HeaderModel> existingHeaderModel = headerModelRepository.findById(id);
        if (existingHeaderModel.isPresent()) {
            HeaderModel headerModel = existingHeaderModel.get();
            headerModel.setLogoUrl(updatedHeaderModel.getLogoUrl());
            headerModel.setDeliveryInfo(updatedHeaderModel.getDeliveryInfo());
            headerModel.setWorkingHours(updatedHeaderModel.getWorkingHours());
            headerModel.setCity(updatedHeaderModel.getCity());
            headerModel.setPhoneNumber(updatedHeaderModel.getPhoneNumber());
            headerModel.setCallbackRequest(updatedHeaderModel.getCallbackRequest());
            headerModel.setNavMenuItems(updatedHeaderModel.getNavMenuItems());
            headerModel.setNavMenuLinks(updatedHeaderModel.getNavMenuLinks());
            return headerModelRepository.save(headerModel);
        } else {
            return null;
        }
    }

    public void deleteHeaderModel(Long id) {
        headerModelRepository.deleteById(id);
    }
}
