FROM 149.0.170.16/centos-tongweb:ums-schj
MAINTAINER withub
ADD ./target/ums.war /home/tongweb/autodeploy
HEALTHCHECK --interval=5s --timeout=3s CMD curl --silent --fail http://localhost:8080/ums/login.jsp || exit 1
ENV LANG en_HK.UTF-8
CMD /home/tongweb/bin/startserver.sh