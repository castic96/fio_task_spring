# FIO - úkol (Spring verze)
Tato verze řešení využívá **Spring Framework** a **Spring Boot** k realizaci požadovaného řešení. 

Toto řešení jsem rozpadl do více _packages_ z důvodu správného strukturování projektu pro "budoucí rozšiřitelnost a udržovatelnost". Stejně tak jsem použil implementaci závislostí vůči rozhraní (_Dependency Inversion_) a využil jsem injektování závislostí (_Dependency Injection_) prostřednictvím _Spring Framework_.

Třídě `cz.fio.StoreContact` ze zadání odpovídá třída `cz.fio.testjavistaspring.service.impl.ContactServiceImpl`, která implementuje rozhraní `cz.fio.testjavistaspring.service.IContactService`. 

## Sestavení a spuštění aplikace
Aplikaci lze sestavit prostřednictvím `Mavenu` (testovaná verze `3.8.1`) a `JDK 17`. Build a spuštění aplikace lze také realizovat prostřednictvím `Dockeru` a to například následovně:
1. provést `checkout` projektu z GITu,
2. spustit `Docker`,
3. v root složce projektu zadat příkaz přes `cmd`: `docker build -t fio/task .` (pro build aplikace),
4. v root složce projektu zadat příkaz přes `cmd`: `docker-compose up` (pro spuštění aplikace).

Aplikace bude přístupná na adrese: http://localhost:8080/test-javista/

Příkaz pro odstranění docker containeru s aplikací: `docker-compose down`