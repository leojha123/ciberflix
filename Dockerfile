FROM tomcat:9.0-jdk21


RUN rm -rf /usr/local/tomcat/webapps/*


COPY dist/LP1_T2_LOAYZA_LEONARDO.war /usr/local/tomcat/webapps/ROOT.war


RUN sed -i 's/metadata-complete="true"/metadata-complete="false"/' /usr/local/tomcat/webapps/ROOT/WEB-INF/web.xml || true

EXPOSE 8080

CMD ["catalina.sh", "run"]