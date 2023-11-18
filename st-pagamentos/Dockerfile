FROM amazoncorretto:17.0.9

ENV APP_NAME=st-pagamentos
WORKDIR /app

COPY ./st-pagamentos/target/${APP_NAME}.jar  ${APP_NAME}.jar

CMD java -jar ${APP_NAME}.jar
EXPOSE 7005
