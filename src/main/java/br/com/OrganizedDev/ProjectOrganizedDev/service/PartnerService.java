package br.com.OrganizedDev.ProjectOrganizedDev.service;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.PartnerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Partner;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    List<Partner> getAllPartners();

    List<PartnerDTO> getAllPartnersFromDTO();

    Optional<Partner> getPartnerById(Long id);

    Partner createPartner(Partner partner);

    Optional<Partner> updatePartner(Long id, Partner partnerDetails);

    boolean deletePartner(Long id);

    List<Partner> getPartnerByTaskId(Long id);

}
