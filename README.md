# Московская биржа
Сервис справочника данных по ценным бумага Московской биржи, 
а также истории сделок по этим ценным бумагам.

### Установка и запуск сервиса

Для установки и работы сервиса необходимо:
  - база данных PostgreSQL;
  - Версия JRE 11.0.8.
  
Конфигурационный файл базы данных расположен в `src/main/resources/config/db_config.txt`, в данном файле 
необходимо прописать в формате JSON данные для работы сервиса - имя рабочей базы данных, имя пользователя и пароль.
   ```json
{
     "host":"localhost",
     "port":8000
}        
   ```

Конфигурационный файл для запуска сервера расположен `src/main/resources/config/server_config.txt`, в данном файле 
необходимо прописать в формате JSON данные для работы сервиса - хост и порт.
   ```json
{
     "host":"localhost",
     "port":8000
}
   ```

Для запуска сервиса необходимо в командной строке в рабочей директории `/tot-systems` запустить
сборку проекта командой:

`./gradlew build`

И запустить сервис командой:

`./gradlew run`

Стартовая страница для работы с сервисом  `http://"host":"port"/`.

### Описание API

API позволяет:

Получать информацию о ценных бумагах и сделок по ним в виде html-таблицы. 

1. Получить информацию по ценной бумаге, отправив  GET запрос
 на `http://"host":"port"/security/read` с обязательным параметром
    - secid - тикер ценной бумаги для поиска в базе.
    
2. Удалить информацию о ценной бумаге,  отправив  GET запрос
на http://"host":"port"/security/delete  с обязательным параметром
   - secid - тикер ценной бумаги для поиска в базе.
   
3. Получить список ценных бумаг, отправив GET запрос на `http://"host":"port"/security/list` с параметрами:
    - limit - количество выводимых элементов (обязательный);
    - offset - смещение от начала списка (обязательный);
    - sort_parameter - параметр сортировке при выводе списка (secid или emitent_title).
   
4. Добавить информацию о ценной бумаге в базу данных, отправив POST запрос на 
`http://"host":"port"/security/add` с данными по бумаге в формате JSON:
    ```json
        {
           "secid":"AAPL",
           "shortname":"Apple",
           "regnumber":" 111",
           "name":" ",
           "isin":"US0378331005",
           "is_traded":"1",
           "emitent_id":"1281003",
           "emitent_title":"Apple Inc",
           "emitent_inn":"1212",
           "emitent_okpo":"1212",
           "gosreg":"121",
           "type":"common_share",
           "group":"stock_shares",
           "primary_boardid":"EQRD\t",
           "marketprice_boardid":"EQRD",
           "history":[]
        }
    ```
    При ручном сохранении ценной бумаги   передаваемые данные в поле name могут быть - 
    только кириллица, цифры и пробел.
5. Обновить информацию о ценной бумаге в базе данных, отправив POST запрос на 
`http://"host":"port"/security/update` с новыми данными по бумаге в формате JSON:
   ```json
   {
      "secid":"AAPL",
      "shortname":"Apple",
      "regnumber":" 111",
      "name":" ",
      "isin":"US0378331005",
      "is_traded":"1",
      "emitent_id":"1281003",
      "emitent_title":"Apple Inc",
      "emitent_inn":"1212",
      "emitent_okpo":"1212",
      "gosreg":"121",
      "type":"common_share",
      "group":"stock_shares",
      "primary_boardid":"EQRD\t",
      "marketprice_boardid":"EQRD",
      "history":[]
   }
   ```
    При обновлении инфораци о ценной бумаге передаваемые данные в поле name могут быть - 
только кириллица, цифры и пробел.

6. Добавить информацию о бумагах из файла выгрузки Московской биржи (securities_*.xml),
отправить POST запрос на `http://"host":"port"/security/add/all` c
переданными данными из файла.

7. Получить информацию о сделках по ценой бумаге, отправив  GET запрос
на `http://"host":"port"/security/history`  с обязательным параметром:
    - secid - тикер ценной бумаги для поиска в базе.

8. Получить информацию о сделке по ценной бумаге, отправив  GET запрос
на `http://"host":"port"/transaction/read`  с обязательным параметром
    - id - id операции в базе.
        
9. Удалить информацию о сделке по ценной бумаге,  отправив  GET запрос
на `http://"host":"port"/transaction/delete`  с обязательным параметром
   - id - id операции в базе.
       
10. Получить список всех сделок с ценными бумагами, отправив GET 
запрос на `http://"host":"port"/transaction/list` с  параметрами:
    - limit - количество выводимых элементов (обязательный);
    - offset - смещение от начала списка (обязательный);
    - sort_parameter - параметр сортировке при выводе списка (id, secid или tradedate).
    
11. Добавить информацию о ценной бумаге в базу данных, отправив POST запрос на 
 `http://"host":"port"/transaction/add` с данными по бумаге в формате JSON:
    ```json
        {
           "id":"666",
           "board_id":"TQBR",
           "trade_date":"2020-04-15",
           "shortname":"Акрон",
           "secid":"AKRN",
           "num_trades":"1782",
           "value":"136521992",
           "open":"5714.7",
           "low":"5522.767",
           "high":"5746.688",
           "legal_close_price":"5740.8",
           "wa_price":"5638.86",
           "close":"5740.5",
           "volume":"24217.4",
           "market_price_2":"5638.33",
           "market_price_3":"345.23",
           "admitted_quote":"4534.23",
           "mp_2_val_trd":"3454.55",
           "market_price_3_trades_value":"34534.54",
           "admitted_value":"43534.1",
           "wa_val":"3453.0"
        }
    ```
    При отсутствии информации о ценной бумаге в базе сервис выполнит rest запрос к API биржи 
`http://iss.moex.com/iss/securities.xml?q=SEARCH_STRING` и добавит 
все найденные по запросу данные  в базу.

12. Обновить информацию о сделке по ценной бумаге в базе данных, отправив POST запрос на 
    `http://"host":"port"/transaction/update` с новыми данными по бумаге в формате JSON:
       ```json
        {
          "id":"666",
          "board_id":"TQBR",
          "trade_date":"2020-04-15",
          "shortname":"Акрон",
          "secid":"AKRN",
          "num_trades":"1782",
          "value":"136521992",
          "open":"5714.7",
          "low":"5522.767",
          "high":"5746.688",
          "legal_close_price":"5740.8",
          "wa_price":"5638.86",
          "close":"5740.5",
          "volume":"24217.4",
          "market_price_2":"5638.33",
          "market_price_3":"345.23",
          "admitted_quote":"4534.23",
          "mp_2_val_trd":"3454.55",
          "market_price_3_trades_value":"34534.54",
          "admitted_value":"43534.1",
          "wa_val":"3453.0"
       }
       ```
   
13. Добавить информацию о сделках по ценным бумагам из файла выгрузки Московской биржи (history_*.xml),
отправить POST запрос на `http://"host":"port"/security/add/all` c
переданными данными из файла.
    
14. Получить сводную таблицу - отправить GET запрос на `http://"host":"port"/pivot_table/list `
с параметрами:
    - limit - количество выводимых элементов (обязательный);
    - offset - смещение от начала списка (обязательный);
    - sort_parameter - параметр сортировке при выводе списка (secid, regnumber, name, 
    emitent_title, tradedate, numtrades, open, close);
    - filter_parameter - параметр для фильтрации (emitent_title или tradedate);
    - filter_parameter_value - значение для параметра фильтрации.


### Описание архитектуры приложения

HTTP-сервер ExchangeHttpServer принимает запросы по REST API и передает их в репозиторий Repository.
Репозиторий прокидывает запрос в ExchangeDataSource. 
Репозиторий позволяет использовать множество источников данных, 
но на текущий момент реализован один - база данных.
ExchangeDataSource реализует подключение к базе данных PostgreSQL.
Работа с базой данных реализована с использованием Hibernate ORM.
