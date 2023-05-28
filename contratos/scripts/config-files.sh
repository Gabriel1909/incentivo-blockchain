#!/bin/bash
# Copyright IBM Corp. All Rights Reserved.
#
# SPDX-License-Identifier: Apache-2.0
#

cd ./crypto-config/peerOrganizations/hospital1.example.com/ || exit

for file in $(find . -name "*.yaml")
do
  sed -i 's/\\/\//g' "$file"
done

cd ../../../

function one_line_pem {
    echo "`awk 'NF {sub(/\\n/, ""); printf "%s\\\\\\\n",$0;}' $1`"
}

function yaml_ccp {
    local PP=$(one_line_pem $1)
    local CP=$(one_line_pem $2)
    sed -e "s#\${PEER_PEM}#$PP#" \
        -e "s#\${CA_PEM}#$CP#" \
        connection-template.yaml | sed -e $'s/\\\\n/\\\n        /g'
}

PEER_PEM=crypto-config/peerOrganizations/hospital1.example.com/tlsca/tlsca.hospital1.example.com-cert.pem
CA_PEM=crypto-config/peerOrganizations/hospital1.example.com/ca/ca.hospital1.example.com-cert.pem

yaml_ccp $PEER_PEM $CA_PEM > crypto-config/peerOrganizations/hospital1.example.com/connection.yaml
