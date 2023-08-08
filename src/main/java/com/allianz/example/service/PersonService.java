package com.allianz.example.service;

import com.allianz.example.database.entity.AddressEntity;
import com.allianz.example.database.entity.PersonEntity;
import com.allianz.example.database.repository.AddressEntityRepository;
import com.allianz.example.database.repository.PersonEntityRepository;
import com.allianz.example.mapper.AddressMapper;
import com.allianz.example.mapper.PersonMapper;
import com.allianz.example.model.PersonDTO;
import com.allianz.example.model.requestDTO.AddressRequestDTO;
import com.allianz.example.model.requestDTO.PersonRequestDTO;
import com.allianz.example.util.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//bean
@Service
public class PersonService extends
        BaseService<PersonDTO, PersonEntity, PersonRequestDTO, PersonEntityRepository, PersonMapper> {


    private final PersonEntityRepository personEntityRepository;
    private final AddressEntityRepository addressEntityRepository;
    private final PersonMapper personMapper;
    private final AddressMapper addressMapper;

    protected PersonService(PersonEntityRepository repository,
                            PersonMapper mapper,
                            PersonEntityRepository personEntityRepository,
                            AddressEntityRepository addressEntityRepository,
                            PersonMapper personMapper, AddressMapper addressMapper) {
        super(repository, mapper);

        this.personEntityRepository = personEntityRepository;
        this.addressEntityRepository = addressEntityRepository;
        this.personMapper = personMapper;
        this.addressMapper = addressMapper;
    }

    public PersonDTO addAddressToPerson(UUID addressUUID, UUID personUUID) {

        AddressEntity addressEntity = addressEntityRepository.findByUuid(addressUUID).get();


        if (personEntityRepository.findByUuid(personUUID).isPresent()) {
            PersonEntity personEntity = personEntityRepository.findByUuid(personUUID).get();
            if (personEntity.getAddressEntityList() != null) {
                personEntity.getAddressEntityList().add(addressEntity);
            } else {
                personEntity.setAddressEntityList(List.of(addressEntity));
            }
            personEntityRepository.save(personEntity);
            return personMapper.entityToDTO(personEntity);
        } else {
            return null;
        }


    }
}
