package com.andreadangiolillo.basecrudapp.services.impl;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Answers.CALLS_REAL_METHODS;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AbstractCrudServiceImplTest {

    private AbstractCrudServiceImpl<MyEntity, Long> sut;

    @Mock
    private JpaRepository<MyEntity, Long> repository;

    @BeforeEach
    void setup(){
        sut = mock(AbstractCrudServiceImpl.class, withSettings()
                .useConstructor(repository)
                .defaultAnswer(CALLS_REAL_METHODS));
    }

    @Test
    @DisplayName("GIVEN entity WHEN save THEN call repository.save and return it")
    void given_Entity_when_Save_then_CallRepositorySaveAndReturn() {
        MyEntity entity = new MyEntity();
        when(repository.save(entity)).thenReturn(entity);

        MyEntity result = sut.save(entity);

        assertSame(entity, result);
        verify(repository, times(1)).save(entity);
    }

    @Test
    @DisplayName("GIVEN repository with data WHEN findAll THEN return list")
    void given_RepositoryWithData_when_FindAll_then_ReturnList() {
        List<MyEntity> expected = List.of(new MyEntity(), new MyEntity());
        when(repository.findAll()).thenReturn(expected);

        List<MyEntity> result = sut.findAll();

        assertEquals(expected, result);
        verify(repository, times(1)).findAll();
    }

    @Test
    @DisplayName("GIVEN ID exists WHEN findById THEN return optional of entity")
    void given_IdExists_when_FindById_then_ReturnOptional() {
        MyEntity entity = new MyEntity();
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<MyEntity> result = sut.findById(1L);

        assertTrue(result.isPresent());
        assertSame(entity, result.get());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("GIVEN ID not exists WHEN deleteById THEN throw EntityNotFoundException")
    void given_IdNotExists_when_DeleteById_then_ThrowException() {
        when(repository.existsById(1L)).thenReturn(false);

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> sut.deleteById(1L));

        assertEquals("Entity with id 1 not found.", ex.getMessage());
        verify(repository, times(1)).existsById(1L);
    }

    @Test
    @DisplayName("GIVEN ID exists WHEN deleteById THEN call deleteById on repository")
    void given_IdExists_when_DeleteById_then_DeleteEntity() {
        when(repository.existsById(1L)).thenReturn(true);

        sut.deleteById(1L);

        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("GIVEN ID not exists WHEN update THEN throw EntityNotFoundException")
    void given_IdNotExists_when_Update_then_ThrowException() {
        MyEntity entity = new MyEntity();
        when(repository.existsById(1L)).thenReturn(false);

        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> sut.update(1L, entity));

        assertEquals("Entity with ID 1 not found", ex.getMessage());
        verify(repository, times(1)).existsById(1L);
    }

    @Test
    @DisplayName("GIVEN ID exists WHEN update THEN save entity")
    void given_IdExists_when_Update_then_SaveEntity() {
        MyEntity entity = new MyEntity();
        when(repository.existsById(1L)).thenReturn(true);
        when(repository.save(entity)).thenReturn(entity);

        MyEntity result = sut.update(1L, entity);

        assertSame(entity, result);
        verify(repository, times(1)).existsById(1L);
        verify(repository, times(1)).save(entity);
    }

    static class MyEntity{
    }

}