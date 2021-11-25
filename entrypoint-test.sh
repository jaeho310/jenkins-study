#!/bin/bash

export PATH=$PATH:/app/sonar-scanner/bin/

cd /app
rm -rf coverrage.out report.json

go test -v ./... -coverprofile=coverage.out -json > report.json

# run sonar scanner
sonar-scanner \
  -Dsonar.projectKey=jenkins-test \
  -Dsonar.projectName=jenkins-test \
  -Dsonar.sourceEncoding=UTF-8 \
  -Dsonar.projectVersion=1.0 \
  -Dsonar.language=go \
  -Dsonar.source=. \
  -Dsonar.exclusions=**/*_test.go,**/mock/**,**/secret/**,**/docs/**,**/data/**,.idea/**,**/vendor/** \
  -Dsonar.host.url=http://192.168.102.114:32044 \
  -Dsonar.go.coverage.reportPaths=**/coverage.out \
  -Dsonar.login=6744f9dee49793d55d26050bd7a33c930422e471 \
  -Dsonar.tests=.
#   -Dsonar.test.inclusions=**/*_test.go \
#   -Dsonar.test.exclusions=**/vendor/**