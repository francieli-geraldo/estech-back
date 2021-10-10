FROM gradle:jdk11 as build
LABEL maintainer="estech - SC Software"
ENV PROFILE="local"
ENV SERVICE_PORT=8081
VOLUME /tmp
COPY . ./
RUN gradle clean build
RUN ls
RUN mkdir -p /opt/estech/app/ \
  && cp -r . /opt/estech/app/
WORKDIR /opt/estech/app/

RUN chmod +x entrypoint.sh
CMD ["./entrypoint.sh"]