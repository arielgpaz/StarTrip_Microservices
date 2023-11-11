FROM amazoncorretto:17.0.9

ENV APP_NAME=st-reservas
WORKDIR /app

COPY ./st-reservas/target/${APP_NAME}.jar  ${APP_NAME}.jar

CMD java -jar ${APP_NAME}.jar
EXPOSE 7004
