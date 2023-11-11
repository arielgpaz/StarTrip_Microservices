FROM amazoncorretto:17.0.9

ENV APP_NAME=st-anuncios
WORKDIR /app

COPY ./st-anuncios/target/${APP_NAME}.jar  ${APP_NAME}.jar

CMD java -jar ${APP_NAME}.jar
EXPOSE 7003
