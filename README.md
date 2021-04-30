# Book Cloud
Simple application for manage books and authors

Technical features:
- CRUD APIs for book and author. Search API for book.
- Exception handling (generic and specific exception)
- Database migration using Liquibase
- API specification (api.yaml)
- Services unit tests with embedded database (H2) 

## Prerequisites
- JRE 11 or above
- Maven v3.6 or above
- MySQL database v8.0.20 or above

## Installation
1. Clone the repo

```git clone https://github.com/anhnguyenbk/book-cloud.git```

2. Install library and generate the source from the API specification.

```mvn clean compile```

3. Edit the configuration file for your database connection parameters

```src/main/resources/application.yaml```

## Usage
### API specification
Copy the content from `api.yaml` in resources folder into [Swagger Editor](https://editor.swagger.io/) to see the details.

## License
[MIT](https://choosealicense.com/licenses/mit/)
