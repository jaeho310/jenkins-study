FROM golang

RUN apt update -y \
    && apt install -y curl \
    && apt install -y unzip

COPY ./ /app

WORKDIR /app

RUN curl -s -L https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.6.2.2472-linux.zip -o sonarscanner.zip \
  && unzip -qq sonarscanner.zip \
  && rm -rf sonarscanner.zip \
  && mv sonar-scanner-4.6.2.2472-linux sonar-scanner

RUN go test -v ./... -coverprofile=coverage.out -json > report.json

# WORKDIR /app

# CMD ["test-report.sh"]