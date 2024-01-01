### Процедура запуска автотестов
**На компьютере установлены Docker и IntelliJ Idea.**

1. Открываем проект в IntelliJ Idea.
2. Запускаем контейнеры в Docker. Для этого в терминале IntelliJ Idea вводим команду `docker compose up -d` 

- для запуска PostgreSQL (по умолчанию прописана данная база данных) достаточно в файл 'application.properties' прописать следующие данные:

  `spring.credit-gate.url=http://localhost:9999/credit
    spring.payment-gate.url=http://localhost:9999/payment
    spring.datasource.url=jdbc:postgresql://localhost:5432/app
    spring.datasource.username=app
    spring.datasource.password=pass`
- для запуска MySQL данной БД необходимо в файл 'application.properties' ввести следующие данные:

  `spring.credit-gate.url=http://localhost:9999/credit
    spring.payment-gate.url=http://localhost:9999/payment
    spring.datasource.url=jdbc:mysql://localhost:3306/app
    spring.datasource.username=app
    spring.datasource.password=pass`

3. Открываем приложение-эмулятор банковских сервисов. Открываем второй терминал в IntelliJ Idea и вводим команду: `java -jar artifacts/aqa-shop.jar`
4. Запускаем тесты командой `gradlew test`.
