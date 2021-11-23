!/bin/bash

cd /app
rm -rf test-results/*
source env.sh

env

# run unittest with coverage
coverage run test-runner.py -o test-results

# export coverage to xml, txt
coverage xml -o test-results/coverage.xml
coverage report > test-results/coverage.txt

UNITTEST_XML_FILES=`ls /app/test-results/TEST*.xml | tr [:space:] ','`

# run sonar scanner
sonar-scanner \
  -Dsonar.projectKey=Jaguar \
  -Dsonar.projectVersion=$1 \
  -Dsonar.sources=src \
  -Dsonar.tests=tests \
  -Dsonar.exclusions=src/ply_tables/** \
  -Dsonar.sourceEncoding=UTF-8 \
  -Dsonar.host.url=http://192.168.102.114:32044 \
  -Dsonar.login=6167efa25b0f71b5ea270a5b992525619d03c5f8 \
  -Dsonar.python.coverage.reportPaths=/app/test-results/coverage.xml \
  -Dsonar.junit.reportPaths=${UNITTEST_XML_FILES::-1}