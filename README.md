# pigeon-user-manager-service
Сервис для обработки и хранения информации о пользователях

## Локальный запуск проекта
- [ ] Запустить [third-party ПО](https://github.com/Dokhat-Pigeon/docker-compose);
- [ ] Клонировать репозиторий на локальное окружение;
- [ ] Установить переменные среды (для application.properties) в конфигурации запуска приложения в среде разработки;
```
API_REST_WHITE_LIST=/swagger-ui.html, /swagger-ui/**, /v3/api-docs/**, /v1/token, /v1/change-password/**, /v1/user/verification/**, /v1/user/registration, /v1/user/authorization, /ws/**, /ws-test/**;API_REST_USER_LIST=/v1/user/logout, /v1/blacklist/**, /v1/user/**;AUTH_ROLES_USER=ADMINISTRATOR, TESTER, MODERATOR, USER;AUTH_ROLES_ADMIN=ADMINISTRATOR, TESTER
```
- [ ] Запустить сборку и запуск проекта.