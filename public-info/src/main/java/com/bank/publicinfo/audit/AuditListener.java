package com.bank.publicinfo.audit;

import com.bank.publicinfo.model.Audit;
import com.bank.publicinfo.model.BaseClass;
import com.bank.publicinfo.repositories.AuditRepository;
import com.bank.publicinfo.util.EntityJsonBeforeUpdateSaver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Класс AuditListener предназначен для отслеживания жизненного цикла экземпляров BaseEntity
 */
@Configurable
public class AuditListener {
    private ZoneOffset offset = ZoneOffset.UTC;

    private BeanFactory beanFactory;
    private ObjectMapper objectMapper;

    @Autowired
    public AuditListener(BeanFactory beanFactory, ObjectMapper objectMapper) {
        this.beanFactory = beanFactory;
        this.objectMapper = objectMapper;
    }

    public AuditListener() {
    }

    /**
     * Метод prePersist вызывается перед сохранением сущности
     * и сохраняет в сущности информацию о создании.
     *
     * @param entity Объект сущности, который будет сохранен.
     */
    @PrePersist
    public void prePersist(BaseClass entity) throws JsonProcessingException {
        AuditRepository auditRepository = beanFactory.getBean(AuditRepository.class);

        String createdBy = "Somebody";
        OffsetDateTime createdAt = OffsetDateTime.now();

        entity.setCreatedAt(createdAt);
        entity.setCreatedBy(createdBy);
    }

    /**
     * Метод postPersist вызывается после сохранения сущности
     * и сохраняет информацию об аудите операции создания.
     *
     * @param entity Объект сущности, который будет сохранен.
     */
    @PostPersist
    public void postPersist(BaseClass entity) throws JsonProcessingException {
        AuditRepository auditRepository = beanFactory.getBean(AuditRepository.class);

        Audit audit = Audit.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("CREATE")
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .entityJson(objectMapper.writeValueAsString(entity))
                .build();

        auditRepository.save(audit);
    }

    /**
     * Метод preUpdate вызывается перед обновлением сущности
     * и сохраняет информацию об обновлении в сущности.
     *
     * @param entity Объект сущности, который будет обновлен.
     */
    @PreUpdate
    public void preUpdate(BaseClass entity) throws JsonProcessingException {
        entity.setModifiedBy("Somebody");
        entity.setModifiedAt(OffsetDateTime.now());
    }

    /**
     * Метод postUpdate вызывается после обновления сущности
     * и сохраняет информацию об аудите операции редактирования.
     *
     * @param entity Объект сущности, который будет обновлен.
     */
    @PostUpdate
    public void postUpdate(BaseClass entity) throws JsonProcessingException {
        AuditRepository auditRepository = beanFactory.getBean(AuditRepository.class);
        String newJson = objectMapper.writeValueAsString(entity);
        String entityJson = EntityJsonBeforeUpdateSaver.getEntityJson();

        Audit audit = Audit.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("UPDATE")
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .modifiedBy(entity.getModifiedBy())
                .modifiedAt(entity.getModifiedAt().toLocalDateTime())
                .newEntityJson(newJson)
                .entityJson(entityJson)
                .build();

        auditRepository.save(audit);
    }

    /**
     * Метод preRemove вызывается перед удалением сущности
     * и сохраняет информацию об аудите операции удаления.
     *
     * @param entity Объект сущности, который будет удален.
     */
    @PreRemove
    public void preRemove(BaseClass entity) throws JsonProcessingException {
        AuditRepository auditRepository = beanFactory.getBean(AuditRepository.class);
        String entityJson = objectMapper.writeValueAsString(entity);

        Audit audit = Audit.builder()
                .entityType(entity.getClass().getSimpleName())
                .operationType("DELETE")
                .createdBy(entity.getCreatedBy())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .modifiedBy("Somebody")
                .modifiedAt(OffsetDateTime.now().toLocalDateTime())
                .entityJson(entityJson)
                .build();

        auditRepository.save(audit);
    }
}


