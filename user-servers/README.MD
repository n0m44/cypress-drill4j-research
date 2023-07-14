# Компиляция
```bash
mvn clean install -DskipTests
```

# Запуск jar файла
```bash
java - jar target/user-servers-1.0-SNAPSHOT.jar
```

P.S. Для запуска jar-а с другим конфигурационным файлом можно воспользоваться
https://www.baeldung.com/spring-properties-file-outside-jar
```bash
java -jar app.jar --spring.config.location=file:///Users/home/config/jdbc.properties
```

# Дополнительно доступны без проверки аутентификации:
- http://localhost:8080/h2-console/ - h2 по умолчанию
- http://localhost:8080/swagger-ui/index.html - swagger docs 