FROM tomcat:9
#SSH 서버 설치
RUN apt-get update
RUN apt-get install -y openssh-server
RUN apt-get install -y git
RUN apt-get install -y vim
RUN apt-get clean
RUN mkdir /var/run/sshd
#root 암호 설정
RUN echo 'root:1234' | chpasswd
#sshd_config 설정 변경
RUN sed -ri 's/^#?PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config
# tomcat 작업 공간 지정
WORKDIR /usr/local/tomcat
RUN cp -R ./webapps.dist/* ./webapps/
RUN sed -i '$d' /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "  <role rolename=\"manager-gui\" />" >> /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "  <role rolename=\"manager-script\" />" >> /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "  <role rolename=\"manager-jmx\" />" >> /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "  <role rolename=\"manager-status\" />" >> /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "  <user username=\"admin\" password=\"admin\"  roles=\"manager-gui, manager-script, manager-jmx, manager-status\"/>" >> /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "  <user username=\"deployer\" password=\"deployer\" roles=\"manager-script\"/>" >> /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "  <user username=\"tomcat\" password=\"tomcat\"  roles=\"manager-gui\"/>" >> /usr/local/tomcat/conf/tomcat-users.xml
RUN echo "</tomcat-users>" >> /usr/local/tomcat/conf/tomcat-users.xml

RUN sed -i '/^  <Valve/d' /usr/local/tomcat/webapps/manager/META-INF/context.xml
EXPOSE 22
EXPOSE 8080
#SSH 서비스 실행
CMD ["/usr/sbin/sshd", "-D"]
#tomcat run 
CMD ["catalina.sh", "run"]
