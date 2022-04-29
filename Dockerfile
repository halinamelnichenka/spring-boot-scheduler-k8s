FROM core-registry.tools.cosmic.sky/core-engineering/release/alpine-java11:v0.27
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.config.additional-location=file:/opt/config/config.yaml -jar /app.jar", ""]
#ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.config.additional-location=file:/opt/config/config.yaml,file:/opt/secret/secret.yaml -jar /app.jar", ""]