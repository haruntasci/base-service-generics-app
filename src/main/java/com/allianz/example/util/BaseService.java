package com.allianz.example.util;

import com.allianz.example.util.dbutil.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public abstract class BaseService<DTO extends BaseDTO, Entity extends BaseEntity, RequestDTO extends BaseDTO, Repository
        extends IBaseRepository<Entity, Long>, Mapper extends IBaseMapper<DTO, Entity, RequestDTO>> {


    private final Repository repository;
    private final Mapper mapper;

    protected BaseService(Repository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DTO createDTO(RequestDTO dto) {
        Entity entity = mapper.requestDTOToEntity(dto);
        entity = repository.save(entity);
        return mapper.entityToDTO(entity);
    }

    public List<DTO> getAll() {
        List<Entity> entityList = repository.findAll();
        return mapper.entityListToDTOList(entityList);
    }

    public DTO getByUUID(UUID uuid) {
        Optional<Entity> entityOptional = repository.findByUuid(uuid);
        return entityOptional.map(mapper::entityToDTO).orElse(null);
    }

    @Transactional
    public int deleteByUUID(UUID uuid) {
        return repository.deleteByUuid(uuid);
    }
}