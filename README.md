# cypress-drill4j-research
---
Небольшое приложение, для исследования возможностей drill4j.

- **Backend**: java
- **Fronend**: Typescript/React
- **Database**: H2

Приложение позволяющее:

1. Аутентифицироваться
2. Создать пользователя, если аутентифицирован 
3. Изменить пользователя, если аутентифицирован
4. Получить список всех юзеров

(back) Пользователь: логин | пароль | uuid  в БД

(back) Используется база данных H2 in mem, но где-то должен валяться файл конфигурации

(?) Нужно для endpoint’ов сделать описание в yaml файлах

Есть страницы: 
 1. Аутентификация
 2. Список юзеров
 3. Создание юзера
 4. Редактирование юзера

Дизайн не важен, но нужно что-то добавить в css, чтобы посмотреть что будет при PR с изменениями в css
И ещё нужно вынести константы локализации в отдельный файл

Цели этого приложения: сделать имитацию использования ресурсов/кода/конфигураций, чтобы можно было проверить, что будет покрывать drill4j, а что нет. 
Результат исследования будет вынесен в отдельный файл. Текущий readme будет изменён.

Когда приложение будет готово: оно будет покрыто e2e тестами на typescript/cypress. 
