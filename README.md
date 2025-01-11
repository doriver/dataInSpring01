## CSV파일에 있는 데이터를, Spring data를 이용해 MySQL과 Elastic Cloud에 넣었음
## dataToEs브랜치( data-elasticsearch로 Elastic Cloud에 )  ,  dataLoading브랜치( jpa로 MySQL에 )

### CsvFileReaderService
csv파일에 있는 데이터를 읽어서, List<자바DTO> 형태로 return( 처음3개, 중간3개, 마지막3개 )     
csv의 각 row들이 CSVRecord에 담기고, 각 필드값들은 String형태로 반환된다.  ( String 좀 파볼만함 )       
jsonString의 경우, ObjectMapper를 이용해 자바타입으로 변환해줌

### CsvFileInsertService
csv파일에 있는 데이터를 읽어서, 각 row마다 @Transactional 이 걸리게 JPA save메소드쪽은 따로 메소드로 만들어 처리.     
elasticsearch에선 @Transactional 대신 try catch사용

### Elastic Cloud 연동
Config클래스( @EnableElasticsearchAuditing ) + dependencies( 'co.elastic.clients:elasticsearch-java:8.17.0' ) + porperites파일

#### DB에 있는 데이터들을 Thymeleaf로 브라우저에서 렌더링 해봄
#### commons-csv, jackson-databind 사용
