package com.bank.publicinfo.model;

import com.bank.publicinfo.audit.AuditListener;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

/**
 *Абстрактный класс BaseEntity является родительским классом для сущностей Registration, Passport, ActualRegistration,
 * Profile и AccountDetailsId. BaseEntity хранит в себе данные об идентификаторе, времени создания и последнего
 * редактирования объектов, а так же пользователе, создавшем и редактировавшем её.
 * Аннотация @MappedSuperclass сообщает, что BaseEntity является родительским для классов-сущностей, но при этом
 * не будет являться сущностью сам по себе и не будет отображаться в базе данных.
 * Аннотация @EntityListeners(AuditListener.class) сообщает, что жизненный цикл экземпляров классов-потомков
 * BaseEntity (создание, обновление и удаление) будет прослушиваться классом AuditListener
 */

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditListener.class)
public abstract class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Создавший объект пользователь не может быть пустым")
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @NotEmpty(message = "Время создания объекта не может быть пустым")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}
