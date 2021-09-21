FROM gradle:jdk11
LABEL maintainer="Phi - Fintech as a Service"

VOLUME /tmp

COPY . ./

RUN gradle build
RUN mkdir -p /opt/vault/app/ && mv ./build/libs/pin-vault-1.0.0.jar /opt/vault/app/
WORKDIR /opt/vault/app/
RUN wget -O elastic-apm-agent.jar https://search.maven.org/remotecontent?filepath=co/elastic/apm/elastic-apm-agent/1.17.0/elastic-apm-agent-1.17.0.jar

CMD java -javaagent:elastic-apm-agent.jar \
    -Delastic.apm.service_name=profile \
	-Delastic.apm.application_packages=br.com.phi.profile.profile \
	-Delastic.apm.server_urls=http://apm-server.monitoring:8200 \
    -Delastic.apm.environment=${CI_COMMIT_REF_NAME} \
    -jar -Xmx4096M -Xms1024M -XX:MaxMetaspaceSize=256M pin-vault-1.0.0.jar
