package com.andreadangiolillo.basecrudapp.controllers;

import com.andreadangiolillo.basecrudapp.services.CrudService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BaseCrudControllerTest {

    @InjectMocks
    private BaseCrudController<MyEntity, Long> sut;

    @Mock
    private CrudService<MyEntity, Long> service;

    @DisplayName("GIVEN Entity WHEN create THEN Call Save With Entity")
    @Test
    void given_Entity_when_Create_then_CallSaveWithEntity(){
        MyEntity myEntity = new MyEntity();
        when(service.save(myEntity)).thenReturn(myEntity);

        ResponseEntity<MyEntity> result = sut.create(myEntity);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertSame(myEntity, result.getBody());
        verify(service, times(1)).save(myEntity);
    }

    @DisplayName("GIVEN service returns list WHEN findAll THEN return list")
    @Test
    void given_ServiceReturnsList_when_FindAll_then_ReturnList() {
        List<MyEntity> entities = List.of(new MyEntity(), new MyEntity());
        when(service.findAll()).thenReturn(entities);

        ResponseEntity<List<MyEntity>> result = sut.findAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(entities, result.getBody());
        verify(service, times(1)).findAll();
    }

    @DisplayName("GIVEN existing ID WHEN getById THEN return entity")
    @Test
    void given_ExistingId_when_GetById_then_ReturnEntity() {
        MyEntity entity = new MyEntity();
        when(service.findById(1L)).thenReturn(Optional.of(entity));

        ResponseEntity<MyEntity> result = sut.getById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(entity, result.getBody());
        verify(service, times(1)).findById(1L);
    }

    @DisplayName("GIVEN non existing ID WHEN getById THEN return null")
    @Test
    void given_NonExistingId_when_GetById_then_ReturnNull() {
        when(service.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<MyEntity> result = sut.getById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNull(result.getBody());
        verify(service, times(1)).findById(1L);
    }

    @DisplayName("GIVEN Entity and ID WHEN update THEN Call Update And Return Entity")
    @Test
    void given_EntityAndId_when_Update_then_CallUpdateAndReturnEntity() {
        MyEntity updated = new MyEntity();
        when(service.update(1L, updated)).thenReturn(updated);

        ResponseEntity<MyEntity> result = sut.update(1L, updated);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertSame(updated, result.getBody());
        verify(service, times(1)).update(1L, updated);
    }

    @DisplayName("GIVEN ID WHEN delete THEN Call Delete And Return NoContent")
    @Test
    void given_Id_when_Delete_then_CallDeleteAndReturnNoContent() {
        ResponseEntity<Void> result = sut.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(service, times(1)).deleteById(1L);
    }

    static class MyEntity{
    }

}