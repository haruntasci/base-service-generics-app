package com.allianz.example.mapper;

import com.allianz.example.database.entity.PersonEntity;
import com.allianz.example.model.PersonDTO;
import com.allianz.example.model.requestDTO.PersonRequestDTO;
import com.allianz.example.util.IBaseMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PersonMapper implements IBaseMapper<PersonDTO, PersonEntity, PersonRequestDTO> {

    private final AddressMapper addressMapper;

    public PersonMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    @Override
    public PersonDTO entityToDTO(PersonEntity entity) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setTc(entity.getTc());
        personDTO.setName(entity.getName());
        personDTO.setSurname(entity.getSurname());
        personDTO.setId(entity.getId());
        personDTO.setUuid(entity.getUuid());
        personDTO.setBirthYear(entity.getBirthYear());
        personDTO.setCreationDate(entity.getCreationDate());
        personDTO.setUpdatedDate(entity.getUpdatedDate());
        personDTO.setAddressList(addressMapper.entityListToDTOList(entity.getAddressEntityList()));

        return personDTO;
    }

    @Override
    public PersonEntity dtoToEntity(PersonDTO dto) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setName(dto.getName());
        personEntity.setSurname(dto.getSurname());
        personEntity.setBirthYear(dto.getBirthYear());
        personEntity.setTc(dto.getTc());
        personEntity.setId(dto.getId());
        personEntity.setCreationDate(dto.getCreationDate());
        personEntity.setUpdatedDate(dto.getUpdatedDate());
        personEntity.setAddressEntityList(addressMapper.dtoListTOEntityList(dto.getAddressList()));

        return personEntity;
    }

    @Override
    public List<PersonDTO> entityListToDTOList(List<PersonEntity> personEntities) {
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (PersonEntity entity : personEntities) {
            personDTOList.add(entityToDTO(entity));
        }
        return personDTOList;
    }

    @Override
    public List<PersonEntity> dtoListTOEntityList(List<PersonDTO> personDTOS) {
        List<PersonEntity> personEntityList = new ArrayList<>();
        for (PersonDTO personDTO : personDTOS) {
            personEntityList.add(dtoToEntity(personDTO));
        }
        return personEntityList;
    }

    @Override
    public PersonEntity requestDTOToEntity(PersonRequestDTO dto) {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setId(dto.getId());
        personEntity.setUuid(dto.getUuid());
        personEntity.setName(dto.getName());
        personEntity.setSurname(dto.getSurname());
        personEntity.setCreationDate(dto.getCreationDate());
        personEntity.setUpdatedDate(dto.getUpdatedDate());
        personEntity.setBirthYear(dto.getBirthYear());
        personEntity.setTc(dto.getTc());
        return personEntity;
    }
}
