package com.example.demo.services;

import com.example.demo.entities.FooterModel;
import com.example.demo.repositories.FooterModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FooterModelService {

    private final FooterModelRepository footerModelRepository;

    @Autowired
    public FooterModelService(FooterModelRepository footerModelRepository) {
        this.footerModelRepository = footerModelRepository;
    }

    public FooterModel saveFooterModel(FooterModel footerModel) {
        return footerModelRepository.save(footerModel);
    }

    public Optional<FooterModel> getFooterModelById(Long id) {
        return footerModelRepository.findById(id);
    }

    public List<FooterModel> getAllFooterModels() {
        return footerModelRepository.findAll();
    }

    public FooterModel updateFooterModel(Long id, FooterModel updatedFooterModel) {
        Optional<FooterModel> existingFooterModel = footerModelRepository.findById(id);
        if (existingFooterModel.isPresent()) {
            FooterModel footerModel = existingFooterModel.get();
            footerModel.setLogoUrl(updatedFooterModel.getLogoUrl());
            footerModel.setCompanyName(updatedFooterModel.getCompanyName());
            footerModel.setInn(updatedFooterModel.getInn());
            footerModel.setBankDetails(updatedFooterModel.getBankDetails());
            footerModel.setAddresses(updatedFooterModel.getAddresses());
            footerModel.setEmail(updatedFooterModel.getEmail());
            footerModel.setPhone(updatedFooterModel.getPhone());
            footerModel.setSocialNetworks(updatedFooterModel.getSocialNetworks());
            return footerModelRepository.save(footerModel);
        } else {
            return null;
        }
    }

    public void deleteFooterModel(Long id) {
        footerModelRepository.deleteById(id);
    }
}
