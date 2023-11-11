FROM amazoncorretto:17.0.9

ENV APP_NAME=st-usuarios
WORKDIR /app

COPY ./st-usuarios/target/${APP_NAME}.jar  ${APP_NAME}.jar

CMD java -jar ${APP_NAME}.jar
EXPOSE 7001
