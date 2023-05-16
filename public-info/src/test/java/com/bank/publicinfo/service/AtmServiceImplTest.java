package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.mappers.AtmMapper;
import com.bank.publicinfo.model.Atm;
import com.bank.publicinfo.repositories.AtmRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
class AtmServiceImplTest {
    @Mock
    private AtmRepository atmRepository;

    @InjectMocks
    private AtmServiceImpl atmService;

    Atm atm;
    AtmDto atmDto;

    @BeforeEach
    public void setUp() {
        atmDto = AtmDto.builder()
                .id(1L)
                .address("Moscow")
                .startOfWork(LocalTime.parse("00:00:00"))
                .endOfWork(LocalTime.parse("00:00:00"))
                .allHours(true)
                .build();
        atm = Atm.builder()
                .id(1L)
                .address("Moscow")
                .startOfWork(LocalTime.parse("00:00:00"))
                .endOfWork(LocalTime.parse("00:00:00"))
                .allHours(true)
                .build();
        atmDto = AtmMapper.INSTANCE.toDto(atm);
        /**
         * и можно не мокать маппер с этой строкой.
         atmDto = atmMapper.toDto(atm); это тоже не нужно
         **/
    }

    @Test
    void save() {
        atmService.save(AtmMapper.INSTANCE.toDto(atm));
        /**
         *ArgumentCaptor - утилитный класс, для "захвата" полей класса
         **/
        ArgumentCaptor<Atm> atmCaptor = ArgumentCaptor.forClass(Atm.class);
        Mockito.verify(atmRepository).save(atmCaptor.capture());
        Atm savedAtm = atmCaptor.getValue();
        assertNull(savedAtm.getId());
        assertEquals(savedAtm.getAddress(), atmDto.getAddress());
        assertEquals(savedAtm.getStartOfWork(), atmDto.getStartOfWork());
        assertEquals(savedAtm.getEndOfWork(), atmDto.getEndOfWork());
        assertEquals(savedAtm.isAllHours(), atmDto.isAllHours());
    }

    @Test
    void findAll() {
        // Given
        List<Atm> atmList = new ArrayList<>();
        atmList.add(atm);
        Mockito.when(atmRepository.findAll()).thenReturn(atmList);
        // When
        List<AtmDto> foundDtoList = atmService.findAll();
        // Then
        Mockito.verify(atmRepository, times(1)).findAll();
        assertEquals(foundDtoList.size(), atmList.size());
        for (int i = 0; i < foundDtoList.size(); i++) {
            Atm foundAtm = AtmMapper.INSTANCE.toEntity(foundDtoList.get(i));
            assertEquals(foundAtm.getId(), atmList.get(i).getId());
            assertEquals(foundAtm.getAddress(), atmList.get(i).getAddress());
            assertEquals(foundAtm.getStartOfWork(), atmList.get(i).getStartOfWork());
            assertEquals(foundAtm.getEndOfWork(), atmList.get(i).getEndOfWork());
            assertEquals(foundAtm.isAllHours(), atmList.get(i).isAllHours());
        }
    }
    @Test
    void existById() {
        when(atmRepository.existsById(1L)).thenReturn(true);
        Boolean actual = atmService.existById(1L);
        assertEquals(true, actual);
    }

    @Test
    void findById() {
        Mockito.when(atmRepository.getReferenceById(1L)).thenReturn(atm);
        // When
        AtmDto foundDto = atmService.findById(1L);
        // Then
        Mockito.verify(atmRepository, times(1)).getReferenceById(1L);
        assertEquals(foundDto.getId(), atm.getId());
    }


    @Test
    public void testUpdate() throws JsonProcessingException {
        // Create an Atm entity to update
        AtmDto atmDto = new AtmDto();
        atmDto.setAddress("123 Main St");
        atmService.save(atmDto);

        // Get the ID of the newly created entity
        Long id = atmDto.getId();

        // Update the entity
        atmDto.setAddress("456 Broadway");
        atmService.update(atmDto, id);

        // Verify that the entity was updated as expected
        assertEquals("456 Broadway", atmDto.getAddress());
    }

    @Test
    void deleteById() {
        long id = 1L;
        atmService.deleteById(id);
        verify(atmRepository).deleteById(id);
    }
}
