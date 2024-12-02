package br.com.OrganizedDev.ProjectOrganizedDev.Test;

import br.com.OrganizedDev.ProjectOrganizedDev.dto.response.CostumerDTO;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.AppUser;
import br.com.OrganizedDev.ProjectOrganizedDev.entity.Costumer;
import br.com.OrganizedDev.ProjectOrganizedDev.repository.CostumerRepository;
import br.com.OrganizedDev.ProjectOrganizedDev.service.CostumerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CostumerTest {

    @Mock
    private CostumerRepository costumerRepository;

    @InjectMocks
    private CostumerServiceImpl costumerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCostumersTest() {
        Costumer costumer1 = new Costumer();
        Costumer costumer2 = new Costumer();
        List<Costumer> costumers = Arrays.asList(costumer1, costumer2);

        when(costumerRepository.findAll()).thenReturn(costumers);

        List<CostumerDTO> result = costumerService.getAllCostumersFromDTO();

        assertEquals(2, result.size());
        verify(costumerRepository, times(1)).findAll();
    }

    @Test
    void getCostumerByIdTest() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));

        Optional<Costumer> result = costumerService.getCostumerById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(costumerRepository, times(1)).findById(1L);
    }

    @Test
    void createCostumerTest() {
        AppUser appUser = new AppUser();
        Costumer costumer = new Costumer();
        costumer.setAppUser(appUser);

        when(costumerRepository.save(costumer)).thenReturn(costumer);

        Costumer result = costumerService.createCostumer(costumer);

        assertNotNull(result);
        assertEquals(costumer, appUser.getCostumer());
        verify(costumerRepository, times(1)).save(costumer);
    }

    @Test
    void updateCostumerTest() {
        AppUser appUser = new AppUser();
        Costumer existingCostumer = new Costumer();
        existingCostumer.setId(1L);
        existingCostumer.setCnpj("oldCnpj");
        existingCostumer.setNomeFantasia("oldNome");
        existingCostumer.setAppUser(appUser);

        Costumer newCostumerDetails = new Costumer();
        newCostumerDetails.setCnpj("newCnpj");
        newCostumerDetails.setNomeFantasia("newNome");

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(existingCostumer));
        when(costumerRepository.save(any(Costumer.class))).thenReturn(existingCostumer);

        Optional<Costumer> result = costumerService.updateCostumer(1L, newCostumerDetails);

        assertTrue(result.isPresent());
        assertEquals("newCnpj", result.get().getCnpj());
        assertEquals("newNome", result.get().getNomeFantasia());
        verify(costumerRepository, times(1)).findById(1L);
        verify(costumerRepository, times(1)).save(existingCostumer);
    }

    @Test
    void deleteCostumerTest() {
        Costumer costumer = new Costumer();
        costumer.setId(1L);

        when(costumerRepository.findById(1L)).thenReturn(Optional.of(costumer));

        boolean result = costumerService.deleteCostumer(1L);

        assertTrue(result);
        verify(costumerRepository, times(1)).findById(1L);
        verify(costumerRepository, times(1)).delete(costumer);
    }

    @Test
    void deleteCostumerNotFoundTest() {
        when(costumerRepository.findById(1L)).thenReturn(Optional.empty());

        boolean result = costumerService.deleteCostumer(1L);

        assertFalse(result);
        verify(costumerRepository, times(1)).findById(1L);
        verify(costumerRepository, never()).delete(any(Costumer.class));
    }
}
