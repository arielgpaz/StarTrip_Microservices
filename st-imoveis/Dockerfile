FROM amazoncorretto:17.0.9

ENV APP_NAME=st-imoveis
WORKDIR /app

COPY ./st-imoveis/target/${APP_NAME}.jar  ${APP_NAME}.jar

CMD java -jar ${APP_NAME}.jar
EXPOSE 7002
