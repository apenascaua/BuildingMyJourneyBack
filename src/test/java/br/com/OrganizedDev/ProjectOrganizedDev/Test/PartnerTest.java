package br.com.OrganizedDev.ProjectOrganizedDev.Test;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.PartnerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Partner;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.PartnerRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.service.PartnerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PartnerTest {

    @InjectMocks
    private PartnerServiceImpl partnerService;

    @Mock
    private PartnerRepository partnerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPartners() {
        List<Partner> partners = new ArrayList<>();
        partners.add(new Partner());
        partners.add(new Partner());

        when(partnerRepository.findAll()).thenReturn(partners);

        List<PartnerDTO> result = partnerService.getAllPartnersFromDTO();
        assertEquals(2, result.size());
        verify(partnerRepository, times(1)).findAll();
    }

    @Test
    void testGetPartnerById() {
        Partner partner = new Partner();
        partner.setId(1L);

        when(partnerRepository.findById(1L)).thenReturn(Optional.of(partner));

        Optional<Partner> result = partnerService.getPartnerById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(partnerRepository, times(1)).findById(1L);
    }

    @Test
    void testCreatePartner() {
        Partner partner = new Partner();
        partner.setName("Test Partner");

        when(partnerRepository.save(any(Partner.class))).thenReturn(partner);

        Partner result = partnerService.createPartner(partner);
        assertNotNull(result);
        assertEquals("Test Partner", result.getName());
        verify(partnerRepository, times(1)).save(partner);
    }

    @Test
    void testUpdatePartner() {
        Partner existingPartner = new Partner();
        existingPartner.setId(1L);
        existingPartner.setName("Existing Partner");

        Partner partnerDetails = new Partner();
        partnerDetails.setName("Updated Partner");

        when(partnerRepository.findById(1L)).thenReturn(Optional.of(existingPartner));
        when(partnerRepository.save(any(Partner.class))).thenReturn(existingPartner);

        Optional<Partner> result = partnerService.updatePartner(1L, partnerDetails);
        assertTrue(result.isPresent());
        assertEquals("Updated Partner", result.get().getName());
        verify(partnerRepository, times(1)).findById(1L);
        verify(partnerRepository, times(1)).save(existingPartner);
    }

    @Test
    void testDeletePartner() {
        Partner partner = new Partner();
        partner.setId(1L);

        when(partnerRepository.findById(1L)).thenReturn(Optional.of(partner));
        doNothing().when(partnerRepository).delete(partner);

        boolean result = partnerService.deletePartner(1L);
        assertTrue(result);
        verify(partnerRepository, times(1)).findById(1L);
        verify(partnerRepository, times(1)).delete(partner);
    }

    @Test
    void testDeletePartnerNotFound() {
        when(partnerRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = partnerService.deletePartner(1L);
        assertFalse(result);
        verify(partnerRepository, times(1)).findById(1L);
        verify(partnerRepository, never()).delete(any(Partner.class));
    }
}
