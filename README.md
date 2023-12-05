Zadání
Nyní se zaměřme na samotné zadání úkolu. Cílem je vytvoření aplikace client - server. Bude se tedy jednat o dvě aplikace, které spolu budou navzájem komunikovat. Obě aplikace budou postaveny na Spring Bootu,
ke komunikaci si mužeš vybrat mezi Netty nebo websockety. Komunikace mezi aplikacemi musí být zabezpečená pomocí TLS. A k serializaci zpráv můžeš použít java.io.Serializable, Google Protocol Buffers, 
Apache Avro či jakýkoliv serializační nástroj, který jsi zvyklý používat.

Clientská část
Jednoduchá spring boot aplikace, která:
● po startu vytvoří persistentní spojení se serverem (netty/websocket)
● periodicky zkontroluje využití paměti či jiných systémových prostředků a pošle data na server

Servrová část
Jednoduchá spring boot aplikace, která:
● obsluhuje připojené klienty
● přijímá zprávy z klienta a ukládá je do databáze
○ databazovou strukturu si můžeš vydefinovat sám, dle tvých potřeb
● zprávy za posledních x minut zobrazí v jednoduché webové aplikaci
○ výběr webové technologie je na tobě
● zobrazí všechny zprávy přesahující určitý threshold (například hodnota CPU, definice záleží na tobě)

Perlička na konec
Není to nic povinného, ale pokud budeš mít chuť a čas ti to dovolí, bylo by fajn aby celé řešení bylo v dockeru a bylo možné ho spustit přes docker compose.
