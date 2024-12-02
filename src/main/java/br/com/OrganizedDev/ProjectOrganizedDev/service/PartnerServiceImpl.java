package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper.PartnerDTOMapper;
import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.PartnerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Partner;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.PartnerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerServiceImpl implements PartnerService {

    @Autowired
    private PartnerRepository partnerRepository;

    @Autowired
    private PartnerDTOMapper partnerDTOMapper;

    @Override
    public List<Partner> getAllPartners(){
        return partnerRepository.findAll();
    }

    @Override
    public List<PartnerDTO> getAllPartnersFromDTO() {
        return Optional.of(partnerRepository.findAll())
                .filter(list -> !list.isEmpty())
                .map(list -> list.stream().map(partnerDTOMapper).toList())
                .orElseThrow(() -> new RuntimeException("NO USERS FOUND"));
    }

    @Override
    public Optional<Partner> getPartnerById(Long id) {
        return partnerRepository.findById(id);
    }

    @Override
    @Transactional
    public Partner createPartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    @Override
    @Transactional
    public Optional<Partner> updatePartner(Long id, Partner partnerDetails) {
        return partnerRepository.findById(id).map(partner -> {
            partner.setName(partnerDetails.getName());
            partner.setEmail(partnerDetails.getEmail());
            // outros atributos...
            return partnerRepository.save(partner);
        });
    }

    @Override
    @Transactional
    public boolean deletePartner(Long id) {
        return partnerRepository.findById(id).map(partner -> {
            partnerRepository.delete(partner);
            return true;
        }).orElse(false);
    }
    @Override
    public List<Partner> getPartnerByTaskId(Long id) {
        return partnerRepository.findByTaskId(id);
    }
}
