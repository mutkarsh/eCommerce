spring:
  application:
    name: ecommerceutk
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost/globantdb
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: update

server:
  port: 8585


eureka:
  client:
    registerWithEureka: true
    fetchRegistry: true
    serviceUrl:
      defaultZone: http://192.168.43.163:8762/eureka/
  instance:
    hostname: localhost
