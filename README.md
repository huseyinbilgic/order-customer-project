# order-customer-project

## Teknolojiler
* Java 17
* Spring Boot

* Spring Web
* Maven
* Hibernate
* Data JPA 
* MySQL Driver
* Lombok
##### Not: Proje main branchindedir.
## Kurulum
* Öncelikle mysql 8'i bilgisayarınıza kurun
* Kurduktan sonra ordercustomerdb adında bir veritabanı oluşturun.
* https://github.com/huseyinbilgic/order-customer-project/blob/main/src/main/resources/application.properties dosyasındaki username ve password alanlarını bilgisayarınızdaki mysql username ve password bilgilerine göre girin.
* Bu yapılandırmalardan sonra çalıştırmaya hazır olacaktır.

## API ENDPOINTS
### Customer API
| Route  | HTTP isteği | Body   | Açıklama   |
|---|---|---|---|
| http://localhost:8080/api/customers  | `GET`   |  Yok | Tüm müşterileri getir
| http://localhost:8080/api/customers/{customerId}  |`GET`   |  Yok | Id'ye göre müşteri getir
| http://localhost:8080/api/customers |`POST`   |  CustomerRequest | Müşteri oluştur 
| http://localhost:8080/api/customers/{customerId}  |`PUT`   |  CustomerRequest | Müşteri güncelle
| http://localhost:8080/api/customers/{customerId}  |`DELETE`   |  Yok | Müşteri sil
| http://localhost:8080/api/customers/search/by-name/{name}  |`GET`   |  Yok | Müşteri adı girilen değeri içeren müşterileri getir
| http://localhost:8080/api/customers/customers-not-orders  |`GET`   |  Yok | Siparişi olmayan müşterileri getirir


### Order API
| Route  | HTTP isteği | Body   | Açıklama   |
|---|---|---|---|
| http://localhost:8080/api/orders  |`GET`   |  Yok | Tüm siparişleri getir
| http://localhost:8080/api/orders/{orderId}  |`GET`   |  Yok | Id'ye göre sipariş getir
| http://localhost:8080/api/orders  |`POST`   |  OrderRequest | Sipariş oluştur
| http://localhost:8080/api/orders/{orderId}  |`PUT`   |  OrderRequest | Müşteri güncelle
| http://localhost:8080/api/orders/{orderId}  |`DELETE`   |  Yok | Müşteri silme
| http://localhost:8080/api/orders/search/after-date  |`GET`   |  OrderAfterCreationDateRequest | Girilen tarihten sonra yapılan siparişler


## Requests

### CustomerRequest 
{
    "name":text,
    "age":number
}
### OrderRequest 
{
    "totalPrice":number,
    "customerId":number
}

### OrderAfterCreationDateRequest
{
    "date":text(date)
}


