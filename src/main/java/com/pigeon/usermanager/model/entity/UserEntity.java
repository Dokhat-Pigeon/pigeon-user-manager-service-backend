package com.pigeon.usermanager.model.entity;

import com.pigeon.usermanager.model.enums.UserRole;
import com.pigeon.usermanager.model.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Модель аккаунта пользователя
 */
@Entity
@Table(schema = "public", name = "user")
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    /**
     * ID пользователя
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hibernate_user_sequence")
    @SequenceGenerator(name = "hibernate_user_sequence", allocationSize = 1)
    private Long id;

    /**
     * Индентификатор состояния пользоватяля
     */
    private UUID state;

    /**
     * Почта пользователя
     */
    private String email;

    /**
     * Логин пользователя
     */
    private String login;

    /**
     * Имя пользователя
     */
    private String name;

    /**
     * Пароль пользователя
     */
    private String password;

    /**
     *
     */
    @Column(name = "image_name")
    private String imageName;

    /**
     * Статус пользователя в системе
     */
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    /**
     * Статус пользователя
     */
    @Enumerated(EnumType.STRING)
    private UserRole role;

    /**
     * Время создания записи
     * <p>
     * {@link CreationTimestamp} присваивает значение автоматически во время сохранения.
     */
    @Column(name = "created_date")
    @CreationTimestamp
    private ZonedDateTime createdDate;
}
