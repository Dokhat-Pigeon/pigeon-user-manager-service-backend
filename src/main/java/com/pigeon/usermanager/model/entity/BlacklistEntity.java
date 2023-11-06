package com.pigeon.usermanager.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Модель черного списка пользователя
 */
@Entity
@Table(schema = "public", name = "blacklist")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlacklistEntity {

    /**
     * ID черного списка
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_blacklist_sequence")
    @SequenceGenerator(name = "hibernate_blacklist_sequence", allocationSize = 1)
    private Long id;

    /**
     * Владелец черного списка
     */
    @ManyToOne
    private UserEntity owner;

    /**
     * Пользователь в черном списке
     */
    @ManyToOne
    private UserEntity user;

    /**
     * Время создания записи
     * <p>
     * {@link CreationTimestamp} присваивает значение автоматически во время сохранения.
     */
    @Column(name = "created_date")
    @CreationTimestamp
    private ZonedDateTime createdDate;
}
