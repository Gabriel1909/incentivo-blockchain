#!/bin/bash
# Copyright IBM Corp. All Rights Reserved.
#
# SPDX-License-Identifier: Apache-2.0
#

installChaincode() {

    local CHAINCODE_NAME=$1
    local PEER_NAME=$2

    export CORE_PEER_ADDRESS=$PEER_NAME:7051
    export CORE_PEER_TLS_ROOTCERT_FILE=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/$ORG_NAME.example.com/peers/$PEER_NAME/tls/ca.crt

    peer lifecycle chaincode package $CHAINCODE_NAME.tar.gz --path ../../../chaincode/$CHAINCODE_NAME --lang java --label "$CHAINCODE_NAME"_1.0
    peer lifecycle chaincode install $CHAINCODE_NAME.tar.gz

    peer lifecycle chaincode queryinstalled >&log.txt
    cat log.txt
    PACKAGE_ID=$(sed -n "/"$CHAINCODE_NAME"_1.0/{s/^Package ID: //; s/, Label:.*$//; p;}" log.txt)

    peer lifecycle chaincode approveformyorg -o orderer0.hospital1.example.com:7050 --tls --cafile /opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/hospital1.example.com/peers/orderer0.hospital1.example.com/msp/tlscacerts/tlsca.hospital1.example.com-cert.pem --channelID $CHANNEL_NAME --name $CHAINCODE_NAME --version 1.0 --package-id $PACKAGE_ID --sequence "1"
    peer lifecycle chaincode commit -o orderer0.hospital1.example.com:7050 --tls --cafile /opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/hospital1.example.com/peers/orderer0.hospital1.example.com/msp/tlscacerts/tlsca.hospital1.example.com-cert.pem --channelID $CHANNEL_NAME --name $CHAINCODE_NAME --version 1.0  --sequence "1"
}

export CHANNEL_NAME=providerschannel
export ORG_NAME=hospital1
export MSPID=Hospital1MSP
export CORE_PEER_LOCALMSPID=$MSPID
export CORE_PEER_MSPCONFIGPATH=/opt/gopath/src/github.com/hyperledger/fabric/peer/crypto/peerOrganizations/$ORG_NAME.example.com/users/Admin@$ORG_NAME.example.com/msp

installChaincode "global" "P874LG"
installChaincode "resumo" "P874LG"
installChaincode "relacionamento" "P874LG"

installChaincode "global" "P09Q6Y"
installChaincode "resumo" "P09Q6Y"
installChaincode "relacionamento" "P09Q6Y"

installChaincode "global" "P60CC5"
installChaincode "resumo" "P60CC5"
installChaincode "relacionamento" "P60CC5"

installChaincode "global" "P30KEH"
installChaincode "resumo" "P30KEH"
installChaincode "relacionamento" "P30KEH"

installChaincode "global" "P51VDL"
installChaincode "resumo" "P51VDL"
installChaincode "relacionamento" "P51VDL"