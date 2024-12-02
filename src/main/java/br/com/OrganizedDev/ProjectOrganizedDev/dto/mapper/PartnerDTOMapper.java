package br.com.OrganizedDev.ProjectOrganizedDev.dto.mapper;
import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.PartnerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Partner;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PartnerDTOMapper implements Function<Partner, PartnerDTO> {

    @Override
    public PartnerDTO apply(Partner partner) {
        return new PartnerDTO(
                partner.getName(),
                partner.getEmail()
        );
    }
}
