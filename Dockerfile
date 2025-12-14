FROM tomcat:9.0-jdk21

RUN rm -rf /usr/local/tomcat/webapps/*

COPY dist/LP1_T2_LOAYZA_LEONARDO.war /usr/local/tomcat/webapps/ROOT.war

RUN cd /usr/local/tomcat/webapps && \
    unzip -q ROOT.war -d ROOT && \
    rm ROOT.war && \
    sed -i 's/metadata-complete="true"/metadata-complete="false"/g' ROOT/WEB-INF/web.xml || echo "Metadata complete not found"

EXPOSE 8080

CMD ["catalina.sh", "run"]