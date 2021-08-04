# Spring Boot paging and sorting example

- H2 database
- Lombok and Mapstruct

Endpoints (all with GET method):
1. `Get all products` - `http://localhost:8080/api/v1/products/?page=2&size=50&sort=name&order=desc`
2. `Get all products with custom sort - +param, -param notation` - `http://localhost:8080/api/v1/products/?page=2&size=50&sort=-price,-createdAt,%2Bname`
3. `Get all products whose name contains term` - `http://localhost:8080/api/v1/products/?name=de&page=2&size=50&sort=name&order=desc`
4. `Get all product whose price satisfies price range` - `http://localhost:8080/api/v1/products/?lower=200&upper=1000&page=2&size=50&sort=name&order=desc`
5. `Get all products whose name appears in the name list` - `http://localhost:8080/api/v1/products/?names=de&names=pe&page=2`
